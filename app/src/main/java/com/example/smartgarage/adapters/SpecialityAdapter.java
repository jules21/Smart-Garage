package com.example.smartgarage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.Model.Speciality;
import com.example.smartgarage.R;

import java.util.ArrayList;
import java.util.List;

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.ExampleViewHolder> {

    private List<Speciality> exampleList;


    public SpecialityAdapter(List<Speciality> exampleList) {
        this.exampleList = exampleList;
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;

        ExampleViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.txv_names);
            textView2 = itemView.findViewById(R.id.txv_address);
        }
    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mech_list_item,
                parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Speciality currentItem = exampleList.get(position);

        holder.textView1.setText(currentItem.getName());
        holder.textView2.setText(currentItem.getDescription());    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

}