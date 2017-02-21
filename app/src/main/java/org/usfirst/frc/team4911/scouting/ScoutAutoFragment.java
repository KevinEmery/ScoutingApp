package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutAutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutAutoFragment extends Fragment {
    CheckBox crossedBaseline;
    CheckBox loadedFromHopper;

    public ScoutAutoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScoutAutoFragment.
     */
    public static ScoutAutoFragment newInstance() {
        return new ScoutAutoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreGameFragment
        View view = inflater.inflate(R.layout.fragment_scout_auto, container, false);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        RecordShootingAttemptFragment shootingEventFragment =
                RecordShootingAttemptFragment.newInstance();
        RecordGearAttemptFragment recordGearAttemptFragment = RecordGearAttemptFragment.newInstance();

        fragmentTransaction.add(R.id.auto_shooting_fragment_container, shootingEventFragment);
        fragmentTransaction.add(R.id.auto_gear_fragment_container, recordGearAttemptFragment);
        fragmentTransaction.commit();

        // Initialise the auto points and hopper hit properties to have default values of false
        ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                .getAutonomousPeriod().setAutoMobilityPoints(false);
        ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                .getAutonomousPeriod().setLoadedFromHopper(false);

        crossedBaseline = (CheckBox) view.findViewById(R.id.chkbx_auto_crossed_baseline);
        crossedBaseline.setOnClickListener(crossedBaselineListener);

        loadedFromHopper = (CheckBox) view.findViewById(R.id.chkbx_auto_loaded_from_hopper);
        loadedFromHopper.setOnClickListener(loadedFromHopperListener);

        return view;
    }

    View.OnClickListener crossedBaselineListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(crossedBaseline.isChecked()){
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setAutoMobilityPoints(true);
            }else{
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setAutoMobilityPoints(false);
            }
        }
    };

    View.OnClickListener loadedFromHopperListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(loadedFromHopper.isChecked()){
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setLoadedFromHopper(true);
            }else{
                ((ScoutMatchActivity) getActivity()).getScoutingData().getMatchData()
                        .getAutonomousPeriod().setLoadedFromHopper(false);
            }
        }
    };
}
