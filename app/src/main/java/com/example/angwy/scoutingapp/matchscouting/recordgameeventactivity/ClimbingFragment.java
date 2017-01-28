package com.example.angwy.scoutingapp.matchscouting.recordgameeventactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angwy.scoutingapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClimbingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClimbingFragment extends Fragment {

    public ClimbingFragment() {
        // Required empty public constructor
    }

    public static ClimbingFragment newInstance() {
        return new ClimbingFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_climbing, container, false);
    }
}
