package com.example.angwy.scoutingapp.matchscouting.recordgameeventactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.angwy.scoutingapp.R;
import com.example.angwy.scoutingapp.matchscouting.FragmentChangeListener;
import com.example.angwy.scoutingapp.matchscouting.ScoutAutoFragment;
import com.example.angwy.scoutingapp.matchscouting.ScoutMatchActivity;
import com.example.angwy.scoutingapp.matchscouting.datamodel.ClimbingEvent;

import java.util.Calendar;
import java.util.Date;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClimbingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClimbingFragment extends Fragment {

    Button buttonReturnToGame;
    CheckBox chkAttempted;
    CheckBox chkSucceeded;

    ClimbingEvent climbingEvent;

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
        View view = inflater.inflate(R.layout.fragment_climbing, container, false);

        buttonReturnToGame = (Button) view.findViewById(R.id.buttonReturnToGame);
        chkAttempted = (CheckBox) view.findViewById(R.id.chkClimbingAttempted);
        chkSucceeded = (CheckBox) view.findViewById(R.id.chkClimbingSucceeded);
        climbingEvent = new ClimbingEvent();

        // This is so we can log the time at which the climb succeeded and thus work out how long
        // it took.
        chkSucceeded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkSucceeded.isChecked()) {
                    climbingEvent.succeeded = true;
                    climbingEvent.succeededTime =
                            java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                                    .getTime());
                }
            }
        });

        buttonReturnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // Copy over the button state to the click event. Note that the succeeded will already
            // have taken care of itself.
            climbingEvent.attempted = chkAttempted.isChecked();

            // Add the event to the list of events
            ((ScoutMatchActivity)getActivity()).matchData.autoMatchEvents.add(climbingEvent);

            // Go back to scout auto
            FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
            fragmentChangeListener.replaceFragment(ScoutAutoFragment.newInstance());
            }
        });

        return view;
    }


}
