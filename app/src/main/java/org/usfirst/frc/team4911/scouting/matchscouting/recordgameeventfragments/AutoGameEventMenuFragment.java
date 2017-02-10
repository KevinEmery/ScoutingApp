package org.usfirst.frc.team4911.scouting.matchscouting.recordgameeventfragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.usfirst.frc.team4911.scouting.R;
import org.usfirst.frc.team4911.scouting.matchscouting.ScoutMatchActivity;
import org.usfirst.frc.team4911.scouting.matchscouting.datamodel.CrossedBaselineEvent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AutoGameEventMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoGameEventMenuFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    private String[] MATCH_EVENTS = new String[] { "Crossed baseline" };
    private ListView mListView;

    public AutoGameEventMenuFragment() {
        // Required empty public constructor
    }

    public static AutoGameEventMenuFragment newInstance() {
        return new AutoGameEventMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_auto_game_event_menu, container, false);
        mListView = (ListView) view.findViewById(R.id.auto_match_event_list_view);
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

        switch (position) {
            case 0:
                CrossedBaselineEvent crossedBaselineEvent = new CrossedBaselineEvent();
                crossedBaselineEvent.setCrossedBaseLine(true);
                ((ScoutMatchActivity)getActivity()).matchData
                        .addAutoMatchEvent(crossedBaselineEvent);
        }
    }
}
