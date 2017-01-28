package com.example.angwy.scoutingapp.matchscouting.recordgameeventactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.angwy.scoutingapp.R;
import com.example.angwy.scoutingapp.matchscouting.FragmentChangeListener;


/**
 * Fragment that contains the first menu that shows when you push the field button.
 */
public class GameEventMenuFragment extends Fragment {

    Button buttonClimbing;
    Button buttonShooting;

    public GameEventMenuFragment() {
        // Required empty public constructor
    }

    public static GameEventMenuFragment newInstance() {
        return new GameEventMenuFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_event_menu, container, false);
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
