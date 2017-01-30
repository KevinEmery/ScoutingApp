package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.usfirst.frc.team4911.scouting.R;

/**
 * Scouts teleop. Basically identical to scout auto except data goes into a different list.
 */
public class ScoutTeleOpFragment extends Fragment {


    public ScoutTeleOpFragment() {
        // Required empty public constructor
    }

    public static ScoutTeleOpFragment newInstance() {
        return new ScoutTeleOpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scout_tele_op, container, false);
    }
}
