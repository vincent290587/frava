package com.example.frava.ui.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.frava.ExtendedSummarySegment;
import com.example.frava.MainActivity;
import com.example.frava.OAuthStarter;
import com.example.frava.R;
import com.example.frava.StravaManager;
import com.example.frava.StravaQueries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.swagger.client.model.Route;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private HomeViewModel homeViewModel;
    private StravaManager stravaViewModel;

    private StravaTask stravaTask;
    private HomeFragment thisFragment;

    private ProgressBar bar;

    private void logText(String s) {
        if (s == null) {
            return;
        }
//        String old_text = homeViewModel.getText().getValue();
//        if (old_text == null) {
//            old_text = "";
//        }
        String new_text = "  \n\n\n\n" + s;
//        new_text += "\n";
//        new_text += old_text;
        homeViewModel.mText.postValue(new_text);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        stravaViewModel = new ViewModelProvider(getActivity()).get(StravaManager.class);
        Log.d(TAG, "getActivity" + getActivity().toString());

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        bar = root.findViewById(R.id.progressBar2);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        stravaViewModel.m_nb_segs.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer nb_segments) {
                Log.i(TAG, "segments changed");
                logText("Getting starred segments...");
                bar.setMax(200);
                bar.setProgress(nb_segments);
            }
        });

        stravaViewModel.m_seg_list.observe(getViewLifecycleOwner(), new Observer<List<ExtendedSummarySegment>>() {
            @Override
            public void onChanged(@Nullable List<ExtendedSummarySegment> segments) {
                Log.i(TAG, "segments changed");
                logText("Getting efforts...");
                bar.setMax(stravaViewModel.m_nb_segs.getValue());
                bar.setProgress(segments.size());
            }
        });

        stravaViewModel.m_routes_list.observe(getViewLifecycleOwner(), new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                logText("Getting routes...");
            }
        });

        // add strava sync button
        FloatingActionButton button_sync = root.findViewById(R.id.strava_sync_button);
        button_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                refresh();
            }
        });

        // add GPS sync button
        FloatingActionButton button_send = root.findViewById(R.id.gps_send_button);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                stravaViewModel.m_command.postValue(MainActivity.SEGMENT_LIST_READY);
            }
        });

        stravaTask = new StravaTask();
        thisFragment = this;

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void refresh() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String refresh_token = sharedPref.getString(getString(R.string.refresh_token), "");
        int expires_at = sharedPref.getInt(getString(R.string.expires_at), 0);
        long timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        if (refresh_token == null || refresh_token.isEmpty() || expires_at == 0 || expires_at < timestamp) {

            Log.i(TAG, "New tokens needed: " + refresh_token);

            // we need new tokens...
            Intent intent2 = new Intent(getActivity(), OAuthStarter.class);
            intent2.putExtra("Code", OAuthStarter.OAUTH_FIRST_REQUEST_CODE);
            startActivity(intent2);
        } else {

            stravaTask.execute();
        }
    }

    class StravaTask extends AsyncTask<String, Void, String> {

        StravaQueries queries = new StravaQueries();

        @Override
        protected void onPreExecute() {
            queries.setObservables(
                    stravaViewModel.m_nb_segs,
                    stravaViewModel.m_routes_list,
                    stravaViewModel.m_seg_list);
        }

        @Override
        protected String doInBackground(String... strings) {
            queries.refresh(thisFragment);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            logText("All queries done");
            bar.setProgress(150);
            bar.setMax(150);
        }
    }
}
