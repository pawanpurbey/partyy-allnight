package com.example.partyy;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

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
		 locationManager = (LocationManager) SplashScreenApp.getInstance().getApplicationContext()
	                .getSystemService(Context.LOCATION_SERVICE);
		 
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
                	//locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER,10000, 1, this); 
                   location =  locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                	if (location != null) {
    	                //Log.d("activity", "LOC by Network");
                       latitide = location.getLatitude();
                       longitude = location.getLongitude();
                    } 
                } 
            } 
             if(location == null &&isGPSEnabled){
            	if (locationManager != null) {
                    // location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    location =  locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                 	if (location != null) {
     	                //Log.d("activity", "LOC by Network");
                        latitide = location.getLatitude();
                        longitude = location.getLongitude();
                     } 
                 } 
            }
        }
        List<String> providers = locationManager.getProviders(true);
        for (int i=providers.size()-1; i>=0; i--) {
            location = locationManager.getLastKnownLocation(providers.get(i));
            if (location != null) break;
    }

	}
}

