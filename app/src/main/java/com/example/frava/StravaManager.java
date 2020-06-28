package com.example.frava;

import android.bluetooth.BluetoothProfile;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.swagger.client.model.Route;
import no.nordicsemi.android.ble.livedata.state.ConnectionState;

public class StravaManager extends ViewModel {

    private static final String TAG = "StravaManager";

    public MutableLiveData<Boolean> m_conn = new MutableLiveData<>();
    public MutableLiveData<Integer> m_command = new MutableLiveData<>();
    public MutableLiveData<Integer> m_nb_segs = new MutableLiveData<>();
    public MutableLiveData<Route> m_route_to_send = new MutableLiveData<>();
    public MutableLiveData<List<Route>> m_routes_list = new MutableLiveData<>();
    public MutableLiveData<List<ExtendedSummarySegment>> m_seg_list = new MutableLiveData<>();

    // observer for main activity
    public final Observer<ConnectionState> m_conn_state_obs = new Observer<ConnectionState>() {
        @Override
        public void onChanged(ConnectionState is_conn) {
            Log.d(TAG, "onChanged connection state " + is_conn.toString());
            m_conn.postValue(is_conn.isReady());
        }
    };

    public StravaManager() {
        super();
    }

    public void prepareRoute(int position) {
        if (m_routes_list.getValue() != null) {
            m_route_to_send.postValue(m_routes_list.getValue().get(position));
        } else {
            Log.e(TAG, "value null");
        }
    }

}
