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

import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
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
        RecordGearAttemptTeleOpFragment.OnGearAttemptTeleopCreatedListener,
        RecordShotAttemptTeleOpFragment.OnShotAttemptTeleopCreatedListener {

    OnTeleopPeriodObjectCreatedListener mListener;

    CheckBox playedDefence;

    List<ShotAttemptTeleop> shotAttempts;
    List<GearAttemptTeleop> gearAttempts;

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

        RecordShotAttemptTeleOpFragment shotAttemptFragment =
                (RecordShotAttemptTeleOpFragment) getChildFragmentManager()
                        .findFragmentById(R.id.teleop_shooting_fragment_container);

        if (shotAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.teleop_shooting_fragment_container,
                            RecordShotAttemptTeleOpFragment.newInstance());
            fragmentTransaction.commit();
        }

        RecordGearAttemptTeleOpFragment gearAttemptTeleOpFragment =
                (RecordGearAttemptTeleOpFragment) getChildFragmentManager()
                        .findFragmentById(R.id.teleop_gear_fragment_container);

        if (gearAttemptTeleOpFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.teleop_gear_fragment_container,
                            RecordGearAttemptTeleOpFragment.newInstance());
            fragmentTransaction.commit();
        }

        // We need to make VERY SURE that rotating won't mess up our list
        if (shotAttempts == null) {
            shotAttempts = new ArrayList<>();
        }

        if (gearAttempts == null) {
            gearAttempts = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in OnCreateView of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_tele_op, container, false);

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
    }

    @Override
    public void onShotAttemptTeleopCreated(ShotAttemptTeleop shotAttemptTeleop) {
        shotAttempts.add(shotAttemptTeleop);
    }

    View.OnClickListener saveTeleop = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            TeleopPeriod teleopPeriod = new TeleopPeriod();

            teleopPeriod.setShotAttempts(shotAttempts);
            teleopPeriod.setGearAttempts(gearAttempts);
            teleopPeriod.setPlayedDefense(playedDefence.isChecked());

            if (mListener != null) {
                mListener.onTeleopPeriodObjectCreated(teleopPeriod);
            }

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
