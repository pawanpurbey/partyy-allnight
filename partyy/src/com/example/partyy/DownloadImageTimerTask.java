package com.example.partyy;

import java.util.Timer;
import java.util.TimerTask;

public class DownloadImageTimerTask extends TimerTask{
	public Timer  timer;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int len = DataArray.getInstance().vec.size();
		boolean bitmapRemaining = false;
		for(int i = 0;i<len;i++){
			OfferData s = DataArray.getInstance().vec.elementAt(i);
			if(s!= null && s.btmmap == null && s.isBitmapRequested == false){
    	        DownloadBitmapTask task = new DownloadBitmapTask(s.url, s.pos);
    	        Void arr[] = null;
    	        
    	        task.execute(arr);
    	        s.isBitmapRequested = true;
    	        bitmapRemaining = true;
    	        break;
	        }
		}
		if(bitmapRemaining == false){
			if(this.timer != null){
				this.timer.cancel();
			}
		}
	}

}
