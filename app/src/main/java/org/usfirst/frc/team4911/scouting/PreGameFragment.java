package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.usfirst.frc.team4911.scouting.datamodel.DriveStation;
import org.usfirst.frc.team4911.scouting.datamodel.MatchData;
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
public class PreGameFragment extends Fragment implements OnRecordLocationEventListener{
    private EditText etxtMatchNum;
    private EditText etxtTeamNum;
    private CheckBox chkbxHasGear;
    private CheckBox chkbxHasFuel;
    private CheckBox chbxUsesOwnRope;
    private Spinner spnrFuelQuantity;
    private TouchPadPosition ropePosition = TouchPadPosition.None;
    private Spinner spinner_tournamentLevel;

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

        spinner_tournamentLevel = (Spinner) view.findViewById(R.id.spinner_tournamentlevel);
        chkbxHasGear = (CheckBox) view.findViewById(R.id.chkbx_pre_game_has_gear);
        chkbxHasFuel = (CheckBox) view.findViewById(R.id.chkbx_pre_game_has_fuel);
        chbxUsesOwnRope = (CheckBox) view.findViewById(R.id.chkbx_pre_game_uses_own_rope);

        spnrFuelQuantity = (Spinner) view.findViewById(R.id.spinner_pre_game_fuel_count);
        spnrFuelQuantity.setAdapter(adapter);

        // Initialise the rope location button
        Button btnRopeLocation = (Button) view.findViewById(R.id.btn_pre_game_own_rope_location);
        btnRopeLocation.setOnClickListener(recordRopeLocation);

        // Initialise the start game button
        Button btnSaveData = (Button) view.findViewById(R.id.btn_pre_game_start_game);
        btnSaveData.setOnClickListener(initScoutingData);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> tournamnetLevelAdapter = ArrayAdapter.createFromResource(
                getActivity().getApplicationContext(),
                R.array.tournamnetlevel_array,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        tournamnetLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner_tournamentLevel.setAdapter(tournamnetLevelAdapter);

        return view;
    }

    /**
     * OnClickListener for the button that records the rope location.
     */
    private View.OnClickListener recordRopeLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Show the location record dialog
            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(
                    ((ScoutMatchActivity) getActivity()).getAlliance(), EventLocationType.CLIMB);
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
            preGame .setHasFuel(chkbxHasFuel.isChecked());
            preGame .setHasGear(chkbxHasGear.isChecked());

            int fuelCount = Integer.parseInt(spnrFuelQuantity
                    .getItemAtPosition(spnrFuelQuantity.getSelectedItemPosition()).toString());
            preGame .setFuelCount(fuelCount);

            boolean usesOwnRope = chbxUsesOwnRope.isChecked();
            preGame.setUsesOwnRope(usesOwnRope);
            preGame.setRopeTouchPadPosition(ropePosition);

            String tournamentLevel = (String) spinner_tournamentLevel.getItemAtPosition(spinner_tournamentLevel.getSelectedItemPosition());
            ((ScoutMatchActivity) getActivity()).getScoutingData().setTournamentLevel(tournamentLevel);

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(1);
        }
    };

    private ScoutingData createNewScoutingData() {
        // TODO : Make this an enum and add to SetupActivity.
        TournamentLevel tournamentLevel = TournamentLevel.qual;

        SharedPreferences sharedpreferences = getActivity().getApplicationContext().getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        String eventCode = sharedpreferences.getString(SetupActivity.EventCode, "DEMO");
        String scoutName = sharedpreferences.getString(SetupActivity.ScoutName, "Anne Gwynne-Robson");
        String scoutingTeamName = sharedpreferences.getString(SetupActivity.ScoutTeam, "ScoutingTeamName");
        String drive_Station = sharedpreferences.getString(SetupActivity.DriveStation, "");
        DriveStation station = !drive_Station.equals("") ? DriveStation.valueOf(drive_Station) : DriveStation.Blue1;
        String deviceId = sharedpreferences.getString(SetupActivity.AppInstanceId, "testKindle");

        int matchNumber = Integer.parseInt(etxtMatchNum.getText().toString());
        int teamNumber = Integer.parseInt(etxtTeamNum.getText().toString());

        return new ScoutingData(eventCode, matchNumber, tournamentLevel.toString(), station, teamNumber,
                deviceId, scoutName, scoutingTeamName);
    }

    // This method gets called by the record location dialog fragment
    @Override
    public void OnRecordLocationEvent(Object locationObject) {
        ropePosition = (TouchPadPosition) locationObject;
    }
}
