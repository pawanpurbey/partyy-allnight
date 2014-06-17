package com.example.partyy;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadImageTimerTask extends TimerTask{
	public Timer  timer;
	static boolean AllVenuesBitmapDownloaded = false;
	static boolean AllEventDataDownloaded = false;//For the time being till event  data from serevr not retreived
	static boolean AllOffersDataDownloaded = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int len = 0;
		if(DataArray.getInstance().vecVenueData != null){
			len = DataArray.getInstance().vecVenueData.size();
		}
		boolean bitmapRemaining = false;
	if(len != 0 &&(StateMachine.getInstance().currentFragment == 0 || (AllEventDataDownloaded == true && AllOffersDataDownloaded == true))){
		for(int i = 0;i<len;i++){
			VenueData s = DataArray.getInstance().vecVenueData.elementAt(i);
			if(s!= null && s.btmmap == null && s.isBitmapRequested == false){
    	        
				try {
			        InputStream in = new java.net.URL(s.url).openStream();
                    Bitmap mIcon11 = BitmapFactory.decodeStream(in);
                    DataArray.getInstance().vecVenueData.elementAt(s.pos).btmmap = mIcon11;
    				
			        bitmapRemaining = true;
			    } catch (Exception e) {
			        
			        e.printStackTrace();
			    }
				
				//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
				if(SplashScreenApp.getInstance() != null){
					SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
					    public void run() {
					    	if(eventarrayadapter.getInstance() != null){
					            eventarrayadapter.getInstance().notifyDataSetChanged();
					    	}
					    	if(offerArrayAdapter.getInstance() != null){
					           offerArrayAdapter.getInstance().notifyDataSetChanged();
					    	}
					        if(venueArayAdapater.getInstance() != null){
					          venueArayAdapater.getInstance().notifyDataSetChanged();
					        }
					    }
				});
					
	        }
				bitmapRemaining = true;
    	        return;
		}
	    
		
	}
		 AllVenuesBitmapDownloaded = true;
	}
    else if (StateMachine.getInstance().currentFragment == 2 || (AllVenuesBitmapDownloaded == true && AllEventDataDownloaded == true)){
		if( DataArray.getInstance().vecOfferData != null)
		    len =  DataArray.getInstance().vecOfferData.size();
		for(int i = 0;i<len;i++){
			OfferData s1 = DataArray.getInstance().vecOfferData.elementAt(i);
			if(s1!= null && s1.btmmap == null && s1.isBitmapRequested == false){
    	        
				try {
			        InputStream in = new java.net.URL(s1.photoString).openStream();
			        Bitmap mIcon11 = BitmapFactory.decodeStream(in);
			        DataArray.getInstance().vecOfferData.elementAt(s1.ownPosition).btmmap = mIcon11;
					
			        bitmapRemaining = true;
			    } catch (Exception e) {
			        
			        e.printStackTrace();
			    }
				
				//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
				if(SplashScreenApp.getInstance() != null){
					SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
					    public void run() {
					    	if(eventarrayadapter.getInstance() != null){
					            eventarrayadapter.getInstance().notifyDataSetChanged();
					    	}
					    	if(offerArrayAdapter.getInstance() != null){
					           offerArrayAdapter.getInstance().notifyDataSetChanged();
					    	}
					        if(venueArayAdapater.getInstance() != null){
					          venueArayAdapater.getInstance().notifyDataSetChanged();
					        }
					    }
				});
					
	        }
				bitmapRemaining = true;
    	        return;
		}
	   
			 
	}
		 AllOffersDataDownloaded = true;
	}
    else if(StateMachine.getInstance().currentFragment ==1 || (AllOffersDataDownloaded == true && AllVenuesBitmapDownloaded == true)){
    	if( DataArray.getInstance().vecEventData != null)
		    len =  DataArray.getInstance().vecEventData.size();
    	for(int i = 0;i<len;i++){
			EventData s1 = DataArray.getInstance().vecEventData.elementAt(i);
			if(s1!= null && s1.btmmap == null && s1.isBitmapRequested == false){
    	        
				try {
			        InputStream in = new java.net.URL(s1.photoString).openStream();
			        Bitmap mIcon11 = BitmapFactory.decodeStream(in);
			        DataArray.getInstance().vecEventData.elementAt(s1.ownPosition).btmmap = mIcon11;
					
			        bitmapRemaining = true;
			    } catch (Exception e) {
			        
			        e.printStackTrace();
			    }
				
				//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
				if(SplashScreenApp.getInstance() != null){
					SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
					    public void run() {
					    	if(eventarrayadapter.getInstance() != null){
					            eventarrayadapter.getInstance().notifyDataSetChanged();
					    	}
					    	if(offerArrayAdapter.getInstance() != null){
					           offerArrayAdapter.getInstance().notifyDataSetChanged();
					    	}
					        if(venueArayAdapater.getInstance() != null){
					          venueArayAdapater.getInstance().notifyDataSetChanged();
					        }
					    }
				});
					
	        }
				bitmapRemaining = true;
    	        return;
		}
	   
			 
	}
    	AllEventDataDownloaded = true;
    }
		if(AllEventDataDownloaded == true && AllOffersDataDownloaded == true && AllVenuesBitmapDownloaded == true){
			if(this.timer != null){
				this.timer.cancel();
			}
		}
	}

}