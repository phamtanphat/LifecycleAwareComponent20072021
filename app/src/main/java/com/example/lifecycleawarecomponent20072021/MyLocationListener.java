package com.example.lifecycleawarecomponent20072021;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLocationListener implements DefaultLifecycleObserver, LocationListener {

    Context context;
    private OnListenLocation onListenLocation;
    private LocationManager locationManager;

    public MyLocationListener(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (onListenLocation != null){
            onListenLocation.onLocationChanged(location);
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    interface OnListenLocation{
        void onLocationChanged(Location location);
    }

    public void setOnListenerLocation(OnListenLocation onListenerLocation){
        this.onListenLocation = onListenerLocation;
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        locationManager.removeUpdates(this);
    }
}
