package com.example.solution.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.solution.R;

public class PreviewActivity extends AppCompatActivity {

    private static final String TAG = "PreviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        int pos = getIntent().getIntExtra("position",-1);
        Log.d(TAG, String.valueOf(pos));
    }
}