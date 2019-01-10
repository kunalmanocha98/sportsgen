package com.sportsgen.Organiser.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

public class NamesportDesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    RadioGroup radioGroup;
    RadioButton btn_tournament, btn_screening;
    Button btn_submit;
    TextInputEditText edt_name, edt_sport, edt_description;
    CreateEventData modelalldata;
    CreateEventData.OnDataEntryListener listener;
    public static String LISTENER_KEY = "54";
    public static String OBJECT_KEY = "55";


    public static Fragment newInstance(CreateEventData.OnDataEntryListener listener, CreateEventData modelalldata) {
        Fragment f = new NamesportDesFragment();
        Bundle b = new Bundle();
        b.putSerializable(LISTENER_KEY, listener);
        b.putSerializable(OBJECT_KEY, modelalldata);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event_name_sport_des, container, false);
        modelalldata = (CreateEventData) getArguments().getSerializable(OBJECT_KEY);
        listener = (CreateEventData.OnDataEntryListener) getArguments().getSerializable(LISTENER_KEY);

        radioGroup = v.findViewById(R.id.radio_group);
        btn_tournament = v.findViewById(R.id.radio_btn_Tournament);
        btn_screening = v.findViewById(R.id.radio_btn_screenings);
        edt_name = v.findViewById(R.id.edt_name);
        edt_sport = v.findViewById(R.id.edt_sport);
        edt_description = v.findViewById(R.id.edt_description);
        btn_submit=v.findViewById(R.id.btn_submit_name_sport);
        btn_submit.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata!=null){
            if (modelalldata.getEventtype() != null){
                if (modelalldata.getEventtype().equals("Tournament")){
                    btn_tournament.setChecked(true);
                    btn_screening.setChecked(false);
                }else{
                    btn_screening.setChecked(true);
                    btn_tournament.setChecked(false);
                }
            }
            if (modelalldata.getEventname() != null){
                edt_name.setText(modelalldata.getEventname());
            }
            if(modelalldata.getEventSport() != null){
                edt_sport.setText(modelalldata.getEventSport());
            }
            if(modelalldata.getEventDescription() != null){
                edt_description.setText(modelalldata.getEventDescription());
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_Tournament: {
                btn_tournament.setTextColor(Color.WHITE);
                btn_screening.setTextColor(ContextCompat.getColor(getActivity(), R.color.textprimary));
                break;
            }
            case R.id.radio_btn_screenings: {
                btn_screening.setTextColor(Color.WHITE);
                btn_tournament.setTextColor(ContextCompat.getColor(getActivity(), R.color.textprimary));
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_name_sport: {
                checkdata();
                break;
            }
        }
    }

    private void checkdata() {
        if (btn_tournament.isChecked() || btn_screening.isChecked()) {
            if (!edt_name.getText().toString().equals("")) {
                if (!edt_sport.getText().toString().equals("")) {
                    if (!edt_description.getText().toString().equals("")) {
                        proceedsubmit();
                    } else {
                        edt_description.setError("This Field is Required");
                    }
                } else {
                    edt_sport.setError("This Field is Required");
                }
            } else {
                edt_name.setError("This Field is Required");
            }
        } else {
            Utils.toast(getActivity(), "Select an event type");
        }
    }

    private void proceedsubmit() {
        if (btn_tournament.isChecked()) {
            listener.setEventType("Tournament");
        } else {
            listener.setEventType("Screening");
        }
        listener.setEventName(edt_name.getText().toString());
        listener.setEventSport(edt_sport.getText().toString());
        listener.setEventDescription(edt_description.getText().toString());
        Constants.StringConstants.is_NameData_Submited=true;
        Utils.toast(getActivity(),"Submitted Successfully");
    }
}
