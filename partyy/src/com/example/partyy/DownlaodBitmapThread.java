package com.example.partyy;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownlaodBitmapThread extends Thread{
    public boolean stop = false;
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean stopDownloading =  false;
		while(stopDownloading == false){
			int len = DataArray.getInstance().vecVenueData.size();
			boolean bitmapRemaining = false;
			for(int i = 0;i<len;i++){
				VenueData s = DataArray.getInstance().vecVenueData.elementAt(i);
				if(s!= null && s.btmmap == null && s.isBitmapRequested == false){
					InputStream in;
					Bitmap mIcon11 = null;
					try {
				         in = new java.net.URL(s.url).openStream();
				        mIcon11 = BitmapFactory.decodeStream(in);
				        DataArray.getInstance().vecVenueData.elementAt(s.pos).btmmap = mIcon11;
						
				        bitmapRemaining = true;
				    } catch (Exception e) {
				        
				        e.printStackTrace();
				    }
					//if(DataArray.getInstance().vecVenueData.elementAt(s.pos).url == in.)
					
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
	    	        break;
			}
		}
		if(bitmapRemaining == false){
			stopDownloading = true;
		}
	}

}
}
