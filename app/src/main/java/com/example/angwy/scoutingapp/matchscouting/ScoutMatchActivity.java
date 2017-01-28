package com.example.angwy.scoutingapp.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.angwy.scoutingapp.BaseActivity;
import com.example.angwy.scoutingapp.R;
import com.example.angwy.scoutingapp.matchscouting.datamodel.MatchData;

// This is the top-level class for match scouting.
// The first thing it does when you start it up is display the match metadata collection screen
public class ScoutMatchActivity extends BaseActivity implements FragmentChangeListener {

    // The matchData object which contains the data of the match we're scouting with this
    // instance of this class.
    public MatchData matchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scout_match);

        // We begin by creating the metadata fragment
        CollectMetadataFragment firstFragment = new CollectMetadataFragment();

        firstFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();

        return;
    }

    /**
     * Helps with switching from fragment to fragment
     * @param fragment The fragment to replace the current fragment with.
     */
    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
