package com.example.partyy;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadImageTimerTask extends TimerTask{
	public Timer  timer;
	Bitmap mIcon11;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int len = DataArray.getInstance().vecVenueData.size();
		boolean bitmapRemaining = false;
		for(int i = 0;i<len;i++){
			VenueData s = DataArray.getInstance().vecVenueData.elementAt(i);
			if(s!= null && s.btmmap == null && s.isBitmapRequested == false){
    	        
				try {
			        InputStream in = new java.net.URL(s.url).openStream();
			        mIcon11 = BitmapFactory.decodeStream(in);
			        bitmapRemaining = true;
			    } catch (Exception e) {
			        
			        e.printStackTrace();
			    }
				DataArray.getInstance().vecVenueData.elementAt(s.pos).btmmap = mIcon11;
				
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
		len =  DataArray.getInstance().vecOfferData.size();
		for(int i = 0;i<len;i++){
			OfferData s1 = DataArray.getInstance().vecOfferData.elementAt(i);
			if(s1!= null && s1.btmmap == null && s1.isBitmapRequested == false){
    	        
				try {
			        InputStream in = new java.net.URL(s1.photoString).openStream();
			        mIcon11 = BitmapFactory.decodeStream(in);
			        bitmapRemaining = true;
			    } catch (Exception e) {
			        
			        e.printStackTrace();
			    }
				DataArray.getInstance().vecOfferData.elementAt(s1.ownPosition).btmmap = mIcon11;
				
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
		if(bitmapRemaining == false){
			if(this.timer != null){
				this.timer.cancel();
			}
		}
	}

}