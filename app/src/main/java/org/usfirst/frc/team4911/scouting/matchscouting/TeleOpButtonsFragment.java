package org.usfirst.frc.team4911.scouting.matchscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.usfirst.frc.team4911.scouting.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeleOpButtonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeleOpButtonsFragment extends Fragment {
    Button buttonMatchEnd;

    public TeleOpButtonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AutoButtonsFragment.
     */
    public static TeleOpButtonsFragment newInstance() {
        return new TeleOpButtonsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tele_op_buttons, container, false);

        buttonMatchEnd = (Button) view.findViewById(R.id.buttonMatchEndTeleOp);

        buttonMatchEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FinishMatchFragment finishMatchFragment = new FinishMatchFragment();
                finishMatchFragment.show(fm, "Dialog Fragment");
            }
        });

        return view;
    }
}
