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
import android.widget.EditText;
import android.widget.Spinner;

import org.usfirst.frc.team4911.scouting.datamodel.DriveStation;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

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
        btnRopeLocation.setOnClickListener(recordRopeLocation);

        // Initialise the start game button
        Button btnSaveData = (Button) view.findViewById(R.id.btn_pre_game_start_game);
        btnSaveData.setOnClickListener(initScoutingData);

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
            ((ScoutMatchActivity) getActivity()).setScoutingData(createNewScoutingData());

            // Now that we've initialised the scouting data we can add the pre-match data
            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getPreGame()
                    .setHasFuel(chkbxHasFuel.isChecked());
            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getPreGame()
                    .setHasGear(chkbxHasGear.isChecked());

            int fuelCount = Integer.parseInt(spnrFuelQuantity
                    .getItemAtPosition(spnrFuelQuantity.getSelectedItemPosition()).toString());
            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getPreGame()
                    .setFuelCount(fuelCount);

            boolean usesOwnRope = chbxUsesOwnRope.isChecked();
            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getPreGame()
                    .setUsesOwnRope(usesOwnRope);
            ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData().getPreGame()
                    .setRopeTouchPadPosition(ropePosition);
        }
    };

    private ScoutingData createNewScoutingData() {
        //TODO: Fetch these from SharedPreferences when we've gotten that working
        String eventCode = "eventCode";
        String tournamentLevel = "tournamentLevel";
        DriveStation station = DriveStation.Blue1;
        String deviceId = "testKindle";
        String scoutName = "Anne Gwynne-Robson";
        String scoutingTeamName = "ScoutingTeamName";

        int matchNumber = Integer.parseInt(etxtMatchNum.getText().toString());
        int teamNumber = Integer.parseInt(etxtTeamNum.getText().toString());

        return new ScoutingData(eventCode, matchNumber, tournamentLevel, station, teamNumber,
                deviceId, scoutName, scoutingTeamName);
    }

    // This method gets called by the record location dialog fragment
    @Override
    public void OnRecordLocationEvent(Object locationObject) {
        ropePosition = (TouchPadPosition) locationObject;
    }
}
