package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments.AutoGameEventMenuFragment;
import org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments.TeleOpGameEventMenuFragment;

public class FieldFragment extends Fragment {

    public FieldFragment() {
        // Required empty public constructor
    }

    public static FieldFragment newInstance() {
        return new FieldFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_field, container, false);
        view.setOnTouchListener(handleTouch);

        return view;
    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                MatchState currentState = ((ScoutMatchActivity) getActivity()).matchState;

                if (currentState == MatchState.AUTO) {
                    AutoGameEventMenuFragment autoGameEventMenuFragment
                            = AutoGameEventMenuFragment.newInstance();
                    autoGameEventMenuFragment.show(fm, "Dialog Fragment");
                }
                else if (currentState == MatchState.TELEOP) {
                    TeleOpGameEventMenuFragment teleOpGameEventMenuFragment =
                            TeleOpGameEventMenuFragment.newInstance();
                    teleOpGameEventMenuFragment.show(fm, "Dialog Fragment");
                }
                else {
                    return true;
                }
            }

            return true;
        }
    };
}
