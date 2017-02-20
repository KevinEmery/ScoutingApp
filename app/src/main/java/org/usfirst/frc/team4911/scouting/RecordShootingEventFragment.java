package org.usfirst.frc.team4911.scouting;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Contains all data interfaces necessary to collect information about a shooting event.
 * Intended for use in auto and teleop scouting events.
 * Use the {@link RecordShootingEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordShootingEventFragment extends DialogFragment {

    public RecordShootingEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment RecordShootingEventFragment.
     */
    public static RecordShootingEventFragment newInstance() {
        return new RecordShootingEventFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_shooting_event, container, false);

        Button location = (Button) view.findViewById(R.id.btn_shooting_record_location);

        // Add an onclick listener for the location button
        location.setOnClickListener(handleBtnPress);

        return view;
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a shooting event.
     */
    private View.OnClickListener handleBtnPress = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment =
                    RecordLocationFragment.newInstance(R.drawable.shootingzone_blue);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };
}
