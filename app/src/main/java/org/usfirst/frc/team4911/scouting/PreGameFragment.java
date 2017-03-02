package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.usfirst.frc.team4911.scouting.datamodel.PreGame;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;


/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link PreGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreGameFragment extends Fragment implements
        RecordLocationFragment.OnRecordLocationMapTouchListener,
        RecordLocationFragment.OnLocationDoneButtonClickListener{

    OnStartClickedListener mListener;

    private EditText etxtMatchNum;
    private EditText etxtTeamNum;
    private ToggleButton toggleButtonHasGear;
    private ToggleButton toggleButtonHasFuel;
    private ToggleButton toggleButtonUsesOwnRope;
    private ToggleButton toggleButtonHasPilot;

    private TouchPadPosition ropePosition = TouchPadPosition.None;
    private String robotPosition = "";

    private boolean isRopePosition;
    private boolean isBotPosition;
    private boolean isOkToCloseLocationDialog = false;

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

        etxtMatchNum = (EditText) view.findViewById(R.id.etxt_pre_game_match_num);
        etxtTeamNum = (EditText) view.findViewById(R.id.etxt_pre_game_team_num);

        toggleButtonHasGear = (ToggleButton) view.findViewById(R.id.toggleButton_pre_game_has_gear);
        toggleButtonHasFuel = (ToggleButton) view.findViewById(R.id.toggleButton_pre_game_has_fuel);
        toggleButtonUsesOwnRope = (ToggleButton) view.findViewById(R.id.toggleButton_pre_game_uses_own_rope);
        toggleButtonHasPilot = (ToggleButton) view.findViewById(R.id.toggleButton_pre_game_has_pilot);

        // Initialise the rope location button
        Button btnRopeLocation = (Button) view.findViewById(R.id.btn_pre_game_own_rope_location);
        btnRopeLocation.setOnClickListener(recordRopeLocation);

        Button robotLocation = (Button) view.findViewById(R.id.btn_pre_game_own_robot_location);
        robotLocation.setOnClickListener(recordRobotLocation);

        // Initialise the start game button
        Button btnSaveData = (Button) view.findViewById(R.id.btn_pre_game_start_game);
        btnSaveData.setOnClickListener(startGame);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartClickedListener) {
            mListener = (OnStartClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStartClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Handles touch events on the location map. Implements an interface defined in that class.
     * TODO: Figure out how to handle the two different types of location that we can be dealing
     * with here. Right now we can only handle ropes >.<
     */
    @Override
    public void onRecordLocationMapTouch(Pair<Float, Float> normalisedTouchPoint) {

        SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");
        boolean isBlueAlliance = (driveStation.toLowerCase().contains("blue"));

        String message = "X: " + normalisedTouchPoint.first + "Y: " + normalisedTouchPoint.second;

        //if (isRopePosition) {
        //    ropePosition = LocationMappingHelpers.GetTouchPadPosition((int) event.getX(),
        //            (int) event.getY(), isBlueAlliance);
//
        //    if (ropePosition == TouchPadPosition.None) {
        //        message = "Please select a valid touchpad";
        //        isOkToCloseLocationDialog = false;
        //    } else {
        //        message = "Rope placed at position " + ropePosition.toString();
        //        isOkToCloseLocationDialog = true;
        //    }
        //}
//
        //if (isBotPosition) {
        //    robotPosition = LocationMappingHelpers.GetShootingPosition((int) event.getX(),
        //            (int) event.getY(), isBlueAlliance);
//
        //    if (robotPosition.equals("")
        //            || robotPosition.equals(LocationMappingHelpers.OUT_OF_BOUNDS)) {
        //        message = "Please select a location on the field";
        //        isOkToCloseLocationDialog = false;
        //    } else {
        //        message = "Robot starting position is at " + robotPosition;
        //        isOkToCloseLocationDialog = true;
        //    }
        //}

        //TODO: Remove when done debugging map
        isOkToCloseLocationDialog = true;

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLocationDoneButtonClick() {
        if (isOkToCloseLocationDialog) {
            isRopePosition = false;
            isBotPosition = false;
        }

        return isOkToCloseLocationDialog;
    }

    /**
     * OnClickListener for the button that records the robot location
     */
    private View.OnClickListener recordRobotLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                    .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

            String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");

            int resourceIdOfMapToDraw = (driveStation.toLowerCase().contains("red")) ?
                    R.drawable.shootingzone_red : R.drawable.shootingzone_blue;

            isBotPosition = true;

            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(resourceIdOfMapToDraw);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    /**
     * OnClickListener for the button that records the rope location.
     */
    private View.OnClickListener recordRopeLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sharedpreferences = getActivity().getApplicationContext()
                    .getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

            String driveStation = sharedpreferences.getString(SetupActivity.DriveStation, "");

            int resourceIdOfMapToDraw = (driveStation.toLowerCase().contains("red")) ?
                    R.drawable.touchpad_locations_red : R.drawable.touchpad_locations_blue;

            isRopePosition = true;

            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(resourceIdOfMapToDraw);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    /**
     * OnClickListener for the button that initialises the scouting data object.
     */
    private View.OnClickListener startGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String teamNum = etxtTeamNum.getText().toString();
            int teamNumber = teamNum.isEmpty() ? 0 : Integer.parseInt(teamNum);

            if (teamNumber <= 0 || 9999 < teamNumber) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                dlgAlert.setMessage("Please provide a valid team number.");
                dlgAlert.setTitle("Invalid Team Number");
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

            // TODO: Should keep a running counter somewhere.
            String matchNum = etxtMatchNum.getText().toString();
            int matchNumber = matchNum.isEmpty() ? 0 : Integer.parseInt(matchNum);
            if (matchNumber <= 0 || 150 <= matchNumber) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                dlgAlert.setMessage("Please provide a valid match number.");
                dlgAlert.setTitle("Invalid Match Number");
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

            if (toggleButtonUsesOwnRope.isChecked() && robotPosition.isEmpty())
            {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(v.getContext());
                dlgAlert.setMessage("Please select the rope position.");
                dlgAlert.setTitle("Invalid Rope Position");
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

            PreGame preGame = new PreGame();
            preGame.setHasGear(toggleButtonHasGear.isChecked());
            preGame.setHasFuel(toggleButtonHasFuel.isChecked());
            preGame.setHasPilot(toggleButtonHasPilot.isChecked());
            preGame.setUsesOwnRope(toggleButtonUsesOwnRope.isChecked());
            preGame.setRopeTouchPadPosition(ropePosition);
            preGame.setRobotPosition(robotPosition);

            if (mListener != null) {
                mListener.onStartClicked(matchNumber, teamNumber, preGame);
            }

            ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.container);
            viewPager.setCurrentItem(1);
        }
    };

    /**
     * Passes the necessary parameters up to the scoutmatchactivity.
     */
    public interface OnStartClickedListener {
        void onStartClicked(int matchNumber, int teamNumber, PreGame preGame);
    }
}
