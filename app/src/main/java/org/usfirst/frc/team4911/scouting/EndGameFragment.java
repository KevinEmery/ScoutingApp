package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView locationMessage;
    CheckBox attempted;
    CheckBox succeeded;

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
        attempted = (CheckBox) view.findViewById(R.id.checkbox_end_game_attempt);
        succeeded = (CheckBox) view.findViewById(R.id.checkbox_end_game_success);

        Button location = (Button) view.findViewById(R.id.btn_climbing_location);
        location.setOnClickListener(recordLocation);

        Button saveToFile = (Button) view.findViewById(R.id.button_end_game_save_data_to_file);
        saveToFile.setOnClickListener(saveDataToFile);

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
    private View.OnClickListener saveDataToFile = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            saveAndClearEndGameData();

            ScoutingData scoutingData = ((ScoutMatchActivity) getActivity()).getScoutingData();

            String fileName = String.format(Locale.getDefault(),
                "%1$s_%2$d_%3$s_%4$s_%5$d_%6$s_%7$s_%8$s.json",
                scoutingData.getEventCode(),
                scoutingData.getMatchNumber(),
                scoutingData.getTournamentLevel(),
                scoutingData.getStation(),
                scoutingData.getTeamNumber(),
                scoutingData.getScoutName(),
                scoutingData.getScoutingTeamName(),
                scoutingData.getDeviceId());

            Gson gson = new GsonBuilder().create();
            String serialisedScoutingData = gson.toJson(scoutingData);
            SaveDataToFile(v, fileName, serialisedScoutingData);
        }
    };

    private void saveAndClearEndGameData() {
        // Save the data
        ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getEndGame()
                .setAttempted(attempted.isChecked());

        ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getEndGame()
                .setSucceeded(succeeded.isChecked());

        // and clear it
        attempted.setChecked(false);
        succeeded.setChecked(false);
        String message = "Location: ";
        locationMessage.setText(message);
    }

    /** Actually does the work of saving the matchdata object to a file */
    private void SaveDataToFile(View view, String fileName, String data) {
        CharSequence text;

        if (this.isExternalStorageWritable()) {
            try {
                File directory = getScoutingDataStorageDir();
                File dataFileHandle = new File(directory, fileName);

                FileOutputStream outputStream = new FileOutputStream(dataFileHandle);
                outputStream.write(data.getBytes());
                outputStream.close();

                text = "File written: " + dataFileHandle.getPath();
            } catch (IOException e) {
                text = "unable to write file because of an exception: " + e.toString();
            }
        }
        else {
            text = "External storage not writeable";
        }

        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Gets the directory where the scouting app will store its output files */
    public static File getScoutingDataStorageDir() {
        File directoryPath = new File(Environment.getExternalStorageDirectory(),
                "ScoutingData");

        if (!directoryPath.exists()) {
            if (!directoryPath.mkdirs()) {
                // Figure out something to do here someday
            }
        }

        return directoryPath;
    }
}
