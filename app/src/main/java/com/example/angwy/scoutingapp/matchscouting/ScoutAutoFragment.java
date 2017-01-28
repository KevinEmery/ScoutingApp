package com.example.angwy.scoutingapp.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.angwy.scoutingapp.R;

public class ScoutAutoFragment extends Fragment {
    private static final String ARG_PARAM1 = "matchData";

    public ScoutAutoFragment() {
        // Required empty public constructor
    }

    public static ScoutAutoFragment newInstance() {
        return new ScoutAutoFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scout_auto, container, false);
        view.setOnTouchListener(handleTouch);
        return view;
    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // We call the data collection fragment
            return true;
        }
    };
}
