package org.usfirst.frc.team4911.scouting.matchscouting;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.DefensiveRating;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The last of the fragments in the match scouting cycle.
 * Collects the few pieces of subjective data and does the file write.
 * Use the {@link FinishMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishMatchFragment extends Fragment {

    private static final String LOG_TAG = "FinishMatchFragment";

    Spinner spinnerDefenceRating;
    Spinner spinnerRole;
    Button buttonSave;

    public FinishMatchFragment() {
        // Required empty public constructor
    }

    public static FinishMatchFragment newInstance() {
        return new FinishMatchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finish_match, container, false);

        spinnerDefenceRating = (Spinner) view.findViewById(R.id.spinnerDefenceRating);
        spinnerDefenceRating.setAdapter(
                new ArrayAdapter<>(
                        view.getContext(), android.R.layout.simple_list_item_1, DefensiveRating.values()));

        spinnerRole = (Spinner) view.findViewById(R.id.spinnerRole);
        spinnerRole.setAdapter(
                new ArrayAdapter<>(
                        view.getContext(), android.R.layout.simple_list_item_1, Role.values()));

        buttonSave = (Button) view.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Serialise the matchdata class and save it to file
                Gson gson = new GsonBuilder().create();
                String matchDataAsString = gson.toJson(((ScoutMatchActivity)getActivity()).matchData);
                SaveDataToFile(v, matchDataAsString);

                // Go back to the data entry screen
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
                fragmentChangeListener.replaceFragment(CollectMetadataFragment.newInstance(), true);
            }
        });

        return view;
    }

    /** Actually does the work of saving the matchdata object to a file */
    private void SaveDataToFile(View view, String data) {
        Context context = view.getContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;

        if (this.isExternalStorageWritable())
        {
            try {
                File directory = this.getScoutingDataStorageDir();
                File dataFileHandle = new File(directory, "scoutingdata.json");

                FileOutputStream outputStream = new FileOutputStream(dataFileHandle);
                outputStream.write(data.getBytes());
                outputStream.close();

                text = "File written: " + dataFileHandle.getPath();
            } catch (IOException e) {
                Log.e(LOG_TAG, e.toString());
                text = "unable to write file because of an exception: " + e.toString();
            }
        }
        else
        {
            text = "External storage not writeable";
        }

        Toast toast = Toast.makeText(context, text, duration);
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
                Log.e(LOG_TAG, "Directory not created");
            }
        }

        return directoryPath;
    }
}
