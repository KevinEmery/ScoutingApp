package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventactivity.GameEventMenuFragment;

public class ScoutAutoFragment extends Fragment {
    private static final String ARG_PARAM1 = "matchData";

    Button buttonEndMatch;

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

        buttonEndMatch = (Button) view.findViewById(R.id.buttonEndMatch);
        buttonEndMatch.setOnClickListener(handleFinishMatchClick);

        return view;
    }

    private View.OnClickListener handleFinishMatchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
            fragmentChangeListener.replaceFragment(FinishMatchFragment.newInstance(), true);
        }
    };

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
            fragmentChangeListener.replaceFragment(GameEventMenuFragment.newInstance(), true);
            return true;
        }
    };
}
