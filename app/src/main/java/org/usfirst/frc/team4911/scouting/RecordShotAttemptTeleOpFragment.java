package org.usfirst.frc.team4911.scouting;

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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.usfirst.frc.team4911.scouting.datamodel.FuelAmount;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAccuracy;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.ShotMode;
import org.usfirst.frc.team4911.scouting.datamodel.ShotSpeed;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordShotAttemptTeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordShotAttemptTeleOpFragment extends Fragment {

    public RecordShotAttemptTeleOpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordShotAttemptTeleOpFragment.
     */
    public static RecordShotAttemptTeleOpFragment newInstance() {
        return new RecordShotAttemptTeleOpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecordShotAttemptFragment recordShotAttemptFragment =
                (RecordShotAttemptFragment) getChildFragmentManager()
                        .findFragmentById(R.id.fragment_container_shot_attempt_teleop);

        if (recordShotAttemptFragment == null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_shot_attempt_teleop,
                            RecordShotAttemptFragment.newInstance());
            fragmentTransaction.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_shot_attempt_tele_op, container, false);

        // I'm going for composition here so we're adding a auto shot attempt fragment and hoping
        // that works
        return view;
    }
}
