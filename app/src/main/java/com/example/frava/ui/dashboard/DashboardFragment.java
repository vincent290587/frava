package com.example.frava.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frava.R;
import com.example.frava.RecyclerItemClickListener;
import com.example.frava.SegmentsAdapter;

public class DashboardFragment extends Fragment {

    //private DashboardViewModel dashboardViewModel;
    private String[] myDataset;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.segments_recycler);
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layout_manager);

        myDataset = new String[70];
        myDataset[0] = "item1";
        for (int i=0; i < 70; i++) {
            myDataset[i] = "test " + i;
        }

        // specify an adapter (see also next example)
        mAdapter = new SegmentsAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        // TODO mAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("HomeFragment", "onItemClick " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.i("HomeFragment", "onItemLongClick " + position);
            }
        }));

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//                //Toast.makeText(this, "Text changed", Toast.LENGTH_SHORT);
//            }
//        });
        return root;
    }
}
