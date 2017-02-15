package org.usfirst.frc.team4911.scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link PreMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreMatchFragment extends Fragment {

    public PreMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PreMatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreMatchFragment newInstance() {
        return new PreMatchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_match, container, false);
    }
}
