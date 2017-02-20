package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutAutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutAutoFragment extends Fragment {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreMatchFragment
        View view = inflater.inflate(R.layout.fragment_scout_auto, container, false);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        RecordShootingEventFragment shootingEventFragment =
                RecordShootingEventFragment.newInstance();
        RecordGearEventFragment recordGearEventFragment = RecordGearEventFragment.newInstance();

        fragmentTransaction.add(R.id.auto_shooting_fragment_container, shootingEventFragment);
        fragmentTransaction.add(R.id.auto_gear_fragment_container, recordGearEventFragment);
        fragmentTransaction.commit();

        return view;
    }
}
