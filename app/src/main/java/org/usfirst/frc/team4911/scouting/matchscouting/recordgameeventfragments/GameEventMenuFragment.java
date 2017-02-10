package org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.support.v4.app.FragmentManager;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.FragmentChangeListener;


/**
 * Fragment that contains the first menu that shows when you push the field button.
 */
public class GameEventMenuFragment extends DialogFragment implements OnItemClickListener {

    private String[] MATCH_EVENTS = new String[] { "Climbing", "Shooting" };
    private ListView mListView;

    public GameEventMenuFragment() {
        // Required empty public constructor
    }

    public static GameEventMenuFragment newInstance() {
        return new GameEventMenuFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game_event_menu, container, false);
        mListView = (ListView) view.findViewById(R.id.match_event_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, MATCH_EVENTS);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // This is the thing that hides the list
        dismiss();
        FragmentManager fm = getActivity().getSupportFragmentManager();

        switch (position) {
            case 0:
                ClimbingFragment climbingFragment = new ClimbingFragment();
                climbingFragment.show(fm, "Dialog Fragment");
                return;
            case 1:
                ShootingFragment fragment = new ShootingFragment();
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener) getActivity();
                fragmentChangeListener.replaceFragment(fragment);
                return;
        }
    }
}
