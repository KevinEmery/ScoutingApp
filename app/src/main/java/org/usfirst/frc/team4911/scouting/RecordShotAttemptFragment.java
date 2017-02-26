package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.ShotMode;

/**
 * A simple {@link Fragment} subclass.
 * Contains all data interfaces necessary to collect information about a shooting event.
 * Use the {@link RecordShotAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 *  Activities that contain this fragment must implement the
 * {@link RecordShotAttemptFragment.OnShotAttemptCreatedListener} interface
 * to handle interaction events.
 */
public class RecordShotAttemptFragment extends Fragment
        implements RecordLocationFragment.OnRecordLocationMapTouchListener {
    private OnShotAttemptCreatedListener mListener;

    private ShotAttempt shotAttempt;

    private SeekBar seekBar_shotsMade;
    private TextView textView_shotsMade;
    private SeekBar seekBar_shotsMissed;
    private TextView textView_shotsMissed;
    private Spinner spinnerShotMode;
    private TextView locationMessage;

    // Parameters for the chronometer
    private Chronometer chronometerShotTime;
    private Button buttonStartStopChronometer;
    private long shotTimeMilliseconds = 0;
    private boolean isTiming = false;
    private long startTimeMs;

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
        onAttachToParentFragment(getParentFragment());
    }

    public void onAttachToParentFragment(Fragment fragment)
    {
        try
        {
            mListener = (OnShotAttemptCreatedListener)fragment;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_shot_attempt, container, false);

        shotAttempt = new ShotAttempt();
        locationMessage = (TextView) view.findViewById(R.id.text_shot_attempt_location);

        spinnerShotMode = (Spinner) view.findViewById(R.id.spinner_shot_attempt_mode);
        spinnerShotMode.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, ShotMode.values()));

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
        //TODO: Hi Scott! This is where the code that handles touch events should go. Right now all
        // it does is show a toast containing the X and Y coordinates of the touch point.
        // I leave the mapping in your hands :)
        String text = "X: " + event.getX() + "Y: " + event.getY();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
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
            ShotMode shotMode = ShotMode.valueOf(spinnerShotMode.getSelectedItem().toString());

            // Here to handle the case where the user presses save without stopping the timer first.
            if (isTiming) {
                stopShotTimer();
            }

            int durationSeconds = (int) shotTimeMilliseconds / 1000;

            shotAttempt.setShotsMade(shotsMade);
            shotAttempt.setShotsMissed(shotsMissed);
            shotAttempt.setShotMode(shotMode);
            shotAttempt.setShotDurationInSeconds(durationSeconds);

            // Pass the shot event up to whoever is listening for it
            mListener.onShotAttemptCreated(shotAttempt);
            restoreDefaults();
        }
    };

    /**
     * Restores the default values of all fields
     */
    private void restoreDefaults() {
        shotAttempt = new ShotAttempt();
        String message = "Location: ";
        locationMessage.setText(message);
    }

    /**
     * Callback method invoked in the parent activity or fragment when a new shot attempt is
     * created.
     */
    public interface OnShotAttemptCreatedListener {
        void onShotAttemptCreated(ShotAttempt shotAttempt);
    }
}
