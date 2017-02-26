package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.DriveStation;
import org.usfirst.frc.team4911.scouting.datamodel.PreGame;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;
import org.usfirst.frc.team4911.scouting.datamodel.TournamentLevel;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link PreGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreGameFragment extends Fragment
        implements RecordLocationFragment.OnRecordLocationMapTouchListener {
    private EditText etxtMatchNum;
    private EditText etxtTeamNum;
    private CheckBox chkbxHasGear;
    private CheckBox chkbxHasFuel;
    private CheckBox chbxUsesOwnRope;
    private Spinner spnrFuelQuantity;
    private TouchPadPosition ropePosition = TouchPadPosition.None;

    public PreGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PreGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreGameFragment newInstance() {
        return new PreGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Remember when we talked about activity lifecycle and how fragments have their own lifecycle
    // only it's a bit different? This is an example of how they're different. When we're dealing
    // with activities, all the layout initialisation stuff happens in OnCreate. With fragments it
    // it generally happens in OnCreateView. That's because OnCreate for fragments runs when the
    // OnCreate of the activity they belong to runs, but OnCreateView is what runs before the
    // fragment is shown to the user. In this case, OnCreateView runs when the user is scrolling
    // through the tabs and has reached one which has the relevant fragment in it.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // First we inflate the view. This allows us to access UI items from code.
        View view = inflater.inflate(R.layout.fragment_pre_game, container, false);

        // Fill the number of items spinner
        Integer[] items = new Integer[]{0,1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, items);

        etxtMatchNum = (EditText) view.findViewById(R.id.etxt_pre_game_match_num);
        etxtTeamNum = (EditText) view.findViewById(R.id.etxt_pre_game_team_num);

        chkbxHasGear = (CheckBox) view.findViewById(R.id.chkbx_pre_game_has_gear);
        chkbxHasFuel = (CheckBox) view.findViewById(R.id.chkbx_pre_game_has_fuel);
        chbxUsesOwnRope = (CheckBox) view.findViewById(R.id.chkbx_pre_game_uses_own_rope);

        spnrFuelQuantity = (Spinner) view.findViewById(R.id.spinner_pre_game_fuel_count);
        spnrFuelQuantity.setAdapter(adapter);

        // Initialise the rope location button
        Button btnRopeLocation = (Button) view.findViewById(R.id.btn_pre_game_own_rope_location);
        btnRopeLocation.setOnClickListener(recordLocation);

        // Initialise the start game button
        Button btnSaveData = (Button) view.findViewById(R.id.btn_pre_game_start_game);
        btnSaveData.setOnClickListener(initScoutingData);

        return view;
    }

    /**
     * Handles touch events on the location map. Implements an interface defined in that class.
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
     * OnClickListener for the button that records the rope location.
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
     * OnClickListener for the button that initialises the scouting data object.
     */
    private View.OnClickListener initScoutingData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ScoutingData scoutingData = createNewScoutingData();
            PreGame preGame = scoutingData.getMatchData().getPreGame();

            ((ScoutMatchActivity) getActivity()).setScoutingData(scoutingData);

            // Now that we've initialised the scouting data we can add the pre-match data
            preGame.setHasFuel(chkbxHasFuel.isChecked());
            preGame.setHasGear(chkbxHasGear.isChecked());

            int fuelCount = Integer.parseInt(spnrFuelQuantity
                    .getItemAtPosition(spnrFuelQuantity.getSelectedItemPosition()).toString());
            preGame.setFuelCount(fuelCount);

            boolean usesOwnRope = chbxUsesOwnRope.isChecked();
            preGame.setUsesOwnRope(usesOwnRope);
            preGame.setRopeTouchPadPosition(ropePosition);

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(1);
        }
    };

    private ScoutingData createNewScoutingData() {
        SharedPreferences sharedpreferences = getActivity().getApplicationContext().getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        String eventCode = sharedpreferences.getString(SetupActivity.EventCode, "DEMO");
        String scoutName = sharedpreferences.getString(SetupActivity.ScoutName, "Anne Gwynne-Robson");
        String scoutingTeamName = sharedpreferences.getString(SetupActivity.ScoutTeam, "ScoutingTeamName");
        String drive_Station = sharedpreferences.getString(SetupActivity.DriveStation, "");
        DriveStation station = !drive_Station.equals("") ? DriveStation.valueOf(drive_Station) : DriveStation.Blue1;
        String deviceId = sharedpreferences.getString(SetupActivity.AppInstanceId, "testKindle");

        int matchNumber = Integer.parseInt(etxtMatchNum.getText().toString());
        int teamNumber = Integer.parseInt(etxtTeamNum.getText().toString());

        return new ScoutingData(eventCode, matchNumber, station, teamNumber, deviceId, scoutName, scoutingTeamName);
    }
}
