package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

import static android.support.v7.app.AlertDialog.*;

/**
 * Created by johansu on 2/20/2017.
 */

public class SetupActivity extends AppCompatActivity {

    Spinner spinner_eventName;
    EditText edit_scoutName;
    Spinner spinner_scoutTeam;
    Spinner spinner_driveStation;
    Button button_savePreferences;

    public static final String MyPREFERENCES = "MyScoutingPrefs" ;
    public static final String EventCode = "eventCodeKey";
    public static final String ScoutName = "scoutNameKey";
    public static final String ScoutTeam = "scoutTeamKey";
    public static final String DriveStation = "driveStationKey";
    public static final String AppInstanceId = "appInstanceKey";

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        spinner_eventName = (Spinner)findViewById(R.id.spinner_event_name);
        edit_scoutName = (EditText)findViewById(R.id.editText_ScoutName);

        spinner_scoutTeam = (Spinner)findViewById(R.id.spinner_scout_team);
        spinner_driveStation = (Spinner)findViewById(R.id.spinner_drive_station);
        button_savePreferences = (Button)findViewById(R.id.button_Save_Preverences);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Resources res = getResources();
        final String[] event_codes = res.getStringArray(R.array.pnw_eventcodes_array);
        final String[] drive_stations = res.getStringArray(R.array.drive_stations_array);
        final String[] scoutTeams = res.getStringArray(R.array.scoutteams_array);

        String event_Name = (sharedpreferences.getString(EventCode, ""));
        edit_scoutName.setText(sharedpreferences.getString(ScoutName, ""));

        String scoutTeam  = sharedpreferences.getString(ScoutTeam, "");
        String drive_Station = sharedpreferences.getString(DriveStation, "");
        final String appInstanceId = sharedpreferences.getString(AppInstanceId, UUID.randomUUID().toString());

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> eventNameAdapter = ArrayAdapter.createFromResource(this,
                R.array.pnw_events_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        eventNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_eventName.setAdapter(eventNameAdapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> scoutTeamAdapter = ArrayAdapter.createFromResource(this,
                R.array.scoutteams_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        scoutTeamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_scoutTeam.setAdapter(scoutTeamAdapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> driveStationAdapter = ArrayAdapter.createFromResource(this,
                R.array.drive_stations_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        driveStationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_driveStation.setAdapter(driveStationAdapter);


        for (int i = 0; i < event_codes.length; i++) {
            if (event_codes[i].equals(event_Name)) {
                spinner_eventName.setSelection(i % event_codes.length);
                break;
            }
        }

        for (int i = 0; i < scoutTeams.length; i++) {
            if (scoutTeams[i].equals(scoutTeam)) {
                spinner_scoutTeam.setSelection(i % scoutTeams.length);
                break;
            }
        }

        for (int position = 0; position < drive_stations.length; position++) {
            if (drive_stations[position].equals(drive_Station)) {
                spinner_driveStation.setSelection(position % drive_stations.length);
                break;
            }
        }

        button_savePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event_Code = event_codes[spinner_eventName.getSelectedItemPosition()];
                String scout_Name = edit_scoutName.getText().toString();
                String scout_Team = (String) spinner_scoutTeam.getItemAtPosition(spinner_scoutTeam.getSelectedItemPosition());
                String drive_station = (String) spinner_driveStation.getItemAtPosition(spinner_driveStation.getSelectedItemPosition());

                if (scout_Name.length() < 2) {
                    Builder dlgAlert = new Builder(v.getContext());
                    dlgAlert.setMessage("Please enter your name.");
                    dlgAlert.setTitle("Missing Info");
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(EventCode, event_Code);
                    editor.putString(ScoutName, scout_Name);
                    editor.putString(ScoutTeam, scout_Team);
                    editor.putString(DriveStation, drive_station);
                    editor.putString(AppInstanceId, appInstanceId);
                    editor.commit();

                    finish();
                }
            }
        });
    }
}