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
import com.example.smartgarage.R;

import java.util.ArrayList;
import java.util.List;

//public class MechanicianAdapter {
//}
public class MechanicianAdapter extends RecyclerView.Adapter<MechanicianAdapter.ExampleViewHolder> implements Filterable {

    public interface OnItemClickListener {
        void onItemClick(Mechanician item);
    }

    private final OnItemClickListener listener;

    private List<Mechanician> exampleList;
    private List<Mechanician> exampleListFull;

    public MechanicianAdapter(List<Mechanician> exampleList, OnItemClickListener listener) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
        this.listener = listener;
    }

//    public MechanicianAdapter(List<Mechanician> exampleList) {
//        this.exampleList = exampleList;
//        exampleListFull = new ArrayList<>(exampleList);
//    }

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;

        ExampleViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.txv_names);
            textView2 = itemView.findViewById(R.id.txv_address);
        }

        public void bind(final Mechanician mechanician,final OnItemClickListener listener) {
            textView1.setText(mechanician.getNames());
            textView2.setText(mechanician.getAddress());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(mechanician);
                }
            });
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
        Mechanician currentItem = exampleList.get(position);

        holder.bind(exampleList.get(position), listener);

//        holder.textView1.setText(currentItem.getNames());
//        holder.textView2.setText(currentItem.getAddress());


    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Mechanician> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Mechanician item : exampleListFull) {
                    if (item.getAddress().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
