package com.example.partyy;

import android.content.Context;

import android.location.Location;
import android.location.LocationManager;

public class LocationGetter {
	
	private LocationManager locationManager;
	
	public Location location;
	
	public static LocationGetter getInstance(){
		return _instance;
	}
	
	private static LocationGetter _instance = null;
	
	private LocationGetter(){
		_instance=this;
		locationManager = (LocationManager) SplashScreenApp.getInstance().getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        // getting GPS status 
       boolean  isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status 
        boolean isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled 
        } else { 
            //this.canGetLocation = true;
            if (isNetworkEnabled) {
                
                if (locationManager != null) {
                   // location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                   location =  locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                	if (location != null) {
    	                //Log.d("activity", "LOC by Network");
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                    } 
                } 
            } 
        }

	}
	
}
