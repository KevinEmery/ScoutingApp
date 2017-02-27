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
import android.widget.TextView;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.HopperAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.TeleopPeriod;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTeleOpFragment extends Fragment implements
        GearAttemptTeleOpFragment.OnGearAttemptTeleopCreatedListener,
        ShotAttemptTeleOpFragment.OnShotAttemptTeleopCreatedListener,
        HopperAttemptFragment.OnHopperAttemptCreatedListener {

    OnTeleopPeriodObjectCreatedListener mListener;

    CheckBox playedDefence;

    TextView shotAttemptLabel;
    TextView gearAttemptLabel;
    TextView hopperAttemptLabel;

    List<ShotAttemptTeleop> shotAttempts;
    List<GearAttemptTeleop> gearAttempts;
    List<HopperAttempt> hopperAttempts;

    public ScoutTeleOpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScoutTeleOpFragment.
     */
    public static ScoutTeleOpFragment newInstance() {
        return new ScoutTeleOpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShotAttemptTeleOpFragment shotAttemptFragment =
                (ShotAttemptTeleOpFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_tele_op_shooting);

        if (shotAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_tele_op_shooting,
                            ShotAttemptTeleOpFragment.newInstance());
            fragmentTransaction.commit();
        }

        GearAttemptTeleOpFragment gearAttemptTeleOpFragment =
                (GearAttemptTeleOpFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_tele_op_gear);

        if (gearAttemptTeleOpFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_tele_op_gear,
                            GearAttemptTeleOpFragment.newInstance());
            fragmentTransaction.commit();
        }

        HopperAttemptFragment hopperAttemptFragment =
                (HopperAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_tele_op_hopper);

        if (hopperAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_tele_op_hopper,
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
        // See note in OnCreateView of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_tele_op, container, false);

        shotAttemptLabel = (TextView) view.findViewById(R.id.label_shots_attempted_teleop);
        gearAttemptLabel = (TextView) view.findViewById(R.id.label_gears_attempted_teleop);
        hopperAttemptLabel = (TextView) view.findViewById(R.id.label_hoppers_activated_teleop);

        playedDefence = (CheckBox) view.findViewById(R.id.checkbox_tele_op_played_defence);
        Button save = (Button) view.findViewById(R.id.button_tele_op_save);
        save.setOnClickListener(saveTeleop);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTeleopPeriodObjectCreatedListener) {
            mListener = (OnTeleopPeriodObjectCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTeleopPeriodObjectCreatedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onGearAttemptTeleopCreated(GearAttemptTeleop gearAttemptTeleop) {
        gearAttempts.add(gearAttemptTeleop);
        String newLabel = getString(R.string.label_attempts_recorded) + gearAttempts.size();
        gearAttemptLabel.setText(newLabel);
    }

    @Override
    public void onShotAttemptTeleopCreated(ShotAttemptTeleop shotAttemptTeleop) {
        shotAttempts.add(shotAttemptTeleop);
        String newLabel = getString(R.string.label_attempts_recorded) + shotAttempts.size();
        shotAttemptLabel.setText(newLabel);
    }

    @Override
    public void onHopperAttemptCreated(HopperAttempt hopperAttempt) {
        hopperAttempts.add(hopperAttempt);
        String newLabel = getString(R.string.label_attempts_recorded) + hopperAttempts.size();
        hopperAttemptLabel.setText(newLabel);
    }

    View.OnClickListener saveTeleop = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            TeleopPeriod teleopPeriod = new TeleopPeriod();

            // Add to existing collection - don't replace
            teleopPeriod.getShotAttempts().addAll(shotAttempts);
            teleopPeriod.getGearAttempts().addAll(gearAttempts);
            teleopPeriod.getHopperAttempts().addAll(hopperAttempts);
            teleopPeriod.setPlayedDefense(playedDefence.isChecked());

            if (mListener != null) {
                mListener.onTeleopPeriodObjectCreated(teleopPeriod);
            }

            // Clear the shot and gear attempt lists
            shotAttempts.clear();
            gearAttempts.clear();
            hopperAttempts.clear();

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(3);
        }
    };

    /**
     * Passes the autonomous period data object up to whoever is listening. Activities that contian
     * this fragment should implement this.
     */
    public interface OnTeleopPeriodObjectCreatedListener {
        void onTeleopPeriodObjectCreated(TeleopPeriod teleopPeriod);
    }
}
