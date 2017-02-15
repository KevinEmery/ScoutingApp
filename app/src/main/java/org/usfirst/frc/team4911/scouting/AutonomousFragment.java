package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AutonomousFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutonomousFragment extends Fragment {
    public AutonomousFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AutonomousFragment.
     */
    public static AutonomousFragment newInstance() {
        return new AutonomousFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreMatchFragment
        return inflater.inflate(R.layout.fragment_autonomous, container, false);
    }
}
