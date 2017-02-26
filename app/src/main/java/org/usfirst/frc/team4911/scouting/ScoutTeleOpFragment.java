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

import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttemptTeleop;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTeleOpFragment extends Fragment {

    CheckBox playedDefence;

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

    View.OnClickListener saveTeleop = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                    .getTeleopPeriod().setPlayedDefense(playedDefence.isChecked());

            // TODO: Find a cleaner way to do this
            if (((ScoutMatchActivity) getActivity())
                    .getScoutingData()
                    .getMatchData()
                    .getTeleopPeriod().getGearAttempts().size() == 0) {
                GearAttemptTeleop attempt = new GearAttemptTeleop();

                ((ScoutMatchActivity) getActivity())
                            .getScoutingData()
                            .getMatchData().getTeleopPeriod().getGearAttempts().add(attempt);
            }

            if (((ScoutMatchActivity) getActivity())
                    .getScoutingData()
                    .getMatchData()
                    .getTeleopPeriod().getShotAttempts().size() == 0) {
                ShotAttemptTeleop attempt = new ShotAttemptTeleop();

                ((ScoutMatchActivity) getActivity())
                        .getScoutingData()
                        .getMatchData().getTeleopPeriod().getShotAttempts().add(attempt);
            }

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(3);
        }
    };
}
