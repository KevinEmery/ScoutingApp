package org.usfirst.frc.team4911.scouting.matchscouting;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.Alliance;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.MatchData;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.Team;

/** This is the fragment that displays the screen which collects the basic match data.
 * A simple {@link DialogFragment} subclass.
 */
public class CollectMetadataFragment extends DialogFragment {

    Button buttonStartAuto;
    EditText editTextScoutName;

    public CollectMetadataFragment() {
        // Required empty public constructor
    }

    public static CollectMetadataFragment newInstance() {
        return new CollectMetadataFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_metadata, container, false);
        addButtonClickListeners(view);
        return view;
    }

    private void addButtonClickListeners(View view) {

        buttonStartAuto = (Button) view.findViewById(R.id.buttonStartAuto);
        editTextScoutName = (EditText) view.findViewById(R.id.edit_text_scout_name);

        buttonStartAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the scout name and add it to the match metadata
                String scout = editTextScoutName.getText().toString();

                // We want to make sure the name isn't empty
                if(TextUtils.isEmpty(scout))
                {
                    Toast.makeText(getActivity(), "Please enter a scout name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Initialise the matchData object in the parent activity class
                ((ScoutMatchActivity)getActivity()).matchData = new MatchData(scout, 1,
                        new Team("CyberKnights", 4911), Alliance.RED);

                // Then we vanish and let the user interact with the game screen
                dismiss();
            }
        });
    }
}
