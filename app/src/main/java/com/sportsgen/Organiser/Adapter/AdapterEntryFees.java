package com.sportsgen.Organiser.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsgen.Organiser.Fragments.EntryFeesFragment;
import com.sportsgen.Organiser.Models.ModelEntryFees;
import com.sportsgen.R;

import java.util.List;

public class AdapterEntryFees extends RecyclerView.Adapter<AdapterEntryFees.ViewHolder> {
    Context context;
    List<ModelEntryFees> list;
    public AdapterEntryFees(Context context, List<ModelEntryFees> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_entry_fees,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
