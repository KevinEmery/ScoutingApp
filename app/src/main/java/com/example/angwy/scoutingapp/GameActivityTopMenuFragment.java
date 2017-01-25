package com.example.angwy.scoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Fragment that contains the first menu that shows when you push the field button.
 */
public class GameActivityTopMenuFragment extends Fragment {

    Button buttonClimbing;
    Button buttonShooting;

    public GameActivityTopMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_actvity_top_menu, container, false);
        addButtonClickListeners(view);
        return view;
    }

    public void addButtonClickListeners(View view) {

        buttonClimbing = (Button) view.findViewById(R.id.buttonClimbing);
        buttonShooting = (Button) view.findViewById(R.id.buttonShooting);

        buttonClimbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClimbingFragment newFragment = new ClimbingFragment();
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
                fragmentChangeListener.replaceFragment(newFragment);
            }
        });

        buttonShooting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment and give it an argument specifying the article it should show
                ShootingFragment newFragment = new ShootingFragment();
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
                fragmentChangeListener.replaceFragment(newFragment);
            }
        });
    }
}
