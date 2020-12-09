package com.example.solution.util;

import android.content.Context;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendHttpRequest(Context context, String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        Log.d("url", String.valueOf(address));
        client.newCall(request).enqueue(callback);
    }
}
