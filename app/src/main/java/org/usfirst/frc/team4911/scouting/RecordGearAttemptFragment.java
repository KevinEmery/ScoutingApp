package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;

/**
 * Code for the fragment which handles recording gear events.
 * Use the {@link RecordGearAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordGearAttemptFragment extends Fragment
        implements RecordLocationFragment.OnRecordLocationMapTouchListener {

    private OnRecordGearAttemptFragmentInteractionListener mListener;

    GearPegPosition gearPegPosition;
    private TextView locationMessage;
    private CheckBox placedGear;

    public RecordGearAttemptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordGearAttemptFragment.
     */
    public static RecordGearAttemptFragment newInstance() {
        return new RecordGearAttemptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_gear_attempt, container, false);

        locationMessage = (TextView) view.findViewById(R.id.txt_gear_record_auto_location);
        placedGear = (CheckBox) view.findViewById(R.id.checkbox_record_gear_success);

        Button location = (Button) view.findViewById(R.id.btn_gear_record_location);
        location.setOnClickListener(recordLocation);

        Button save = (Button) view.findViewById(R.id.btn_gear_record_save);
        save.setOnClickListener(saveGearAttempt);

        // Add an onclick listener for the location button

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRecordGearAttemptFragmentInteractionListener) {
            mListener = (OnRecordGearAttemptFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Handles touch events on the location map.
     */
    @Override
    public void onRecordLocationMapTouch(MotionEvent event) {
        //TODO: Hi Scott! This is where the code that handles touch events should go. Right now all
        // it does is show a toast containing the X and Y coordinates of the touch point.
        // I leave the mapping in your hands :)
        String text = "X: " + event.getX() + "Y: " + event.getY();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a gear event.
     */
    private View.OnClickListener recordLocation = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getChildFragmentManager();

            //TODO: Set this so map changes depending on alliance
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(R.drawable.airship_blue);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    /**
     * OnClickListener for the save button that records the current gear event.
     */
    private View.OnClickListener saveGearAttempt = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            GearAttempt gearAttempt = new GearAttempt();
            GearResult result = (placedGear.isChecked()) ? GearResult.Success : GearResult.Failed;

            gearAttempt.setGearResult(result);
            gearAttempt.setGearPegPosition(gearPegPosition);

            // Call the parent activity and pass it the gear attempt
            if (mListener != null) {
                mListener.onRecordGearAttemptFragmentInteraction(gearAttempt);
            }

            restoreDefaults();
        }
    };

    /**
     * Clears the current gearResult object and restores all the appropriate defaults.
     */
    private void restoreDefaults() {
        String message = "Location: ";
        locationMessage.setText(message);
        placedGear.setChecked(false);
    }

    /**
     * All activities containing this fragment must implement this interface.
     */
    public interface OnRecordGearAttemptFragmentInteractionListener {
        void onRecordGearAttemptFragmentInteraction(GearAttempt gearAttempt);
    }
}
