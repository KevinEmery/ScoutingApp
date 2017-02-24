package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;

/**
 * Code for the fragment which handles recording gear events.
 * Use the {@link RecordGearAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordGearAttemptFragment extends Fragment implements OnRecordLocationEventListener {

    GearPegPosition gearPegPosition;
    private TextView locationMessage;
    private TextView countMessage;
    private CheckBox placedGear;

    public RecordGearAttemptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordGearAttemptFragment.
     */
    public static RecordGearAttemptFragment newInstance() {
        return new RecordGearAttemptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_gear_attempt, container, false);

        locationMessage = (TextView) view.findViewById(R.id.txt_gear_record_auto_location);
        countMessage = (TextView) view.findViewById(R.id.text_view_record_gear_count);
        placedGear = (CheckBox) view.findViewById(R.id.checkbox_record_gear_success);

        Button location = (Button) view.findViewById(R.id.btn_gear_record_location);
        location.setOnClickListener(recordLocation);

        Button save = (Button) view.findViewById(R.id.btn_gear_record_save);
        save.setOnClickListener(saveGearAttempt);

        // Add an onclick listener for the location button

        return view;
    }

    // This method gets called by the record location dialog fragment
    @Override
    public void OnRecordLocationEvent(Object locationObject) {

        GearPegPosition position = (GearPegPosition) locationObject;
        String message = "Location: " + position;
        locationMessage.setText(message);
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a gear event.
     */
    private View.OnClickListener recordLocation = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment =
                    RecordLocationFragment.newInstance(
                            ((ScoutMatchActivity) getActivity()).getAlliance(),
                            EventLocationType.PLACEGEAR);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    /**
     * OnClickListener for the save button that records the current gear event.
     */
    private View.OnClickListener saveGearAttempt = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            GearAttempt gearAttempt = new GearAttempt();
            GearResult result = (placedGear.isChecked()) ? GearResult.Success : GearResult.Failed;

            gearAttempt.setGearResult(result);
            gearAttempt.setGearPegPosition(gearPegPosition);

            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getAutonomousPeriod()
                    .getGearAttempts().add(gearAttempt);

            String message = "Attempts recorded: " + ((ScoutMatchActivity) getActivity())
                    .getScoutingData().getMatchData().getAutonomousPeriod().getGearAttempts().size();
            countMessage.setText(message);

            restoreDefaults();
        }
    };

    /**
     * Clears the current gearResult object and restores all the appropriate defaults.
     */
    private void restoreDefaults() {
        String message = "Location: ";
        locationMessage.setText(message);
        placedGear.setChecked(false);
    }

}
