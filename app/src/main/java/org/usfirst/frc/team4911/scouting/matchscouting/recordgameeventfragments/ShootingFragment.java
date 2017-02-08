package org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.ShotEvent;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShootingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShootingFragment extends Fragment {

    private static final String SHOT_EVENT = "shotEvent";

    private ShotEvent shotEvent;

    public ShootingFragment() {
        // Required empty public constructor
    }

    public static ShootingFragment newInstance() {
        return new ShootingFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_shooting, container, false);
    }
}
