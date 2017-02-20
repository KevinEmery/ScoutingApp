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

/**
 * Code for the fragment which handles recording gear events.
 * Use the {@link RecordGearEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordGearEventFragment extends Fragment {

    public RecordGearEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecordGearEventFragment.
     */
    public static RecordGearEventFragment newInstance() {
        return new RecordGearEventFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_gear_event, container, false);
        Button location = (Button) view.findViewById(R.id.btn_gear_record_location);

        // Add an onclick listener for the location button
        location.setOnClickListener(handleBtnPress);

        return view;
    }

    /**
     * OnTouchListener for the location button which invites the user to note down the location
     * of a gear event.
     */
    private View.OnClickListener handleBtnPress = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getChildFragmentManager();
            DialogFragment fieldMapFragment = RecordLocationFragment.newInstance(
                    ((ScoutMatchActivity) getActivity()).getAlliance(), LocationMapType.AIRSHIP);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };
}