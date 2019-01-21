package com.sportsgen.Organiser.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

public class ExtraDetailsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    CheckBox checkBox_reg, checkBox_hrs, checkBox_jersey, checkBox_certi;
    TextView txt_reg_number, txt_hours_before;
    public static String OBJECT_KEY = "52";
    CreateEventData.OnDataEntryListener onDataEntryListener;
    CreateEventData modelalldata;
    Button btn_submit;

    public static Fragment newInstance(CreateEventData modelalldata) {
        Fragment f = new ExtraDetailsFragment();
        Bundle b = new Bundle();
        b.putParcelable(OBJECT_KEY, modelalldata);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onDataEntryListener = (CreateEventData.OnDataEntryListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_extra_details, container, false);
        modelalldata = getArguments().getParcelable(OBJECT_KEY);
        checkBox_reg = v.findViewById(R.id.checkbox_reg);
        checkBox_reg.setChecked(false);
        checkBox_reg.setOnCheckedChangeListener(this);
        checkBox_hrs = v.findViewById(R.id.checkbox_hours);
        checkBox_hrs.setChecked(true);
        checkBox_hrs.setOnCheckedChangeListener(this);
        checkBox_jersey = v.findViewById(R.id.checkbox_custom_jersey);
        checkBox_jersey.setChecked(false);
        checkBox_jersey.setOnCheckedChangeListener(this);
        checkBox_certi = v.findViewById(R.id.checkbox_custom_certificate);
        checkBox_certi.setChecked(false);
        checkBox_certi.setOnCheckedChangeListener(this);
        txt_hours_before = v.findViewById(R.id.txt_hours_beforeevent_closes);
        txt_reg_number = v.findViewById(R.id.txt_limited_registration);
        txt_hours_before.setVisibility(View.GONE);
        txt_reg_number.setVisibility(View.GONE);
        btn_submit = v.findViewById(R.id.btn_submit_extras);
        btn_submit.setOnClickListener(this);

        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata != null) {
            if (modelalldata.getIs_limited_reg() != null) {
                if (modelalldata.getIs_limited_reg()) {
                    checkBox_reg.setChecked(true);
                    txt_reg_number.setVisibility(View.VISIBLE);
                    txt_reg_number.setText(modelalldata.getReg_no());
                } else {
                    checkBox_reg.setChecked(false);
                }
                if (modelalldata.getIs_24_hours()) {
                    checkBox_hrs.setChecked(true);
                } else {
                    checkBox_hrs.setChecked(false);
                    txt_hours_before.setText(modelalldata.getHrs_before());
                }
                if (modelalldata.getIs_custom_tshirts()) {
                    checkBox_jersey.setChecked(true);
                } else {
                    checkBox_jersey.setChecked(false);
                }
                if (modelalldata.getIs_custom_certi()) {
                    checkBox_certi.setChecked(true);
                } else {
                    checkBox_certi.setChecked(false);
                }
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_reg: {
                if (isChecked) {
                    Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
                    dialog.setContentView(R.layout.dialog_limited_registration);
                    dialog.setCancelable(false);
                    TextInputEditText edt_ltd_reg = dialog.findViewById(R.id.edt_submit_reg);
                    Button btn_submit = dialog.findViewById(R.id.submit_limited_reg);
                    btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (edt_ltd_reg.getText().toString().equals("")) {
                                edt_ltd_reg.setError("Please enter some value");
                                txt_reg_number.setVisibility(View.GONE);
//                                Utils.toast(getActivity(),"Please enter some value")
                            } else {
                                txt_reg_number.setVisibility(View.VISIBLE);
                                txt_reg_number.setText(edt_ltd_reg.getText().toString());
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                } else {
                    txt_reg_number.setVisibility(View.GONE);
                }
                break;
            }
            case R.id.checkbox_hours: {
                if (isChecked) {
                    txt_hours_before.setVisibility(View.GONE);
                } else {
                    Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
                    dialog.setContentView(R.layout.dialog_limited_registration);
                    dialog.setCancelable(false);
                    TextInputEditText edt_ltd_reg = dialog.findViewById(R.id.edt_submit_reg);
                    edt_ltd_reg.setHint("Enter number of hours");
                    Button btn_submit = dialog.findViewById(R.id.submit_limited_reg);
                    btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (edt_ltd_reg.getText().toString().equals("")) {
                                edt_ltd_reg.setError("Please enter some value");
                                txt_hours_before.setVisibility(View.GONE);
//                                Utils.toast(getActivity(),"Please enter some value")
                            } else {
                                txt_hours_before.setVisibility(View.VISIBLE);
                                txt_hours_before.setText(edt_ltd_reg.getText().toString());
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();


                }
                break;
            }
            case R.id.checkbox_custom_jersey: {
                if (isChecked) {

                } else {

                }
                break;
            }
            case R.id.checkbox_custom_certificate: {
                if (isChecked) {

                } else {

                }
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_extras: {
                checkdata();
                break;
            }
        }
    }

    private void checkdata() {
        if (checkBox_reg.isChecked()) {
            onDataEntryListener.set_is_ltd_reg(true);
            onDataEntryListener.set_ltd_reg(txt_reg_number.getText().toString());
        } else {
            onDataEntryListener.set_is_ltd_reg(false);
        }


        if (checkBox_hrs.isChecked()) {
            onDataEntryListener.set_is_hrs(true);
            onDataEntryListener.set_ltd_hrs(txt_reg_number.getText().toString());
        } else {
            onDataEntryListener.set_is_hrs(false);
        }


        if (checkBox_jersey.isChecked()) {
            onDataEntryListener.set_is_custom_jersey(false);
        } else {
            onDataEntryListener.set_is_custom_jersey(false);
        }


        if (checkBox_certi.isChecked()) {
            onDataEntryListener.set_is_custom_certi(false);
        } else {
            onDataEntryListener.set_is_custom_certi(false);
        }

        Constants.StringConstants.is_Extra_Details_Data_submitted = true;
        Utils.toast(getActivity(), "Submitted Successfully");
    }
}
