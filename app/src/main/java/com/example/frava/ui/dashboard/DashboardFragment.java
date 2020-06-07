package com.example.frava.ui.dashboard;

import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frava.MyTools;
import com.example.frava.R;
import com.example.frava.RecyclerItemClickListener;
import com.example.frava.SegmentsAdapter;

import static androidx.core.content.ContextCompat.getSystemService;

public class DashboardFragment extends Fragment {

    //private DashboardViewModel dashboardViewModel;
    private String[] myDataset;

    LayoutInflater m_inflater;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        m_inflater = inflater;
        View root = m_inflater.inflate(R.layout.fragment_dashboard, container, false);

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
