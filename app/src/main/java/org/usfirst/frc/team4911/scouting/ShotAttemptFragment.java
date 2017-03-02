package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.ShotMode;

/**
 * A simple {@link Fragment} subclass.
 * Contains all data interfaces necessary to collect information about a shooting event.
 * Use the {@link ShotAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 *  Activities that contain this fragment must implement the
 * {@link ShotAttemptFragment.OnShotAttemptCreatedListener} interface
 * to handle interaction events.
 */
public class ShotAttemptFragment extends Fragment implements
        RecordLocationFragment.OnRecordLocationMapTouchListener,
        RecordLocationFragment.OnLocationDoneButtonClickListener {
    private OnShotAttemptCreatedListener mListener;

    private ShotAttempt shotAttempt;
    private String shotLocation = "";

    private ToggleButton toggleButton_shotLow;
    private ToggleButton toggleButton_shotHigh;
    private SeekBar seekBar_shotsMade;
    private TextView textView_shotsMade;
    private SeekBar seekBar_shotsMissed;
    private TextView textView_shotsMissed;
    private TextView locationMessage;

    // Parameters for the chronometer
    private Chronometer chronometerShotTime;
    private Button buttonStartStopChronometer;
    private long shotTimeMilliseconds = 0;
    private boolean isTiming = false;
    private long startTimeMs;

    public ShotAttemptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment ShotAttemptFragment.
     */
    public static ShotAttemptFragment newInstance() {
        return new ShotAttemptFragment();
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
            mListener = (OnShotAttemptCreatedListener)fragment;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnShotAttemptCreatedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shot_attempt, container, false);

        shotAttempt = new ShotAttempt();

        toggleButton_shotLow = (ToggleButton) view.findViewById(R.id.togglebutton_shot_low);
        toggleButton_shotHigh = (ToggleButton) view.findViewById(R.id.togglebutton_shot_high);

        locationMessage = (TextView) view.findViewById(R.id.text_shot_attempt_location);

        seekBar_shotsMade = (SeekBar) view.findViewById(R.id.seekbar_shots_made);
        seekBar_shotsMade.setOnSeekBarChangeListener(shotsMadeSeekBarListener);
        textView_shotsMade = (TextView) view.findViewById(R.id.textview_shotsmade);
        textView_shotsMade.setText("0");

        seekBar_shotsMissed = (SeekBar) view.findViewById(R.id.seekbar_shots_missed);
        seekBar_shotsMissed.setOnSeekBarChangeListener(shotsMissedSeekBarListener);
        textView_shotsMissed = (TextView) view.findViewById(R.id.textview_shotsmissed);
        textView_shotsMissed.setText("0");

        // Setup for the shot time counter
        chronometerShotTime = (Chronometer) view.findViewById(R.id.chronometer_shot_attempt_time);
        buttonStartStopChronometer = (Button) view.findViewById(R.id.button_shot_attempt_startstop);
        buttonStartStopChronometer.setOnClickListener(startStopButtonListener);

        toggleButton_shotLow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_shotHigh.isChecked()) {
                    toggleButton_shotHigh.setChecked(false);
                }
            }
        });

        toggleButton_shotHigh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_shotLow.isChecked()) {
                    toggleButton_shotLow.setChecked(false);
                }
            }
        });

        Button location = (Button) view.findViewById(R.id.button_shot_attempt_location);
        location.setOnClickListener(recordLocation);

        Button save = (Button) view.findViewById(R.id.button_shot_attempt_save);
        save.setOnClickListener(saveShotAttempt);

        return view;
    }

    /**
     * Handles touch events on the location map.
     */
    @Override
    public void onRecordLocationMapTouch(MotionEvent event) {
        SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");

        boolean isBlueAlliance = (driveStation.toLowerCase().contains("blue"));

        shotLocation = LocationMappingHelpers.GetShootingPosition((int) event.getX(),
                (int) event.getY(), isBlueAlliance);

        String message;

        if (LocationMappingHelpers.OUT_OF_BOUNDS.equals(shotLocation)) {
            message = "Please select a point in the shooting area";
        } else {
            message = "Shot location " + shotLocation;
            String locationText = getString(R.string.label_location) + shotLocation;
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
        return !LocationMappingHelpers.OUT_OF_BOUNDS.equals(shotLocation);
    }

    /**
     * OnChangeListener for the shots made seekbar.
     */
    private SeekBar.OnSeekBarChangeListener shotsMadeSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            textView_shotsMade.setText(String.valueOf(seekBar.getProgress()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener shotsMissedSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            textView_shotsMissed.setText(String.valueOf(seekBar.getProgress()));
         }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    /**
     * Interaction listener for the chronometer button.
     */
    private View.OnClickListener startStopButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isTiming) {
                startShotTimer();
            } else {
                stopShotTimer();
            }
        }
    };

    /**
     * Starts the shot timer. I have this sneaky feeling that this logic should live in some kind
     * of custom view or something like that.
     */
    private void startShotTimer() {
        startTimeMs = System.currentTimeMillis();
        chronometerShotTime.setBase(SystemClock.elapsedRealtime());
        chronometerShotTime.start();
        buttonStartStopChronometer.setText("Stop");
        isTiming = true;
    }

    /**
     * Stops the shot timer. Again, is this something that should be a custom view that I see
     * before me.
     */
    private void stopShotTimer() {
        shotTimeMilliseconds = System.currentTimeMillis() - startTimeMs;
        chronometerShotTime.setBase(SystemClock.elapsedRealtime());
        chronometerShotTime.stop();
        buttonStartStopChronometer.setText("Start");
        isTiming = false;
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a shooting event.
     */
    private View.OnClickListener recordLocation = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                    .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

            String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");

            int resourceIdOfMapToDraw = (driveStation.toLowerCase().contains("red")) ?
                    R.drawable.shootingzone_red : R.drawable.shootingzone_blue;

            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(resourceIdOfMapToDraw);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    /**
     * OnTouchListener for the save button.
     */
    private View.OnClickListener saveShotAttempt = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Scrape the data model and restore all defaults
            int shotsMade = seekBar_shotsMade.getProgress();
            int shotsMissed = seekBar_shotsMissed.getProgress();
            boolean shotHigh = toggleButton_shotHigh.isChecked();

            boolean hasHighOrLowShots = toggleButton_shotHigh.isChecked() || toggleButton_shotLow.isChecked();
            boolean hasShots = shotsMade + shotsMissed > 0;
            boolean hasLocation = !shotLocation.equals("");

            // Here to handle the case where the user presses save without stopping the timer first.
            if (isTiming) {
                stopShotTimer();
            }

            int durationSeconds = (int) shotTimeMilliseconds / 1000;

            if (!(hasShots && hasHighOrLowShots && hasLocation)) {

                if (!hasHighOrLowShots) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                    dlgAlert.setMessage("Please record if shots where HIGH or LOW.");
                    dlgAlert.setTitle("Missing Fuel Target");
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

                if (!hasShots) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                    dlgAlert.setMessage("Please record the number of shots made and missed.");
                    dlgAlert.setTitle("Missing Shot Counts");
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

                if (!hasLocation) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                    dlgAlert.setMessage("Please record the shooting location.");
                    dlgAlert.setTitle("Missing Shot Location");
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
            }

            shotAttempt.setShotLocation(shotLocation);
            shotAttempt.setShotsMade(shotsMade);
            shotAttempt.setShotsMissed(shotsMissed);
            shotAttempt.setShotDurationInSeconds(durationSeconds);
            shotAttempt.setShotMode(shotHigh ? ShotMode.High : ShotMode.Low);

            // Pass the shot event up to whoever is listening for it
            if (mListener != null) {
                mListener.onShotAttemptCreated(shotAttempt);
            }


            restoreDefaults();
        }
    };

    /**
     * Restores the default values of all fields
     */
    private void restoreDefaults() {
        shotAttempt = new ShotAttempt();
        locationMessage.setText(getString(R.string.label_location));
        seekBar_shotsMade.setProgress(0);
        seekBar_shotsMissed.setProgress(0);
        textView_shotsMade.setText("");
        textView_shotsMissed.setText("");
    }

    /**
     * Callback method invoked in the parent activity or fragment when a new shot attempt is
     * created.
     */
    public interface OnShotAttemptCreatedListener {
        void onShotAttemptCreated(ShotAttempt shotAttempt);
    }
}
