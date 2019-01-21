package com.sportsgen.Organiser.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.CommonClasses.TextEditorActivity;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

public class NamesportDesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{
    RadioGroup radioGroup;
    RadioButton btn_tournament, btn_screening;
    Button btn_submit;
    TextInputEditText edt_name, edt_sport, edt_description;
    TextView write_description;
    CreateEventData modelalldata;
    CreateEventData.OnDataEntryListener onDataEntryListener;
    public static String LISTENER_KEY = "54";
    public static String OBJECT_KEY = "55";
    private String des_text;


    public static Fragment newInstance(CreateEventData modelalldata) {
        Fragment f = new NamesportDesFragment();
        Bundle b = new Bundle();
        b.putParcelable(OBJECT_KEY,modelalldata);
        f.setArguments(b);
        return f;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateEventData.OnDataEntryListener){
            onDataEntryListener=(CreateEventData.OnDataEntryListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event_name_sport_des, container, false);
        modelalldata = (CreateEventData) getArguments().getParcelable(OBJECT_KEY);

        radioGroup = v.findViewById(R.id.radio_group);
        btn_tournament = v.findViewById(R.id.radio_btn_Tournament);
        btn_screening = v.findViewById(R.id.radio_btn_screenings);
        edt_name = v.findViewById(R.id.edt_name);
        edt_sport = v.findViewById(R.id.edt_sport);
        btn_submit = v.findViewById(R.id.btn_submit_name_sport);
        write_description = v.findViewById(R.id.description_status);
        write_description.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata != null) {
            if (modelalldata.getEventtype() != null) {
                if (modelalldata.getEventtype().equals("Tournament")) {
                    btn_tournament.setChecked(true);
                    btn_screening.setChecked(false);
                } else {
                    btn_screening.setChecked(true);
                    btn_tournament.setChecked(false);
                }
            }
            if (modelalldata.getEventname() != null) {
                edt_name.setText(modelalldata.getEventname());
            }
            if (modelalldata.getEventSport() != null) {
                edt_sport.setText(modelalldata.getEventSport());
            }
            if (modelalldata.getEventDescription() != null) {
//                write_description.setText(modelalldata.getEventDescription());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    write_description.setText(Html.fromHtml(modelalldata.getEventDescription(),Html.FROM_HTML_MODE_LEGACY));
                }else {
                    write_description.setText(Html.fromHtml(des_text));
                }
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
            case R.id.description_status:{
                openEditor();
            }
        }
    }

    private void openEditor() {
        Intent i= new Intent(getActivity(),TextEditorActivity.class);
        startActivityForResult(i,Constants.IntegerConstants.DESCRIPTION_RESULT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==Constants.IntegerConstants.DESCRIPTION_RESULT){
            if (resultCode==getActivity().RESULT_OK){
                des_text=data.getStringExtra(Constants.StringConstants.HTML_TEXT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    write_description.setText(Html.fromHtml(des_text,Html.FROM_HTML_MODE_LEGACY));
                }else {
                    write_description.setText(Html.fromHtml(des_text));
                }
            }
        }
    }

    private void checkdata() {
        if (btn_tournament.isChecked() || btn_screening.isChecked()) {
            if (!edt_name.getText().toString().equals("")) {
                if (!edt_sport.getText().toString().equals("")) {
                    if (!write_description.getText().toString().equals("")) {
                        proceedsubmit();
                    } else {
//                        edt_description.setError("This Field is Required");
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
            onDataEntryListener.setEventType("Tournament");
        } else {
            onDataEntryListener.setEventType("Screening");
        }
        onDataEntryListener.setEventName(edt_name.getText().toString());
        onDataEntryListener.setEventSport(edt_sport.getText().toString());
        onDataEntryListener.setEventDescription(des_text);
        Constants.StringConstants.is_NameData_Submited = true;
        Utils.toast(getActivity(), "Submitted Successfully");
    }
}
