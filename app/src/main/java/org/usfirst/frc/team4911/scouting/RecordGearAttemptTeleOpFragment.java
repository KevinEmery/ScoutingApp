package org.usfirst.frc.team4911.scouting;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordGearAttemptTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordGearAttemptTeleOpFragment extends Fragment
        implements RecordGearAttemptFragment.OnGearAttemptCreatedListener {

    OnGearAttemptTeleopCreatedListener mListener;

    private CheckBox wasDefended;
    private Spinner spinnerGearResult;

    public RecordGearAttemptTeleOpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordGearAttemptTeleOpFragment.
     */
    public static RecordGearAttemptTeleOpFragment newInstance() {
        return new RecordGearAttemptTeleOpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onAttachToParentFragment(getParentFragment());

        // This is what it looks like when I try to do composition with fragments.
        RecordGearAttemptFragment recordGearAttemptFragment =
                (RecordGearAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_gear_attempt_teleop);

        if (recordGearAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_shot_attempt_teleop,
                            RecordShotAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }
    }

    public void onAttachToParentFragment(Fragment fragment)
    {
        try
        {
            mListener = (OnGearAttemptTeleopCreatedListener)fragment;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_gear_attempt_tele_op, container, false);

        wasDefended = (CheckBox) view.findViewById(R.id.checkbox_gear_attempt_tele_op_defended);
        spinnerGearResult = (Spinner) view.findViewById(R.id.spinner_gear_attempt_telop_result);
        spinnerGearResult.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, GearResult.values()));

        return view;
    }

    @Override
    public void onGearAttemptCreated(GearAttempt gearAttempt) {
        GearResult result = GearResult.valueOf(spinnerGearResult.getSelectedItem().toString());

        // We need to override the result with what we saw here and set the was defended flag
        GearAttemptTeleop gearAttemptTeleop = new GearAttemptTeleop(gearAttempt);
        gearAttemptTeleop.setGearResult(result);
        gearAttemptTeleop.setWasDefended(wasDefended.isChecked());

        if (mListener != null) {
            mListener.onGearAttemptTeleopCreated(gearAttemptTeleop);
        }
    }

    /**
     * All activities containing this fragment must implement this interface.
     */
    public interface OnGearAttemptTeleopCreatedListener {
        void onGearAttemptTeleopCreated(GearAttemptTeleop gearAttemptTeleop);
    }
}
