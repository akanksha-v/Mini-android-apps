package com.example.mystopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;
    private Button reset;
    private Chronometer chronometer;

    private long lastPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);
        chronometer = findViewById(R.id.chronometer2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastPause != 0){
                    chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
                chronometer.start();
                start.setEnabled(false);
                stop.setEnabled(true);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause= SystemClock.elapsedRealtime();
                chronometer.stop();
                stop.setEnabled(false);
                start.setEnabled(true);
            }
        });
    reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            lastPause=0;
            start.setEnabled(true);
            stop.setEnabled(false);
        }
    });
    }
}