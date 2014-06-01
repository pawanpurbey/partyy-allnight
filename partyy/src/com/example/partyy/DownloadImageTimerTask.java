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
		int len = DataArray.getInstance().vec.size();
		boolean bitmapRemaining = false;
		for(int i = 0;i<len;i++){
			OfferData s = DataArray.getInstance().vec.elementAt(i);
			if(s!= null && s.btmmap == null && s.isBitmapRequested == false){
    	        /*DownloadBitmapTask task = new DownloadBitmapTask(s.url, s.pos);
    	        Void arr[] = null;
    	        
    	        task.execute(arr);
    	        s.isBitmapRequested = true;
    	        bitmapRemaining = true;*/
				try {
			        InputStream in = new java.net.URL(s.url).openStream();
			        mIcon11 = BitmapFactory.decodeStream(in);
			        bitmapRemaining = true;
			    } catch (Exception e) {
			        
			        e.printStackTrace();
			    }
				DataArray.getInstance().vec.elementAt(s.pos).btmmap = mIcon11;
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
					bitmapRemaining = true;
    	        break;
	        }
		}
			
		
	}
		if(bitmapRemaining == false){
			if(this.timer != null){
				this.timer.cancel();
			}
		}
	}

}