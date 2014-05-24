package com.example.partyy;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import org.json.JSONObject;
public class SplashScreen extends Activity{
	private static int SPLASH_TIME_OUT = 3000;
	boolean isFirstTime = false;
	public void onCreate(Bundle savedInstanceState){
		if(savedInstanceState == null){
			//DataFetcher fetcher = (DataFetcher) DataFetcher.getInstance();
		    //fetcher.execute();
			SplashScreenApp app= new SplashScreenApp();
			SplashScreenApp.getInstance().context = getApplicationContext();
			SQLiteAsyncTask task = new SQLiteAsyncTask(this);
			task.function = 1;
			User user[] = null;
			task.execute(user);
			if(DataArray.getInstance().vec ==null || DataArray.getInstance().vec.size() ==0){
				
			}
			else{
				Intent i = new Intent(this, MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				this.startActivity(i);
		        finish();
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
		finish();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}