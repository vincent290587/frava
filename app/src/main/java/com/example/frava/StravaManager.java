package com.example.frava;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class StravaManager extends ViewModel {

    private static final String TAG = "StravaManager";

    private FunctionCallback m_callback;

    public List<ExtendedSummarySegment> cur_list;

    public StravaManager() {
        super();
    }

    public void setCallback(FunctionCallback callback) {
        m_callback = callback;
    }

    Observer<List<ExtendedSummarySegment>> observer = new Observer<List<ExtendedSummarySegment>>() {

        @Override
        public void onChanged(List<ExtendedSummarySegment> extendedSummarySegments) {

            cur_list = extendedSummarySegments;
            m_callback.onUpdated(extendedSummarySegments);
            Log.i(TAG, "startObserving #" + extendedSummarySegments.size());
        }
    };

    public void startObserving(@NonNull StravaQueries queries) {

        queries.getLiveData().observeForever(observer);
    }

    public void stopObserving(@NonNull StravaQueries queries) {

        queries.getLiveData().removeObservers(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
