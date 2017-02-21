package org.usfirst.frc.team4911.scouting;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;

/**
 * A simple {@link Fragment} subclass.
 * Contains all data interfaces necessary to collect information about a shooting event.
 * Intended for use in auto and teleop scouting events.
 * Use the {@link RecordShotAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordShotAttemptFragment extends DialogFragment
        implements OnRecordLocationEventListener{
    ShotAttempt shotAttempt;
    private TextView locationMessage;

    public RecordShotAttemptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment RecordShotAttemptFragment.
     */
    public static RecordShotAttemptFragment newInstance() {
        return new RecordShotAttemptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_shot_attempt, container, false);

        shotAttempt = new ShotAttempt();
        locationMessage = (TextView) view.findViewById(R.id.text_shot_attempt_location);

        Button location = (Button) view.findViewById(R.id.button_shot_attempt_location);
        location.setOnClickListener(handleBtnPress);

        return view;
    }

    // This method gets called by the record location dialog fragment
    @Override
    public void OnRecordLocationEvent(Object locationObject) {
        String position = (String) locationObject;
        shotAttempt.setShotLocation(position);
        String message = "Location: " + position;
        locationMessage.setText(message);
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a shooting event.
     */
    private View.OnClickListener handleBtnPress = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(
                    ((ScoutMatchActivity) getActivity()).getAlliance(),
                    EventLocationType.SHOOT);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };
}
