package com.sportsgen.Organiser.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sportsgen.R;

public class NamesportDesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    RadioGroup radioGroup;
    RadioButton btn_tournament,btn_screening;
    public static Fragment newInstance(){
        Fragment f=new NamesportDesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_create_event_name_sport_des,container,false);
        radioGroup=v.findViewById(R.id.radio_group);
        btn_tournament=v.findViewById(R.id.radio_btn_Tournament);
        btn_screening=v.findViewById(R.id.radio_btn_screenings);
        radioGroup.setOnCheckedChangeListener(this);
        return v;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_btn_Tournament:{
                btn_tournament.setTextColor(Color.WHITE);
                btn_screening.setTextColor(ContextCompat.getColor(getActivity(),R.color.textprimary));
                break;
            }
            case R.id.radio_btn_screenings:{
                btn_screening.setTextColor(Color.WHITE);
                btn_tournament.setTextColor(ContextCompat.getColor(getActivity(),R.color.textprimary));
                break;
            }
        }
    }
}
