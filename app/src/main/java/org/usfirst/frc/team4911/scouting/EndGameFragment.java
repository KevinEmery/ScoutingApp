package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
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

import org.usfirst.frc.team4911.scouting.datamodel.EndGame;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndGameFragment extends Fragment implements
        RecordLocationFragment.OnRecordLocationMapTouchListener,
        RecordLocationFragment.OnLocationDoneButtonClickListener {
    OnSaveAndClearClickedListener mListener;

    TextView locationMessage;
    ToggleButton toggleButton_attempted;
    ToggleButton toggleButton_noattempt;
    ToggleButton toggleButton_Succeeded;
    ToggleButton toggleButton_Failed;
    TouchPadPosition climbPosition = TouchPadPosition.None;

    long climbStartedTimeMs = 0;
    long climbEndedTimeMs = 0;


    public EndGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EndGameFragment.
     */
    public static EndGameFragment newInstance() {
        return new EndGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);

        locationMessage = (TextView) view.findViewById(R.id.text_view_end_game_location);
        toggleButton_attempted = (ToggleButton) view.findViewById(R.id.togglebutton_end_game_attempt);
        toggleButton_noattempt = (ToggleButton) view.findViewById(R.id.togglebutton_end_game_noattempt);
        toggleButton_Succeeded = (ToggleButton) view.findViewById(R.id.togglebutton_end_game_success);
        toggleButton_Failed = (ToggleButton) view.findViewById(R.id.togglebutton_end_game_failed);

        toggleButton_attempted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_noattempt.isChecked()) {
                    toggleButton_noattempt.setChecked(false);
                }

                if (!isChecked)
                {
                    toggleButton_Succeeded.setChecked(false);
                    toggleButton_Failed.setChecked(false);
                }
            }
        });

        toggleButton_noattempt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_attempted.isChecked()) {
                    toggleButton_attempted.setChecked(false);
                }

                if (isChecked)
                {
                    toggleButton_Succeeded.setChecked(false);
                    toggleButton_Failed.setChecked(false);
                }
            }
        });

        toggleButton_Succeeded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_Failed.isChecked()) {
                    toggleButton_Failed.setChecked(false);
                }

                if (isChecked) {
                    toggleButton_attempted.setChecked(true);
                }
            }
        });

        toggleButton_Failed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && toggleButton_Succeeded.isChecked()) {
                    toggleButton_Succeeded.setChecked(false);
                }

                if (isChecked) {
                    toggleButton_attempted.setChecked(true);
                }
            }
        });

        Button location = (Button) view.findViewById(R.id.btn_climbing_location);
        location.setOnClickListener(recordLocation);

        Button saveToFile = (Button) view.findViewById(R.id.button_end_game_save_data_to_file);
        saveToFile.setOnClickListener(saveAndEndGame);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSaveAndClearClickedListener) {
            mListener = (OnSaveAndClearClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSaveAndClearClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Handles touch events on the location map.
     */
    @Override
    public void onRecordLocationMapTouch(Pair<Float, Float> normalisedTouchPoint) {

        SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");
        boolean isBlueAlliance = (driveStation.toLowerCase().contains("blue"));

        climbPosition = LocationMappingHelpers.GetTouchPadPosition(normalisedTouchPoint,
                isBlueAlliance);

        String message;

        if (climbPosition == TouchPadPosition.None) {
            message = "Please select a touchpad";
        } else {
            message = "Bot climbed at touchpad " + climbPosition.toString();
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Handles presses of the 'done' button on the record location dialog.
     * @return True if we're cool with the dialog being closed, false otherwise.
     */
    @Override
    public boolean onLocationDoneButtonClick() {
        return climbPosition != TouchPadPosition.None;
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
                    R.drawable.touchpad_locations_red : R.drawable.touchpad_locations_blue;

            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(resourceIdOfMapToDraw);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a gear event.
     */
    private View.OnClickListener saveAndEndGame = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            EndGame endGame = new EndGame();

            endGame.setAttempted(toggleButton_attempted.isChecked());
            endGame.setSucceeded(toggleButton_Succeeded.isChecked());
            endGame.setTimeInSeconds((int)(climbStartedTimeMs - climbEndedTimeMs)/1000);
            endGame.setTouchPadPosition(climbPosition);

            if (mListener != null) {
                mListener.onSaveAndClearClicked(endGame);
            }

            clearEndGameData();
        }
    };

    /**
     * Clears the end-game data
     */
    private void clearEndGameData() {
        toggleButton_attempted.setChecked(false);
        toggleButton_Succeeded.setChecked(false);
        String message = "Location: ";
        locationMessage.setText(message);
    }

    /**
     * Passes the end-game data object created when the start match button is clicked up to
     * whoever's listening.
     */
    public interface OnSaveAndClearClickedListener {
        void onSaveAndClearClicked(EndGame endGame);
    }
}
