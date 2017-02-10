package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.usfirst.frc.team4911.scouting.BaseActivity;
import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.MatchData;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.MatchEvent;

// This is the top-level class for match scouting.
// The first thing it does when you start it up is display the match metadata collection screen
public class ScoutMatchActivity extends BaseActivity implements ButtonFragmentChangeListener {

    // The matchData object which contains the data of the match we're scouting with this
    // instance of this class.
    public MatchData matchData;

    // Object that keeps track of match state
    public MatchState matchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        matchState = MatchState.PREMATCH;

        setContentView(R.layout.activity_scout_match);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FieldFragment fieldFragment = FieldFragment.newInstance();
        PreMatchButtonFragment preMatchButtonFragment = PreMatchButtonFragment.newInstance();

        fragmentTransaction.add(R.id.field_fragment_container, fieldFragment);
        fragmentTransaction.add(R.id.match_phase_buttons_fragment_container, preMatchButtonFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void replaceButtonFragment(Fragment buttonFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.match_phase_buttons_fragment_container, buttonFragment,
                buttonFragment.toString());
        fragmentTransaction.commit();
    }
}
