package com.example.frava;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.swagger.client.model.Route;

public class StravaManager extends ViewModel {

    private static final String TAG = "StravaManager";

    public MutableLiveData<Route> m_route_to_send = new MutableLiveData<>();
    public MutableLiveData<List<Route>> m_routes_list = new MutableLiveData<>();
    public MutableLiveData<List<ExtendedSummarySegment>> m_seg_list = new MutableLiveData<>();

    public MutableLiveData<StravaQueries> mBinder = new MutableLiveData<>();

    public StravaManager() {
        super();
    }

    public void refresh() {

        StravaQueries stravaQueries = mBinder.getValue();
        if (stravaQueries != null) {
            Log.d(TAG, "refresh");
            stravaQueries.setObservables(m_routes_list, m_seg_list);
            stravaQueries.refresh();
        } else {
            Log.d(TAG, "null binder");
        }
    }

    public void prepareRoute(int position) {
        if (m_routes_list.getValue() != null) {
            m_route_to_send.postValue(m_routes_list.getValue().get(position));
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    public ServiceConnection connectionStrava = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            StravaQueries.LocalBinder binder = (StravaQueries.LocalBinder) service;
            mBinder.postValue(binder.getService());
            Log.i(TAG, "Strava service started");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBinder.postValue(null);
            Log.i(TAG, "Strava service stopped");
        }
    };

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
