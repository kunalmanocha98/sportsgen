package com.sportsgen.Organiser.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Adapter.AdapterEntryFees;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.Organiser.Models.ModelEntryFees;
import com.sportsgen.R;

import java.util.ArrayList;
import java.util.List;

public class EntryFeesFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RecyclerView rv_entry_fees;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton btn_add_new,btn_remove_last;
    List<ModelEntryFees> list;
    AdapterEntryFees adapter;
    Button btn_submit;
    RadioGroup radioGroup;
    RadioButton btn_free, btn_paid;
    CreateEventData.OnDataEntryListener onDataEntryListener;
    CreateEventData modelalldata;

    public static String OBJECT_KEY="59";

    public static Fragment newInstance(CreateEventData modelalldata){
        Fragment f=new EntryFeesFragment();
        Bundle b=new Bundle();
        b.putParcelable(OBJECT_KEY,modelalldata);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onDataEntryListener=(CreateEventData.OnDataEntryListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_create_event_entry_fees,container,false);
        modelalldata=getArguments().getParcelable(OBJECT_KEY);

        rv_entry_fees=v.findViewById(R.id.rv_entry_fees);
        btn_add_new=v.findViewById(R.id.fab_add);
        btn_remove_last=v.findViewById(R.id.fab_remove);
        btn_add_new.setOnClickListener(this);
        btn_remove_last.setOnClickListener(this);
        layoutManager=new LinearLayoutManager(getContext());
        rv_entry_fees.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        adapter=new AdapterEntryFees(getActivity(),list);
        rv_entry_fees.setAdapter(adapter);
        rv_entry_fees.setNestedScrollingEnabled(true);
        rv_entry_fees.setHasFixedSize(true);
        radioGroup=v.findViewById(R.id.radio_group_entry_fees);
        btn_paid=v.findViewById(R.id.radio_btn_paid);
        btn_free=v.findViewById(R.id.radio_btn_free);
        radioGroup.setOnCheckedChangeListener(this);
        btn_submit=v.findViewById(R.id.btn_submit_categories);
        btn_submit.setOnClickListener(this);
        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata!=null){
            if (modelalldata.getIs_paid()){
                btn_paid.setChecked(true);
            }else{
                btn_free.setChecked(true);
            }

            list=modelalldata.getList_entry_fees();
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_add:{
                addnew_category();
                break;
            }
            case R.id.fab_remove:{
                remove_last_item();
                break;
            }
            case R.id.btn_submit_categories:{
                checkdata();
                break;
            }
        }
    }


    private void remove_last_item() {
        list.remove(list.size()-1);
        adapter.notifyDataSetChanged();
    }

    private void addnew_category() {
        Dialog dialog=new Dialog(getActivity(),R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_entry_fees_category);
        dialog.setCancelable(false);
        TextInputEditText edt_category_name=dialog.findViewById(R.id.edt_category_name);
        TextInputEditText edt_category_price=dialog.findViewById(R.id.edt_category_price);

        if (btn_free.isChecked()){
            edt_category_price.setVisibility(View.GONE);
        }else{
            edt_category_price.setVisibility(View.VISIBLE);
        }

        Button btn_add=dialog.findViewById(R.id.btn_add);
        Button btn_cancel=dialog.findViewById(R.id.btn_cancel);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edt_category_name.getText().toString();
                String price=edt_category_price.getText().toString();
                ModelEntryFees modelEntryFees=new ModelEntryFees();

                if (name.equals("")){
                    edt_category_name.setError("This field is required");
                }else {
                    if (edt_category_price.equals("")){
                        if(btn_free.isChecked()){
                            modelEntryFees.setCategory(name);
                            modelEntryFees.setFees_entry(price);
                            list.add(modelEntryFees);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }else{
                            edt_category_price.setError("This field is required");
                        }
                    }
                }


//                if(name.equals("")){
//
//                }else if (price.equals("")){
//                    edt_category_price.setError("This field is required");
//                }else if (!name.equals("") || !price.equals("")){
//                    modelEntryFees.setCategory(name);
//                    modelEntryFees.setFees_entry(price);
//                    list.add(modelEntryFees);
//                    adapter.notifyDataSetChanged();
//                    dialog.dismiss();
//                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_paid: {
                btn_paid.setTextColor(Color.WHITE);
                btn_free.setTextColor(ContextCompat.getColor(getActivity(), R.color.textprimary));
                break;
            }
            case R.id.radio_btn_free: {
                btn_free.setTextColor(Color.WHITE);
                btn_paid.setTextColor(ContextCompat.getColor(getActivity(), R.color.textprimary));
                break;
            }
        }
    }

    private void checkdata() {
        if (btn_free.isChecked()){
            onDataEntryListener.set_is_paid(false);
        }else if (btn_paid.isChecked()){
            onDataEntryListener.set_is_paid(true);
        }else {
            Utils.toast(getActivity(),"Please select an option");
        }
        onDataEntryListener.set_list_of_categories(list);
        Constants.StringConstants.is_CategoriesData_submitted=true;
        Utils.toast(getActivity(), "Submitted Successfully");
    }

}
