
	package com.example.partyy;

	import java.util.HashSet;
	import java.util.Set;



	import android.app.Application;
	import android.content.Context;
	import android.content.SharedPreferences;
	import android.media.AudioManager;
	import android.media.MediaPlayer;
	import android.os.Handler;
	import android.preference.PreferenceManager;

	public class SplashScreenApp extends Application{
		private Handler                       i_handler;
		public Context context;
		public void onCreate(){
	        i_handler = new Handler();
	       
			
		}
	    private static SplashScreenApp _instance = null;
	    public SplashScreenApp(){
	    	_instance= this;
	    }
	    public static SplashScreenApp getInstance(){
	      return _instance;
	    }
	   
		public void runOnUiThread(Runnable runnable) {
		    i_handler.post(runnable);
			
		}
        public Context getApplicationContext(){
        	return context;
        }
		
	    
	}


