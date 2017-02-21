package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTeleOpFragment extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in OnCreateView of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_tele_op, container, false);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        RecordShootingAttemptFragment shootingEventFragment =
                RecordShootingAttemptFragment.newInstance();
        RecordGearAttemptFragment recordGearAttemptFragment = RecordGearAttemptFragment.newInstance();

        fragmentTransaction.add(R.id.teleop_shooting_fragment_container, shootingEventFragment);
        fragmentTransaction.add(R.id.teleop_gear_fragment_container, recordGearAttemptFragment);
        fragmentTransaction.commit();

        return view;
    }
}
