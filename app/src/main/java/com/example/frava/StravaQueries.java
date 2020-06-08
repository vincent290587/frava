package com.example.frava;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.AthletesApi;
import io.swagger.client.api.RoutesApi;
import io.swagger.client.api.SegmentsApi;
import io.swagger.client.api.StreamsApi;
import io.swagger.client.auth.OAuth;
import io.swagger.client.model.DetailedAthlete;
import io.swagger.client.model.Route;
import io.swagger.client.model.StreamSet;
import io.swagger.client.model.SummarySegment;
import io.swagger.client.model.SummarySegmentEffort;
import no.nordicsemi.android.ble.callback.profile.ProfileDataCallback;

public class StravaQueries extends Service {

    private static final String TAG = "StravaQueries";

    private Context mContext;

    private List<SummarySegment> seg_sum_list;

    MutableLiveData<List<Route>> p_routes_list;
    MutableLiveData<List<ExtendedSummarySegment>> p_seg_list;

    // Binder given to clients
    private final IBinder binder = new StravaQueries.LocalBinder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        StravaQueries getService() {
            // Return this instance of BluetoothLeService so clients can call public methods
            return StravaQueries.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public StravaQueries() {
        super();
        seg_sum_list = null;
        p_routes_list = new MutableLiveData<List<Route>>();
        p_seg_list    = new MutableLiveData<List<ExtendedSummarySegment>>();
    }

    public void subscribeRoutes(Observer<List<Route>> observer) {
        p_routes_list.observeForever(observer);
    }

    public LiveData<List<ExtendedSummarySegment>> getLiveData() {
        return p_seg_list;
    }

    private void refreshList() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);

        String access_token = sharedPref.getString(getString(R.string.access_token), "");
        String refresh_token = sharedPref.getString(getString(R.string.refresh_token), "");

        Log.d(TAG, "onHandleIntent token=" + access_token);
        Log.d(TAG, "onHandleIntent refresh=" + refresh_token);

        ApiClient apiClient = Configuration.getDefaultApiClient();
        apiClient.setAccessToken(access_token);
        apiClient.setDebugging(false);

        // Configure OAuth2 access token for authorization: strava_oauth
        OAuth strava_oauth = (OAuth) apiClient.getAuthentication("strava_oauth");
        strava_oauth.setAccessToken(access_token);

        querySegments();
        queryEfforts();
        queryRoutes();
    }

    Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {
           refreshList();
           return;
        }
    });

    private void queryRoutes() {

        Integer page = 1; // Integer | Page number. Defaults to 1.
        Integer perPage = 30; // Integer | Number of items per page. Defaults to 30.

        // get the starred segments sync mode
        try {

            AthletesApi ath_api = new AthletesApi();
            DetailedAthlete athlete = ath_api.getLoggedInAthlete();
            int athlete_id = 0;
            if (athlete != null) {
                athlete_id = athlete.getId();
            } else {
                Log.e(TAG, "NULL athlete ID");
                return;
            }

            List<Route> result = new ArrayList<>();
            do {
                RoutesApi apiInstance = new RoutesApi();

                // add all that we found in our list
                result.addAll(apiInstance.getRoutesByAthleteId(athlete_id, page, perPage));

                page += 1;

                if (MapTool.isDebugVersion()) {
                    return;
                }

            } while (result != null && result.size() > 0);

            p_routes_list.postValue(result);

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void querySegments() {

        Integer page = 1; // Integer | Page number. Defaults to 1.
        Integer perPage = 30; // Integer | Number of items per page. Defaults to 30.

        seg_sum_list = new ArrayList<SummarySegment>();

        // get the starred segments sync mode
        try {

            List<SummarySegment> result = null;
            do {
                SegmentsApi apiInstance = new SegmentsApi();
                result = apiInstance.getLoggedInAthleteStarredSegments(page, perPage);

                // add all that we found in our list
                seg_sum_list.addAll(result);

                page += 1;

                if (MapTool.isDebugVersion()) {
                    return;
                }

            } while (result != null && result.size() > 0);

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void queryEfforts() {

        List<ExtendedSummarySegment> tmp_result = new ArrayList<ExtendedSummarySegment>();

        for (SummarySegment summarySegment : seg_sum_list) {

            SummarySegmentEffort effort = summarySegment.getAthletePrEffort();
            if (effort != null) {
                Long effort_id = effort.getId();

                Log.d(TAG, "SummarySegmentEffort ID " + effort_id);

                // now try to get the PR effort
                try {

                    StreamsApi apiInstance = new StreamsApi();
                    String[] keys = {"latlng", "time", "altitude"};
                    StreamSet streamSet = apiInstance.getSegmentEffortStreams(effort_id, Arrays.asList(keys), true);

                    ExtendedSummarySegment ext_seg = new ExtendedSummarySegment(summarySegment);
                    ext_seg.attachStreams(streamSet);
                    tmp_result.add(ext_seg);

                    // update our public list
                    p_seg_list.postValue(tmp_result);

                    if (MapTool.isDebugVersion()) {
                        return;
                    }

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        if (mContext == null) {

            mContext = getApplicationContext();
            thread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mContext = null;
        thread.interrupt();
        super.onDestroy();
    }
}
