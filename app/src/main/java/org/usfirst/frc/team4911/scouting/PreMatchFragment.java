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

    // Remember when we talked about activity lifecycle and how fragments have their own lifecycle
    // only it's a bit different? This is an example of how they're different. When we're dealing
    // with activities, all the layout initialisation stuff happens in OnCreate. With fragments it
    // it generally happens in OnCreateView. That's because OnCreate for fragments runs when the
    // OnCreate of the activity they belong to runs, but OnCreateView is what runs before the
    // fragment is shown to the user. In this case, OnCreateView runs when the user is scrolling
    // through the tabs and has reached one which has the relevant fragment in it.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        // Note that if you're adding button listeners to a fragment, you need to change this
        // to return a view and then look in that view for all the IDs of the buttons. Not doing
        // this causes lots of crashing and frustration. For an example of what I'm talking about
        // see here (scroll down to the first answer):
        // https://stackoverflow.com/questions/18711433/button-listener-for-button-in-fragment-in-android
        return inflater.inflate(R.layout.fragment_pre_match, container, false);
    }
}
