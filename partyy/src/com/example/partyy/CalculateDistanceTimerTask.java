package com.example.partyy;

import java.util.TimerTask;

import android.location.Location;

public class CalculateDistanceTimerTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 LocationGetter.getInstance().getLocation();
         //String longitude = new String();
         double longitude = LocationGetter.getInstance().longitude;
         //String latitude = new String();
         double latitude = LocationGetter.getInstance().latitide;
         int len  = 0;
         if(DataArray.getInstance().vecVenueData != null){
             len =  DataArray.getInstance().vecVenueData.size();
         }
         for(int i = 0 ; i< len;i++){
        	 double venueLat = Double.parseDouble(DataArray.getInstance().vecVenueData.elementAt(i).lat);
        	 double venueLon = Double.parseDouble(DataArray.getInstance().vecVenueData.elementAt(i).lon);
        	 //Calculate Distance and then assign it
        	 float[] res = new float[1];
        	 Location.distanceBetween(latitude, longitude, venueLat, venueLon, res);
        	 res[0] = res[0]/1000;
        	 int distance = (int) res[0];
        	 String dist = new String();
        	 dist += distance + " km";
        	 DataArray.getInstance().vecVenueData.elementAt(i).distance = dist;
         }
         if(SplashScreenApp.getInstance() != null){
				SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				    	
				        if(venueArayAdapater.getInstance() != null){
				          venueArayAdapater.getInstance().notifyDataSetChanged();
				        }
				    }
			});
	}

}
}
