package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.usfirst.frc.team4911.scouting.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreMatchButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreMatchButtonFragment extends Fragment {
    Button buttonStartMatch;

    public PreMatchButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AutoButtonsFragment.
     */
    public static PreMatchButtonFragment newInstance() {
        return new PreMatchButtonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pre_match_button, container, false);

        buttonStartMatch = (Button) view.findViewById(R.id.buttonStartMatch);

        buttonStartMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect login data
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                CollectMetadataFragment collectMetadataFragment = new CollectMetadataFragment();
                collectMetadataFragment.show(fragmentManager, "Dialog Fragment");

                // Switch in start match
                AutoButtonsFragment autoButtonsFragment = AutoButtonsFragment.newInstance();
                ButtonFragmentChangeListener fc =(ButtonFragmentChangeListener)getActivity();
                fc.replaceButtonFragment(autoButtonsFragment);

                // And set the match state to auto
                ((ScoutMatchActivity) getActivity()).matchState = MatchState.AUTO;
            }
        });

        return view;
    }
}
