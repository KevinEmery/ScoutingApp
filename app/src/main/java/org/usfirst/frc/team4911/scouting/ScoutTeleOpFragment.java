package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

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

        RecordShotAttemptTeleOpFragment shotAttemptFragment = RecordShotAttemptTeleOpFragment.newInstance();
        RecordGearAttemptTeleOpFragment gearAttemptFragment = RecordGearAttemptTeleOpFragment.newInstance();

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.teleop_shooting_fragment_container, shotAttemptFragment);
        fragmentTransaction.add(R.id.teleop_gear_fragment_container, gearAttemptFragment);
        fragmentTransaction.commit();
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
        }
    };
}
