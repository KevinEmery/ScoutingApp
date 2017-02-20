package org.usfirst.frc.team4911.scouting;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostMatchFragment extends Fragment {

    public PostMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PostMatchFragment.
     */
    public static PostMatchFragment newInstance() {
        return new PostMatchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // See note in the OnCreateView method of PreMatchFragment
        View view = inflater.inflate(R.layout.fragment_post_match, container, false);
        Button location = (Button) view.findViewById(R.id.btn_climbing_location);
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
            DialogFragment fieldMapFragment =
                    RecordLocationFragment.newInstance(R.drawable.airship_blue);
            fieldMapFragment.show(fragmentManager, "DialogFragment");
        }
    };
}
