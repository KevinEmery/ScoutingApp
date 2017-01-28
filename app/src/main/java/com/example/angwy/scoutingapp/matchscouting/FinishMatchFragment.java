package com.example.angwy.scoutingapp.matchscouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angwy.scoutingapp.R;

/**
 * The last of the fragments in the match scouting cycle.
 * Collects the few pieces of subjective data and does the file write.
 * Use the {@link FinishMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishMatchFragment extends Fragment {

    public FinishMatchFragment() {
        // Required empty public constructor
    }

    public static FinishMatchFragment newInstance() {
        return new FinishMatchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish_match, container, false);
    }
}
