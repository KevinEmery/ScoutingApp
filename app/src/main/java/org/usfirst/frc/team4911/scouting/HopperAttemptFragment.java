package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.HopperAttempt;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HopperAttemptFragment.OnHopperAttemptCreatedListener} interface
 * to handle interaction events.
 * Use the {@link HopperAttemptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HopperAttemptFragment extends Fragment implements
        RecordLocationFragment.OnRecordLocationMapTouchListener,
        RecordLocationFragment.OnLocationDoneButtonClickListener {

    private OnHopperAttemptCreatedListener mListener;

    CheckBox hopperActivated;
    String hopperLocation;

    public HopperAttemptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HopperAttemptFragment.
     */
    public static HopperAttemptFragment newInstance() {
        return new HopperAttemptFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onAttachToParentFragment(getParentFragment());
    }

    private void onAttachToParentFragment(Fragment fragment)
    {
        try
        {
            mListener = (OnHopperAttemptCreatedListener)fragment;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnGearAttemptCreatedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hopper_attempt, container, false);

        hopperActivated = (CheckBox) view.findViewById(R.id.checkbox_hopper_attempt_activated);

        Button location = (Button) view.findViewById(R.id.button_hopper_attempt_location);
        location.setOnClickListener(recordLocation);

        Button save = (Button) view.findViewById(R.id.button_hopper_attempt_save);
        save.setOnClickListener(autoSaveButton);

        return view;
    }

    @Override
    public void onRecordLocationMapTouch(Pair<Float, Float> normalisedTouchPoint) {
        String text = "X: " + normalisedTouchPoint.first + "Y: " + normalisedTouchPoint.second;
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        hopperLocation = "foo";
    }

    /**
     * Handles presses of the 'done' button on the record location dialog.
     * @return True if we're cool with the dialog being closed, false otherwise.
     */
    @Override
    public boolean onLocationDoneButtonClick() {
        // We're gonna want to beef this out once we've added a hopper number class.
        return true;
    }

    private View.OnClickListener recordLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int resourceIdOfMapToDraw = R.drawable.full_field;

            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(resourceIdOfMapToDraw);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };

    View.OnClickListener autoSaveButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            HopperAttempt hopperAttempt = new HopperAttempt();

            if (mListener != null) {
                mListener.onHopperAttemptCreated(hopperAttempt);
            }
        }
    };

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnHopperAttemptCreatedListener {
        void onHopperAttemptCreated(HopperAttempt hopperAttempt);
    }
}
