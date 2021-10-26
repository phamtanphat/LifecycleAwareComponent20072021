package com.example.lifecycleawarecomponent20072021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyLocationListener locationListener;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationListener = new MyLocationListener(this);
        getLifecycle().addObserver(locationListener);
        locationListener.locationLiveData.observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                if (location != null){
                    Log.d("BBB",count + " " +location.getLatitude()  + " " + location.getLongitude());
                    count++;
                }
            }
        });

        locationListener.liveDataString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("BBB",Thread.currentThread().getName());
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}