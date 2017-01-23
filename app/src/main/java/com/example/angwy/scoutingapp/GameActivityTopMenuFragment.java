package com.example.angwy.scoutingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameActvityTopMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameActvityTopMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameActivityTopMenuFragment extends Fragment {
    public GameActivityTopMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GameActvityTopMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameActivityTopMenuFragment newInstance() {
        GameActivityTopMenuFragment fragment = new GameActivityTopMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_actvity_top_menu, container, false);
    }
}
