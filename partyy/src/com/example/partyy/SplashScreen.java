package com.example.partyy;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
public class SplashScreen extends Activity implements LocationListener{
	
	private static int SPLASH_TIME_OUT = 3000;
	boolean isFirstTime = false;
	boolean isAlertDialogShown = false;
	private LocationManager locationManager;
	private String provider;
	private ArrayList<ImageView> imageHolders;
	private ArrayList<String> images;
	private String[] array = {"Please wait.","Please wait...","Please wait.....","Please wait......."};
	private Thread animationThread;
	private boolean stopped = true;
    TextView plsWaitView;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		if(savedInstanceState == null){
			//DataFetcher fetcher = (DataFetcher) DataFetcher.getInstance();
		    //fetcher.execute();
			ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		     if(cm.getActiveNetworkInfo() == null ||cm.getActiveNetworkInfo().isConnected() == false){
		    	 AlertDialog alertDialog = new AlertDialog.Builder(this).create(); 
		         alertDialog.setTitle("No Interent connection");
		         alertDialog.setMessage("To use this application internet connection is required...Please try again after switching on the internet connection.");
                 final Activity c = this;
		         alertDialog.setButton("Got it..", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		               // here you can add functions
		            	
		   		        	finish();
		   		            c.finish();
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
			imageHolders = new ArrayList<ImageView>();
			plsWaitView= (TextView)findViewById(R.id.TextViewPlsWait);
			imageHolders.add((ImageView) findViewById(R.id.imgOne1));
			imageHolders.add((ImageView) findViewById(R.id.imgTwo2));
			imageHolders.add((ImageView) findViewById(R.id.imgThree3));

			// Prepare an array list of images to be animated
			images = new ArrayList<String>();

			images.add("progress_1");
			images.add("progress_2");
			images.add("progress_3");
			images.add("progress_4");
			images.add("progress_5");
			images.add("progress_6");
			images.add("progress_7");
			images.add("progress_8");
			images.add("progress_9");
			startAnimation();
		    }
		     locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		     // Define the criteria how to select the locatioin provider -> use
		     // default
		     Criteria criteria = new Criteria();
		     provider = locationManager.getBestProvider(criteria, false);
		     locationManager.requestLocationUpdates(provider, 400, 1, this);
		     Location location = locationManager.getLastKnownLocation(provider);

		     // Initialize the location fields
		     if (location != null) {
		       System.out.println("Provider " + provider + " has been selected.");
		       
		     } 
		}
		else{
			//abcd
			int k = 0;
		}
		if(imageHolders != null){
			for (ImageView imageView : imageHolders) {
				
				imageView.setVisibility(View.GONE);
			}
		}
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
       
		if(isAlertDialogShown == false){
			 timer.cancel();
			finish();
		}
		   
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	private Timer timer;
	public void startAnimation() {
		//setVisibility(View.VISIBLE);
		  timer = new Timer();
	        TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() 
				     {
				    public void run() 
				    {
				         handleMessage();  
				        }
				     });
				}
			};
	        timer.schedule(task, 0, 100);
	}
	

	    int currentImage = 0;
	    int index = 0;
		public void handleMessage() {
			int currentImage = 0;
			int nextImage = 0;
			// Logic to change the images
			/*for (ImageView imageView : imageHolders) {
				currentImage = Integer.parseInt(imageView.getTag().toString());
				if (currentImage < 9) {
					nextImage = currentImage + 1;
				} else {
					nextImage = 1;
				}
				imageView.setTag("" + nextImage);
				imageView.setImageResource(getResources().getIdentifier(
						images.get(nextImage - 1), "drawable",
						"com.example.partyy"));
				imageView.setVisibility(View.GONE);
			}*/
			if(index >=4)
				index = 0;
			plsWaitView.setText(array[index++]);
		}
		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}

	


	}