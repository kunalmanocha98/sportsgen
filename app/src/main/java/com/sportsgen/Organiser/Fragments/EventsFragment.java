package com.sportsgen.Organiser.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.sportsgen.R;
import com.sportsgen.User.Fragments.TournamentFragment;

public class EventsFragment extends Fragment {
    public static Fragment newInstance(){
        Fragment f=new EventsFragment();
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_organiser_events,container,false);
        SpeedDialView speedDialView=v.findViewById(R.id.fab_speed_dial);
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_long_label, R.drawable.ic_add_white_24dp)
                        .setLabel("Label 1")
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_long_label, R.drawable.ic_add_white_24dp)
                        .setLabel("Label 2")
                        .create()
        );
        return v;
    }
}
