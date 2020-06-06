package com.example.frava.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;

    private String[] myDataset;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.segments_recycler);
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout_manager);

        myDataset = new String[5];
        myDataset[0] = "test 01";
        myDataset[1] = "test 02";
        myDataset[2] = "test 03";
        myDataset[3] = "test 04";
        myDataset[4] = "test 05";

        // specify an adapter (see also next example)
        mAdapter = new SegmentsAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

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
