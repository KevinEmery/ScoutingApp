package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments.GameEventMenuFragment;

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
            fragmentChangeListener.replaceFragment(FinishMatchFragment.newInstance());
        }
    };

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            // Fix for the fact that this was getting called multiple times as describedc here:
            // https://stackoverflow.com/questions/8182513/ontouch-event-of-ontouchlistener-gets-called-twice-in-android
            if (event.getAction() == MotionEvent.ACTION_UP) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                GameEventMenuFragment fragment = GameEventMenuFragment.newInstance();
                fragment.show(fm, "Dialog Fragment");
            }

            return true;
        }
    };
}
