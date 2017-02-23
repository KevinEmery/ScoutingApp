package org.usfirst.frc.team4911.scouting;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import org.usfirst.frc.team4911.scouting.datamodel.MatchData;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;

// This is the single activity we care about the most.
public class ScoutMatchActivity extends AppCompatActivity {

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

    /**
     * Keeps track of the alliance type that the match is scouting for
     */
    private AllianceType alliance;

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

        // If we haven't set the stuff in shared preferences, make a fuss

        // Get the alliance type from sharedPreferences
        alliance = AllianceType.BLUE;
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
     * Getter for the {@link MatchData} object associated with this class.
     * @return The matchdata object associated with this class.
     */
    public ScoutingData getScoutingData() {
        return this.scoutingData;
    }

    /**
     * Setter for the {@link MatchData} object associated with this class.
     * @param scoutingData The matchdata object to set matchdata to.
     */
    public void setScoutingData(ScoutingData scoutingData) {
        this.scoutingData = scoutingData;
    }

    /**
     * Gets the alliance type of the current match.
     * @return The alliance type of the current match.
     */
    public AllianceType getAlliance() {
        return this.alliance;
    }

    /**
     * Sets the alliance type of the current match.
     * @param alliance The alliance type to set.
     */
    public void setAlliance(AllianceType alliance) {
        this.alliance = alliance;
    }
}
