package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;

/**
 * Code for the fragment which handles recording gear events.
 * Use the {@link GearAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GearAttemptFragment extends Fragment implements
        RecordLocationFragment.OnRecordLocationMapTouchListener,
        RecordLocationFragment.OnLocationDoneButtonClickListener {

    private OnGearAttemptCreatedListener mListener;

    GearPegPosition gearPegPosition;
    private ToggleButton toggleButton_gearAttempted;
    private ToggleButton toggleButton_gearNotAttempted;

    private ToggleButton togglebutton_gearSuccess;
    private ToggleButton togglebutton_gearFailed;

    private TextView locationMessage;

    public GearAttemptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GearAttemptFragment.
     */
    public static GearAttemptFragment newInstance() {
        return new GearAttemptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onAttachToParentFragment(getParentFragment());
    }

    private void onAttachToParentFragment(Fragment fragment)
    {
        try
        {
            mListener = (OnGearAttemptCreatedListener)fragment;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnGearAttemptCreatedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gear_attempt, container, false);

        locationMessage = (TextView) view.findViewById(R.id.txt_gear_record_auto_location);

        toggleButton_gearAttempted= (ToggleButton) view.findViewById(R.id.togglebutton_record_gear_attempt);
        toggleButton_gearNotAttempted = (ToggleButton) view.findViewById(R.id.togglebutton_record_gear_noattempt);
        togglebutton_gearSuccess = (ToggleButton) view.findViewById(R.id.togglebutton_record_gear_success);
        togglebutton_gearFailed = (ToggleButton) view.findViewById(R.id.togglebutton_record_gear_fail);

        toggleButton_gearAttempted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_gearNotAttempted.isChecked()) {
                    toggleButton_gearNotAttempted.setChecked(false);
                }

                if (!isChecked)
                {
                    togglebutton_gearSuccess.setChecked(false);
                    togglebutton_gearFailed.setChecked(false);
                }

            }
        });

        toggleButton_gearNotAttempted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_gearAttempted.isChecked()) {
                    toggleButton_gearAttempted.setChecked(false);
                }
                if (isChecked)
                {
                    togglebutton_gearSuccess.setChecked(false);
                    togglebutton_gearFailed.setChecked(false);
                }
            }
        });

        togglebutton_gearSuccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && togglebutton_gearFailed.isChecked()) {
                    togglebutton_gearFailed.setChecked(false);
                }

                if (isChecked) {
                    toggleButton_gearAttempted.setChecked(true);
                }
            }
        });

        togglebutton_gearFailed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && togglebutton_gearSuccess.isChecked()) {
                    togglebutton_gearSuccess.setChecked(false);
                }

                if (isChecked) {
                    toggleButton_gearAttempted.setChecked(true);
                }
            }
        });

        Button location = (Button) view.findViewById(R.id.btn_gear_record_location);
        location.setOnClickListener(recordLocation);

        Button save = (Button) view.findViewById(R.id.btn_gear_record_save);
        save.setOnClickListener(saveGearAttempt);

        // Add an onclick listener for the location button

        return view;
    }

    /**
     * Handles touch events on the location map.
     */
    @Override
    public void onRecordLocationMapTouch(MotionEvent event) {

        // Here we determine the alliance we're scouting for so we can display the correct map
        SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");

        boolean isBlueAlliance = (driveStation.toLowerCase().contains("blue"));

        gearPegPosition = LocationMappingHelpers.GetGearPegPosition((int) event.getX(),
                (int) event.getY(), isBlueAlliance);

        String message;

        if (gearPegPosition == GearPegPosition.None) {
            message = "Please select a valid gear peg position";
        } else {
            message = "Gear peg " + gearPegPosition.toString() + " selected.";
            String locationText = getString(R.string.label_location) + gearPegPosition.toString();
            locationMessage.setText(locationText);
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Handles presses of the 'done' button on the record location dialog.
     * @return True if we're cool with the dialog being closed, false otherwise.
     */
    @Override
    public boolean onLocationDoneButtonClick() {
        return gearPegPosition != GearPegPosition.None;
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a gear event.
     */
    private View.OnClickListener recordLocation = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Here we determine the alliance we're scouting for so we can display the correct map
            SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                    .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

            String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");

            int resourceIdOfMapToDraw = (driveStation.toLowerCase().contains("red")) ?
                    R.drawable.gear_locations_red : R.drawable.gear_locations_blue;

            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(resourceIdOfMapToDraw);
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
            GearResult result = (togglebutton_gearSuccess.isChecked()) ? GearResult.Success : GearResult.Failed;

            gearAttempt.setGearResult(result);
            gearAttempt.setGearPegPosition(gearPegPosition);

            if (!(toggleButton_gearAttempted.isChecked() || toggleButton_gearNotAttempted.isChecked())) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                dlgAlert.setMessage("No attempt to load gears was recorded.");
                dlgAlert.setTitle("Gears");
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return;
            }

            // Call the parent activity and pass it the gear attempt
            if (mListener != null) {
                mListener.onGearAttemptCreated(gearAttempt);
            }

            restoreDefaults();
        }
    };

    /**
     * Clears the current gearResult object and restores all the appropriate defaults.
     */
    private void restoreDefaults() {
        locationMessage.setText(getString(R.string.label_location));
        toggleButton_gearAttempted.setChecked(false);
        toggleButton_gearNotAttempted.setChecked(false);
        togglebutton_gearSuccess.setChecked(false);
        togglebutton_gearFailed.setChecked(false);
    }

    /**
     * All activities containing this fragment must implement this interface.
     */
    public interface OnGearAttemptCreatedListener {
        void onGearAttemptCreated(GearAttempt gearAttempt);
    }
}
