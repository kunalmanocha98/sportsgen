package com.sportsgen.Organiser.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    Button btn_add_new;
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
        btn_add_new=v.findViewById(R.id.btn_add_new_category);
        btn_add_new.setOnClickListener(this);
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
            case R.id.btn_add_new_category:{
                addnew_category();
                break;
            }
        }
    }

    private void addnew_category() {
        list.add(new ModelEntryFees());
        adapter.notifyDataSetChanged();
    }
}
