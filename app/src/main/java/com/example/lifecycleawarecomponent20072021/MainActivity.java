package com.example.lifecycleawarecomponent20072021;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    MyLocationListener locationListener;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationListener = new MyLocationListener(this);
        getLifecycle().addObserver(locationListener);
        locationListener.setOnListenerLocation(new MyLocationListener.OnListenLocation() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("BBB",count + " " +location.getLatitude()  + " " + location.getLongitude());
                count++;

            }
        });
    }
}