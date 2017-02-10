package org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.ScoutMatchActivity;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.ClimbingEvent;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link ClimbingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClimbingFragment extends DialogFragment {

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
        getDialog().setTitle("Climbing");

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
                    climbingEvent.setSucceeded(true);
                    climbingEvent.logSucceededTime();
                }
            }
        });

        buttonReturnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // Copy over the button state to the click event. Note that the succeeded will already
            // have taken care of itself
            climbingEvent.setAttempted(chkAttempted.isChecked());

            // Add the event to the list of events
            ((ScoutMatchActivity)getActivity()).matchData.addAutoMatchEvent(climbingEvent);

            // Close the fragment
            dismiss();
            }
        });

        return view;
    }


}
