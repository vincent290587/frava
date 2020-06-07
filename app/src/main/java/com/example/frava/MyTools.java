package com.example.frava;

import android.content.res.Resources;

public class MyTools {

    public static int fromDp(int dp_val) {
        int ret = (int) ((float)dp_val * Resources.getSystem().getDisplayMetrics().density + 0.5f);
        return ret;
    }
}
