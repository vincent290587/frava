package com.example.frava;

import androidx.annotation.NonNull;

import java.util.List;

public interface LeCallbacks {

    default void onDataSend(@NonNull final byte[] data) {
        // ignore
    }

    default void onListDataSend(final List<byte[]> data) {
        // ignore
    }
}
