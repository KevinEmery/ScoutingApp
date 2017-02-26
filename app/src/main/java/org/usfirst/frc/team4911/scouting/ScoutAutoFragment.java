package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import org.usfirst.frc.team4911.scouting.datamodel.AutonomousPeriod;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;
import org.usfirst.frc.team4911.scouting.datamodel.MatchData;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutAutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutAutoFragment extends Fragment
    implements  RecordShotAttemptFragment.OnShotAttemptCreatedListener {

    OnAutoPeriodObjectCreatedListener mListener;

    CheckBox crossedBaseline;
    CheckBox loadedFromHopper;

    List<ShotAttempt> shotAttempts;
    List<GearAttempt> gearAttempts;

    public ScoutAutoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScoutAutoFragment.
     */
    public static ScoutAutoFragment newInstance() {
        return new ScoutAutoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecordShotAttemptFragment recordShotAttemptFragment =
                (RecordShotAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.auto_shooting_fragment_container);

        if (recordShotAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.auto_shooting_fragment_container,
                            RecordShotAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }

        RecordGearAttemptFragment recordGearAttemptFragment =
                (RecordGearAttemptFragment) getChildFragmentManager()
                .findFragmentById(R.id.auto_gear_fragment_container);

        if (recordGearAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.auto_gear_fragment_container,
                            RecordGearAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_auto, container, false);

        crossedBaseline = (CheckBox) view.findViewById(R.id.chkbx_auto_crossed_baseline);
        loadedFromHopper = (CheckBox) view.findViewById(R.id.chkbx_auto_loaded_from_hopper);

        shotAttempts = new ArrayList<>();
        gearAttempts = new ArrayList<>();

        Button save = (Button) view.findViewById(R.id.button_auto_save);
        save.setOnClickListener(autoSaveButton);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAutoPeriodObjectCreatedListener) {
            mListener = (OnAutoPeriodObjectCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onShotAttemptCreated(ShotAttempt shotAttempt) {
        // Add it to the list of shot attempts
        shotAttempts.add(shotAttempt);
    }

    View.OnClickListener autoSaveButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // Create the autonomous period object and hand it on up to the top level activity
            AutonomousPeriod autonomousPeriod = new AutonomousPeriod();
            autonomousPeriod.setAutoMobilityPoints(crossedBaseline.isChecked());
            autonomousPeriod.setLoadedFromHopper(loadedFromHopper.isChecked());
            autonomousPeriod.setShotAttempts(shotAttempts);
            autonomousPeriod.setGearAttempts(gearAttempts);
            mListener.onAutoPeriodObjectCreated(autonomousPeriod);

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(2);
        }
    };

    /**
     * Passes the autonomous period data object up to whoever is listening. Activities that contian
     * this fragment should implement this.
     */
    public interface OnAutoPeriodObjectCreatedListener {
        void onAutoPeriodObjectCreated(AutonomousPeriod autonomousPeriod);
    }
}
