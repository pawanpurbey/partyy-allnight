package com.example.partyy;

import android.content.Context;

import android.location.Location;
import android.location.LocationManager;

public class LocationGetter {
	
	private LocationManager locationManager;
	public double latitide;
	public double longitude;
	private static Object obj;
	public Location location;
	
	public static synchronized LocationGetter getInstance(){
		if(_instance == null){
			_instance = new LocationGetter();
		}
		return _instance;
	}
	
	private static LocationGetter _instance = new LocationGetter();
	
	 private LocationGetter(){
		_instance=this;
		locationManager = (LocationManager) SplashScreenApp.getInstance().getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
	 }
	 public void  getLocation(){
		 
		

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
                       latitide = location.getLatitude();
                       longitude = location.getLongitude();
                    } 
                } 
            } 
        }

	}
	
}
