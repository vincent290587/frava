package com.example.frava.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frava.ExtendedSummarySegment;
import com.example.frava.MyTools;
import com.example.frava.R;
import com.example.frava.RecyclerItemClickListener;
import com.example.frava.SegmentsAdapter;
import com.example.frava.StravaManager;

import java.util.List;

import io.swagger.client.model.Route;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    private StravaManager stravaViewModel;

    LayoutInflater m_inflater;

    int m_last_position;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        m_inflater = inflater;

        stravaViewModel = new ViewModelProvider(getActivity()).get(StravaManager.class);
        Log.i(TAG, "getActivity" + getActivity().toString());

        stravaViewModel.m_routes_list.observe(getViewLifecycleOwner(), new Observer<List<Route>>() {
            @Override
            public void onChanged(@Nullable List<Route> route) {
                Log.i(TAG, "Routes changed");
                mAdapter.notifyDataSetChanged();
            }
        });

        stravaViewModel.m_seg_list.observe(getViewLifecycleOwner(), new Observer<List<ExtendedSummarySegment>>() {
            @Override
            public void onChanged(@Nullable List<ExtendedSummarySegment> segments) {
                if (segments != null) {
                    Log.i(TAG, "segments changed: " + segments.size());
                } else {
                    Log.i(TAG, "segments changed");
                }
            }
        });

        View root = m_inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.segments_recycler);
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layout_manager);

        // specify an adapter (see also next example)
        mAdapter = new SegmentsAdapter(stravaViewModel.m_routes_list.getValue());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                m_last_position = position;

                // inflate the layout of the popup window
                View popupView = m_inflater.inflate(R.layout.route_popup, null);

                // create the popup window
                final PopupWindow popupWindow = new PopupWindow(popupView, MyTools.fromDp(300), MyTools.fromDp(200), true);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window token
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                Button button_cancel = popupView.findViewById(R.id.button_cancel);
                button_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });

                Button button_send = popupView.findViewById(R.id.button_send);
                button_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        stravaViewModel.prepareRoute(m_last_position);
                    }
                });

                Log.i("HomeFragment", "onItemClick " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.i("HomeFragment", "onItemLongClick " + position);
            }
        }));

        return root;
    }
}
