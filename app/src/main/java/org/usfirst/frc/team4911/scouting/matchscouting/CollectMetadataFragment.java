package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.MatchData;

import java.util.ArrayList;

/** This is the fragment that displays the screen which collects the basic match data.
 * A simple {@link Fragment} subclass.
 */
public class CollectMetadataFragment extends Fragment {

    Button buttonStartAuto;

    public CollectMetadataFragment() {
        // Required empty public constructor
    }

    public static CollectMetadataFragment newInstance() {
        return new CollectMetadataFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_metadata, container, false);
        addButtonClickListeners(view);
        return view;
    }

    public void addButtonClickListeners(View view) {

        buttonStartAuto = (Button) view.findViewById(R.id.buttonStartAuto);

        buttonStartAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialise the matchData object in the parent activity class
                ((ScoutMatchActivity)getActivity()).matchData = new MatchData();
                ((ScoutMatchActivity)getActivity()).matchData.autoMatchEvents = new ArrayList<>();
                ((ScoutMatchActivity)getActivity()).matchData.teleOpMatchEvents = new ArrayList<>();

                // Get the scout name and add it to the match metadata
                EditText scoutNameEditText =
                        (EditText) getActivity().findViewById(R.id.edit_text_scout_name);
                ((ScoutMatchActivity)getActivity()).matchData.scout =
                        scoutNameEditText.getText().toString();

                // Then we start to scout auto
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
                fragmentChangeListener.replaceFragment(ScoutAutoFragment.newInstance(), true);
            }
        });
    }
}
