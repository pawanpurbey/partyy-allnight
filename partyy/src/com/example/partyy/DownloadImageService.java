package com.example.partyy;

import java.util.TimerTask;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;

public class DownloadImageService extends IntentService{
    private Context context;
    public DownloadImageService(){
    	super("DownloadImageService");
    }
	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		int len = DataArray.getInstance().vec.size();
		for(int i = 0;i<len;i++){
			OfferData s = DataArray.getInstance().vec.elementAt(i);
			if(s!= null && s.btmmap == null){
    	        DownloadBitmapTask task = new DownloadBitmapTask(s.url, s.pos);
    	        Void arr[] = null;
    	        
    	        task.execute(arr);
    	        int i1 =0;
    	        while(i1<100000){
    	        	i1++;
    	        }
    	        /*try {
					wait(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	        }
		}
		
	}

}
