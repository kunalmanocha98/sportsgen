package com.sportsgen.Organiser.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sportsgen.Organiser.Adapter.AdapterEntryFees;
import com.sportsgen.Organiser.Models.ModelEntryFees;
import com.sportsgen.R;

import java.util.ArrayList;
import java.util.List;

public class EntryFeesFragment extends Fragment implements View.OnClickListener {
    RecyclerView rv_entry_fees;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton btn_add_new,btn_remove_last;
    List<ModelEntryFees> list;
    AdapterEntryFees adapter;
    public static Fragment newInstance(){
        Fragment f=new EntryFeesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_create_event_entry_fees,container,false);
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
        return v;
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
        Button btn_add=dialog.findViewById(R.id.btn_add);
        Button btn_cancel=dialog.findViewById(R.id.btn_cancel);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edt_category_name.getText().toString();
                String price=edt_category_price.getText().toString();
                if(name.equals("")){
                    edt_category_name.setError("This field is required");
                }else if (price.equals("")){
                    edt_category_price.setError("This field is required");
                }else if (!name.equals("") || !price.equals("")){
                    ModelEntryFees modelEntryFees=new ModelEntryFees();
                    modelEntryFees.setCategory(name);
                    modelEntryFees.setFees_entry(price);
                    list.add(modelEntryFees);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

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
}
