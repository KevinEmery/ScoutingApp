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
import org.usfirst.frc.team4911.scouting.datamodel.HopperAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutAutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutAutoFragment extends Fragment implements
        ShotAttemptFragment.OnShotAttemptCreatedListener,
        GearAttemptFragment.OnGearAttemptCreatedListener,
        HopperAttemptFragment.OnHopperAttemptCreatedListener {

    OnAutoPeriodObjectCreatedListener mListener;

    CheckBox crossedBaseline;

    List<ShotAttempt> shotAttempts;
    List<GearAttempt> gearAttempts;
    List<HopperAttempt> hopperAttempts;

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

        ShotAttemptFragment shotAttemptFragment =
                (ShotAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_auto_shooting);

        if (shotAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_auto_shooting,
                            ShotAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }

        GearAttemptFragment gearAttemptFragment =
                (GearAttemptFragment) getChildFragmentManager()
                .findFragmentById(R.id.fragment_container_auto_gear);

        if (gearAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_auto_gear,
                            GearAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }

        HopperAttemptFragment hopperAttemptFragment =
                (HopperAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_auto_hopper);

        if (hopperAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_auto_hopper,
                            HopperAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }

        // We need to make VERY SURE that rotating won't mess up our list
        if (shotAttempts == null) {
            shotAttempts = new ArrayList<>();
        }

        if (gearAttempts == null) {
            gearAttempts = new ArrayList<>();
        }

        if (hopperAttempts == null) {
            hopperAttempts = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_auto, container, false);

        crossedBaseline = (CheckBox) view.findViewById(R.id.chkbx_auto_crossed_baseline);

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
                    + " must implement OnAutoPeriodObjectCreatedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onShotAttemptCreated(ShotAttempt shotAttempt) {
        shotAttempts.add(shotAttempt);
    }

    @Override
    public void onGearAttemptCreated(GearAttempt gearAttempt) {
        gearAttempts.add(gearAttempt);
    }

    @Override
    public void onHopperAttemptCreated(HopperAttempt hopperAttempt) {
        hopperAttempts.add(hopperAttempt);
    }

    View.OnClickListener autoSaveButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // Create the autonomous period object and hand it on up to the top level activity
            AutonomousPeriod autonomousPeriod = new AutonomousPeriod();
            autonomousPeriod.setAutoMobilityPoints(crossedBaseline.isChecked());

            // Add to existing collection - don't replace
            autonomousPeriod.getGearAttempts().addAll(gearAttempts);
            autonomousPeriod.getShotAttempts().addAll(shotAttempts);
            autonomousPeriod.getHopperAttempts().addAll(hopperAttempts);

            if (mListener != null) {
                mListener.onAutoPeriodObjectCreated(autonomousPeriod);
            }

            // Clear the shot and gear attempt lists
            shotAttempts.clear();
            gearAttempts.clear();
            hopperAttempts.clear();

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
