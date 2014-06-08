package com.example.partyy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import org.json.JSONObject;
public class SplashScreen extends Activity{
	private static int SPLASH_TIME_OUT = 3000;
	boolean isFirstTime = false;
	boolean isAlertDialogShown = false;
	public void onCreate(Bundle savedInstanceState){
		if(savedInstanceState == null){
			//DataFetcher fetcher = (DataFetcher) DataFetcher.getInstance();
		    //fetcher.execute();
			ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		     if(cm.getActiveNetworkInfo() == null ||cm.getActiveNetworkInfo().isConnected() == false){
		    	 AlertDialog alertDialog = new AlertDialog.Builder(this).create(); 
		         alertDialog.setTitle("No Interent connection");
		         alertDialog.setMessage("To use this application internet connection is required...Please try again after switching on the internet connection.");
                 final Context c = this;
		         alertDialog.setButton("Got it..", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		               // here you can add functions
		            	
		   		        	finish();
		   		        
		            }
		         });
                 isAlertDialogShown = true;
		         alertDialog.show();
		    	 
		     }
		    else{
			SplashScreenApp app= new SplashScreenApp();
			SplashScreenApp.getInstance().context = getApplicationContext();
			SQLiteAsyncTask task = new SQLiteAsyncTask(this);
			task.function = 1;
			User user[] = null;
			task.execute(user);
			if(DataArray.getInstance().vecVenueData ==null || DataArray.getInstance().vecVenueData.size() ==0 ||DataArray.getInstance().vecOfferData ==null || DataArray.getInstance().vecOfferData.size() == 0 ){
				
			}
			else{
				Intent i = new Intent(this, MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				this.startActivity(i);
		        finish();
			}
		    }
		}
		else{
			//abcd
			int k = 0;
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//finish();
		/*new Handler().postDelayed(new Runnable(){
			/*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            //@Override
            /*public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
		},SPLASH_TIME_OUT);*/
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(isAlertDialogShown == false)
		   finish();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}