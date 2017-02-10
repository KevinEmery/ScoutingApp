package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.usfirst.frc.team4911.scouting.BaseActivity;
import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.MatchData;

// This is the top-level class for match scouting.
// The first thing it does when you start it up is display the match metadata collection screen
public class ScoutMatchActivity extends BaseActivity implements ButtonFragmentChangeListener {

    // The matchData object which contains the data of the match we're scouting with this
    // instance of this class.
    public MatchData matchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scout_match);

        // When we enter this activity we land on the create match metadata fragment
        FieldFragment fieldFragment = FieldFragment.newInstance();
        fieldFragment.setArguments(getIntent().getExtras());

        AutoButtonsFragment buttonsFragment = AutoButtonsFragment.newInstance();
        buttonsFragment.setArguments(getIntent().getExtras());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.field_fragment_container, fieldFragment);
        fragmentTransaction.add(R.id.match_phase_buttons_fragment_container, buttonsFragment);
        fragmentTransaction.commit();

        CollectMetadataFragment fragment = CollectMetadataFragment.newInstance();
        fragment.show(fragmentManager, "Dialog Fragment");
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
