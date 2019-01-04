package com.sportsgen.Organiser.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsgen.R;

public class DateandTimeFragment extends Fragment {
    public static Fragment newInstance(){
        Fragment f=new DateandTimeFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_create_event_date_time,container,false);
        return v;
    }
}
