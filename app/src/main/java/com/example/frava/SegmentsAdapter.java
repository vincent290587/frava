package com.example.frava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.swagger.client.model.Route;

public class SegmentsAdapter extends RecyclerView.Adapter<SegmentsAdapter.ViewHolder>  {

    private List<Route> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SegmentsAdapter(List<Route> myDataset) {
        mDataset = myDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final TextView s_name;
        public final TextView s_length;
        public final ProgressBar s_progress;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = itemView;
            this.s_name = itemView.findViewById(R.id.segment_name);
            this.s_length = itemView.findViewById(R.id.segment_km);
            this.s_progress = itemView.findViewById(R.id.progressBar);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.segment_item, parent, false);

        return new ViewHolder(v);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // TODO fill data per item knowing position
        holder.s_progress.setVisibility(View.INVISIBLE);
        holder.s_name.setText(mDataset.get(position).getName());
        holder.s_length.setText(mDataset.get(position).getDistance().toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }
}
