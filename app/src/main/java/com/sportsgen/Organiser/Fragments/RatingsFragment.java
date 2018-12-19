package com.sportsgen.Organiser.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsgen.R;
import com.sportsgen.User.Fragments.TournamentFragment;

public class RatingsFragment extends Fragment {
    public static Fragment newInstance(){
        Fragment f=new RatingsFragment();
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_organiser_rating,container,false);
        return v;
    }
}
