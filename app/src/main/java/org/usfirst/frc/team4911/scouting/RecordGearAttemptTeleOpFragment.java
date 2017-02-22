package org.usfirst.frc.team4911.scouting;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordGearAttemptTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordGearAttemptTeleOpFragment extends Fragment
        implements OnRecordLocationEventListener{
    private Spinner gearResult;
    private CheckBox wasDefended;
    private TextView locationMessage;
    private GearPegPosition location;

    public RecordGearAttemptTeleOpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordGearAttemptTeleOpFragment.
     */
    public static RecordGearAttemptTeleOpFragment newInstance() {
        return new RecordGearAttemptTeleOpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_gear_attempt_tele_op, container, false);

        gearResult = (Spinner) view.findViewById(R.id.spinner_gear_attempt_telop_result);
        gearResult.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, GearResult.values()));

        wasDefended = (CheckBox) view.findViewById(R.id.checkbox_gear_attempt_tele_op_defended);
        locationMessage = (TextView) view.findViewById(R.id.text_gear_attempt_tele_op_location);

        Button location = (Button) view.findViewById(R.id.button_gear_attempt_tele_op_location);
        location.setOnClickListener(recordLocation);

        Button save = (Button) view.findViewById(R.id.button_gear_attempt_tele_op_save);
        save.setOnClickListener(saveGearResult);

        return view;
    }

    @Override
    public void OnRecordLocationEvent(Object locationObject) {
        location = (GearPegPosition) locationObject;
        String message = "Location: " + location;
        locationMessage.setText(message);
    }

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

    private View.OnClickListener saveGearResult = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            GearAttemptTeleop gearAttemptTeleop = new GearAttemptTeleop();
            gearAttemptTeleop.setGearPegPosition(location);
            gearAttemptTeleop.setWasDefended(wasDefended.isChecked());
            gearAttemptTeleop.setGearResult(
                    GearResult.valueOf(gearResult.getSelectedItem().toString()));

            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getTeleopPeriod()
                    .addGearAttempt(gearAttemptTeleop);
            restoreDefaults();
        }
    };

    private void restoreDefaults() {
        wasDefended.setChecked(false);
        location = GearPegPosition.None;
        String message = "Location: ";
        locationMessage.setText(message);
    }
}
