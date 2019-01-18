package com.sportsgen.Organiser.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

public class ExtraDetailsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    CheckBox checkBox_reg,checkBox_hrs,checkBox_jersey,checkBox_certi;
    TextView txt_reg_number,txt_hours_before;
    public static String OBJECT_KEY="52";
    public static Fragment newInstance(CreateEventData modelalldata){
        Fragment f=new ExtraDetailsFragment();
        Bundle b=new Bundle();
        b.putParcelable(OBJECT_KEY,modelalldata);
        f.setArguments(b);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_extra_details,container,false);
        checkBox_reg=v.findViewById(R.id.checkbox_reg);
        checkBox_reg.setOnCheckedChangeListener(this);
        checkBox_hrs=v.findViewById(R.id.checkbox_hours);
        checkBox_hrs.setOnCheckedChangeListener(this);
        checkBox_jersey=v.findViewById(R.id.checkbox_custom_jersey);
        checkBox_jersey.setOnCheckedChangeListener(this);
        checkBox_certi=v.findViewById(R.id.checkbox_custom_certificate);
        checkBox_certi.setOnCheckedChangeListener(this);
        txt_hours_before=v.findViewById(R.id.txt_hours_beforeevent_closes);
        txt_reg_number=v.findViewById(R.id.txt_limited_registration);
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
