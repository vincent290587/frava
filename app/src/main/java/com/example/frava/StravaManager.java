package com.example.frava;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.swagger.client.model.Route;

public class StravaManager extends ViewModel {

    private static final String TAG = "StravaManager";

    public MutableLiveData<Route> m_route_to_send = new MutableLiveData<>();
    public MutableLiveData<List<Route>> m_routes_list = new MutableLiveData<>();
    public MutableLiveData<List<ExtendedSummarySegment>> m_seg_list = new MutableLiveData<>();

    public StravaManager() {
        super();
    }

    public void prepareRoute(int position) {
        if (m_routes_list.getValue() != null) {
            m_route_to_send.postValue(m_routes_list.getValue().get(position));
        }
    }

}
