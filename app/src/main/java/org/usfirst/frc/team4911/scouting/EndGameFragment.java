package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.EndGame;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndGameFragment extends Fragment
        implements RecordLocationFragment.OnRecordLocationMapTouchListener {

    OnSaveAndClearClickedListener mListener;

    TextView locationMessage;
    CheckBox checkBox_attempted;
    CheckBox checkBox_noattempt;
    CheckBox checkBox_Succeeded;
    CheckBox checkBox_Failed;
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
        checkBox_attempted = (CheckBox) view.findViewById(R.id.checkbox_end_game_attempt);
        checkBox_noattempt = (CheckBox) view.findViewById(R.id.checkbox_end_game_noattempt);
        checkBox_Succeeded = (CheckBox) view.findViewById(R.id.checkbox_end_game_success);
        checkBox_Failed = (CheckBox) view.findViewById(R.id.checkbox_end_game_failed);

        checkBox_Succeeded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && checkBox_Failed.isChecked()) {
                    checkBox_Failed.setChecked(!isChecked);
                }

                climbEndedTimeMs = System.currentTimeMillis();
            }
        });

        checkBox_Failed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && checkBox_Succeeded.isChecked()) {
                    checkBox_Succeeded.setChecked(!isChecked);
                }
            }
        });

        checkBox_attempted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && checkBox_noattempt.isChecked()) {
                    checkBox_noattempt.setChecked(!isChecked);
                }

                climbStartedTimeMs = System.currentTimeMillis();
            }
        });

        checkBox_noattempt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && checkBox_attempted.isChecked()) {
                    checkBox_attempted.setChecked(!isChecked);
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
                    + " must implement OnFragmentInteractionListener");
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
    public void onRecordLocationMapTouch(MotionEvent event) {
        //TODO: Hi Scott! This is where the code that handles touch events should go. Right now all
        // it does is show a toast containing the X and Y coordinates of the touch point.
        // I leave the mapping in your hands :)
        String text = "X: " + event.getX() + "Y: " + event.getY();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
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
                    R.drawable.airship_red : R.drawable.airship_blue;

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

            endGame.setAttempted(checkBox_attempted.isChecked());
            endGame.setSucceeded(checkBox_Succeeded.isChecked());
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
        checkBox_attempted.setChecked(false);
        checkBox_Succeeded.setChecked(false);
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
