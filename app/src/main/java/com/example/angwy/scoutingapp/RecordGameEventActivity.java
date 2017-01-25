package com.example.angwy.scoutingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordGameEventActivity extends BaseActivity implements FragmentChangeListener {

    Button buttonBackToField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_game_event);

        GameActivityTopMenuFragment firstFragment = new GameActivityTopMenuFragment();
        firstFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
        addButtonClickListeners();
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

    public void addButtonClickListeners() {
        buttonBackToField = (Button) findViewById(R.id.buttonReturnToField);

        buttonBackToField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the field activity
                Intent intent = new Intent(RecordGameEventActivity.this, FieldMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
