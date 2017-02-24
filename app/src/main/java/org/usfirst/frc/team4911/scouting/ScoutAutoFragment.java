package org.usfirst.frc.team4911.scouting;

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
import org.usfirst.frc.team4911.scouting.datamodel.MatchData;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutAutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutAutoFragment extends Fragment {
    CheckBox crossedBaseline;
    CheckBox loadedFromHopper;

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
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        RecordShotAttemptFragment shootingEventFragment = RecordShotAttemptFragment.newInstance();
        RecordGearAttemptFragment recordGearAttemptFragment = RecordGearAttemptFragment.newInstance();

        fragmentTransaction.add(R.id.auto_shooting_fragment_container, shootingEventFragment);
        fragmentTransaction.add(R.id.auto_gear_fragment_container, recordGearAttemptFragment);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_auto, container, false);

        crossedBaseline = (CheckBox) view.findViewById(R.id.chkbx_auto_crossed_baseline);
        crossedBaseline.setOnClickListener(crossedBaselineListener);

        loadedFromHopper = (CheckBox) view.findViewById(R.id.chkbx_auto_loaded_from_hopper);
        loadedFromHopper.setOnClickListener(loadedFromHopperListener);

        Button save = (Button) view.findViewById(R.id.button_auto_save);
        save.setOnClickListener(saveButton);

        return view;
    }

    View.OnClickListener crossedBaselineListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(crossedBaseline.isChecked()){
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setAutoMobilityPoints(true);
            }else{
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setAutoMobilityPoints(false);
            }
        }
    };

    View.OnClickListener loadedFromHopperListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(loadedFromHopper.isChecked()){
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setLoadedFromHopper(true);
            }else{
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setLoadedFromHopper(false);
            }
        }
    };

    View.OnClickListener saveButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            AutonomousPeriod autonomousPeriod = ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                    .getAutonomousPeriod();

            autonomousPeriod.setAutoMobilityPoints(crossedBaseline.isChecked());
            autonomousPeriod.setLoadedFromHopper(loadedFromHopper.isChecked());

            // TODO: Should be able to call shootingEventFragment for the ShotAttempt data.
            // The instance shoudl maintain the it's own state and not add the ShotAttempt to
            // the array.

            //ShotAttempt shotapptempt =
            //autonomousPeriod.getShotAttempts().add()

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(2);
        }
    };
}
