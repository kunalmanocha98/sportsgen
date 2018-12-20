package com.sportsgen.Organiser.Fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leinardi.android.speeddial.FabWithLabelView;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.sportsgen.R;

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

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_long_label, R.drawable
                .ic_add_white_24dp)
                .setLabel("label 1")
                .create());

        Drawable drawable2 = AppCompatResources.getDrawable(getActivity(), R.drawable.ic_add_white_24dp);
        FabWithLabelView fabWithLabelView2 = speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id
                .fab_custom_color, drawable2)
                .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.colorwhite,null))
                .setLabel("label 2")
                .setLabelColor(Color.BLACK)
                .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorwhite,null))
                .create());

        return v;
    }
}
