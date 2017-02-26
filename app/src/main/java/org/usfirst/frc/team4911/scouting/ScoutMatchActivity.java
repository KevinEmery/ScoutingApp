package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import org.usfirst.frc.team4911.scouting.datamodel.AutonomousPeriod;
import org.usfirst.frc.team4911.scouting.datamodel.DriveStation;
import org.usfirst.frc.team4911.scouting.datamodel.MatchData;
import org.usfirst.frc.team4911.scouting.datamodel.PreGame;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.TeleopPeriod;

// This is the single activity we care about the most.
public class ScoutMatchActivity extends AppCompatActivity implements
        ScoutAutoFragment.OnAutoPeriodObjectCreatedListener,
        ScoutTeleOpFragment.OnTeleopPeriodObjectCreatedListener,
        PreGameFragment.OnStartClickedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * The {@link MatchData} object that stores all scouted data collected from this match.
     */
    private ScoutingData scoutingData;

    // One thing I should probably mention is that it's important to keep track of when UI stuff
    // gets bound to code. Here in the activity class we initialise all the views and stuff that
    // belong to the activity specifically and not any of the fragments. Think of it as being the
    // stuff that's defined in activity_scout_match.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages. This is the thing that holds all the different pages
     * and handles us switching between them.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // This is the method that creates the different fragments for each page.
            // Notice that it's pretty similar to the OnClickListener of the menu in
            // MainActivity?
            switch (position) {
                case  0:
                    return PreGameFragment.newInstance();
                case 1:
                    return ScoutAutoFragment.newInstance();
                case 2:
                    return ScoutTeleOpFragment.newInstance();
                case 3:
                    return EndGameFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages. IDK why we need to keep track of the number like this but
            // we do so we're doing it. This method is probably called by something at a deeper
            // layer of Android.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // From the name of this method I think it gets the titles of the pages in the
            // fragment. IDK what a lot of this code does in detail. I just guess.
            switch (position) {
                case 0:
                    return "Pre-Match";
                case 1:
                    return "Autonomous";
                case 2:
                    return "Tele-Op";
                case 3:
                    return "Post-Match";
            }
            return null;
        }
    }

    /**
     * Fragment listener for the scoutautofragment. Adds the auto period to the matchData object
     * when it's created.
     * @param autonomousPeriod The autonomous period object to add to the matchdata
     */
    @Override
    public void onAutoPeriodObjectCreated(AutonomousPeriod autonomousPeriod) {
        scoutingData.getMatchData().setAutonomousPeriod(autonomousPeriod);
    }

    /** Same thing but for teleop.
     * @param teleopPeriod The teleop period object to add to matchdata.
     */
    @Override
    public void onTeleopPeriodObjectCreated(TeleopPeriod teleopPeriod) {
        scoutingData.getMatchData().setTeleopPeriod(teleopPeriod);
    }

    /**
     * Method that gets called when a match is started.
     * @param matchNumber the match number of the match that's starting.
     * @param teamNumber Team number of the team being scouted in this match.
     * @param preGame The pre-game data object for this team for this match.
     */
    @Override public void onStartClicked(int matchNumber, int teamNumber, PreGame preGame) {
        // Create the new scouting data object
        SharedPreferences sharedpreferences = getSharedPreferences(SetupActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        String eventCode = sharedpreferences.getString(SetupActivity.EventCode, "DEMO");
        String scoutName = sharedpreferences.getString(SetupActivity.ScoutName, "Anne Gwynne-Robson");
        String scoutingTeamName = sharedpreferences.getString(SetupActivity.ScoutTeam, "ScoutingTeamName");
        String drive_Station = sharedpreferences.getString(SetupActivity.DriveStation, "");
        DriveStation station = !drive_Station.equals("") ? DriveStation.valueOf(drive_Station) : DriveStation.Blue1;
        String deviceId = sharedpreferences.getString(SetupActivity.AppInstanceId, "testKindle");

        this.scoutingData = new ScoutingData(eventCode, matchNumber, station, teamNumber, deviceId, scoutName, scoutingTeamName);
        this.scoutingData.getMatchData().setPreGame(preGame);
    }
}
