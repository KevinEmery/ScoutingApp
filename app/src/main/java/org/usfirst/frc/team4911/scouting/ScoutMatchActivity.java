package org.usfirst.frc.team4911.scouting;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // I do not know what this does but I think it's got something to do with
        // the little bar up the top that lets us jump between activities.
        getMenuInflater().inflate(R.menu.menu_scout_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // This is the method that creates the different fragments for each page.
            // Notice that it's pretty similar to the OnClickListener of the menu in
            // MainActivity?
            switch (position) {
                case  0:
                    return PreMatchFragment.newInstance();
                case 1:
                    return AutonomousFragment.newInstance();
                case 2:
                    return TeleOpFragment.newInstance();
                case 3:
                    return PostMatchFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages. IDK why we need to keep track of the number like this but
            // we do so we're doing it.
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
}
