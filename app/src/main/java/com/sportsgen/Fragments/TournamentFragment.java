package com.sportsgen.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsgen.R;

public class TournamentFragment extends Fragment {

    public static Fragment newInstance(){
        Fragment f=new TournamentFragment();
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_tournaments,container,false);

        return v;
    }
}
