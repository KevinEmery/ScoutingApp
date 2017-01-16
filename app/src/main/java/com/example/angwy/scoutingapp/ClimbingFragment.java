package com.example.angwy.scoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClimbingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClimbingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClimbingFragment extends Fragment {

    public ClimbingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClimbingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClimbingFragment newInstance() {
        ClimbingFragment fragment = new ClimbingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_climbing, container, false);
    }
}
