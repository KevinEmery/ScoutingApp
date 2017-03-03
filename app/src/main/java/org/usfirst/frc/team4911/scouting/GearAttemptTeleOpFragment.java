package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GearAttemptTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GearAttemptTeleOpFragment extends Fragment
        implements GearAttemptFragment.OnGearAttemptCreatedListener {

    OnGearAttemptTeleopCreatedListener mListener;

    private CheckBox wasDefended;

    public GearAttemptTeleOpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GearAttemptTeleOpFragment.
     */
    public static GearAttemptTeleOpFragment newInstance() {
        return new GearAttemptTeleOpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onAttachToParentFragment(getParentFragment());

        // This is what it looks like when I try to do composition with fragments.
        GearAttemptFragment gearAttemptFragment =
                (GearAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_gear_attempt_teleop);

        if (gearAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_gear_attempt_teleop,
                            GearAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }
    }

    private void onAttachToParentFragment(Fragment fragment)
    {
        try
        {
            mListener = (OnGearAttemptTeleopCreatedListener)fragment;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnGearAttemptTeleopCreatedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gear_attempt_tele_op, container, false);

        wasDefended = (CheckBox) view.findViewById(R.id.checkbox_gear_attempt_tele_op_defended);

        return view;
    }

    @Override
    public void onGearAttemptCreated(GearAttempt gearAttempt) {

        // We need to override the result with what we saw here and set the was defended flag
        GearAttemptTeleop gearAttemptTeleop = new GearAttemptTeleop(gearAttempt);
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
