package com.example.partyy;
import java.io.IOException;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OpenVenueActivity extends ActionBarActivity{
	private TextView viewTiming;
	private TextView viewAge;
	private TextView viewPhone;
	private TextView viewDescription;

	String venueLat ;
	String venueLon;
	// The minimum distance to change Updates in meters 
		private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	 
		// The minimum time between updates in milliseconds 
		private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	 
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.openvenuelayout);
			Bundle bundle = getIntent().getExtras();

		    //Extract the data�
		    int  valPos = bundle.getInt("Pos");
		   // view = (TextView)findViewById(R.id.textViewEventLayout);
		    //Now get actual data to show 
		    VenueData data = DataArray.getInstance().vecVenueData.elementAt(valPos);
		   
		    	        String sDescription = data.sDescription;
	       // String[] arrSplit =sDescription.split("LAT:");
	        
	        //String[] lon = sDescription.split("LON:");
	        venueLat = data.lat;
	        venueLon = data.lon;
	        viewTiming = (TextView)this.findViewById(R.id.TimingsOpenVenueLayout);
	        viewPhone = (TextView)this.findViewById(R.id.PhoneOpenVenueLayout);
	        viewAge = (TextView)this.findViewById(R.id.AgeOpenVenueLayout);
	        viewDescription = (TextView)this.findViewById(R.id.DescriptionOpenVenueLayout);
	        viewTiming.setText(data.timing);
	        viewPhone.setText(data.phone);
	        viewAge.setText(data.City);
	        viewDescription.setText(data.bDescription);
		    if(data== null || data.sbtmmap == null){
		    	RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.openvenuelayout);
    	    	relativeLayout.setBackgroundResource(R.drawable.striker);
		       
    	    }
    	    else  if (data != null && data.sbtmmap != null){
    	    	
    	    	
    	    	Drawable drawable = new BitmapDrawable(this.getResources(), data.sbtmmap);
    	        View v = findViewById(R.id.openvenuelayout);
    	    	RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.openvenuelayout);
    	    	relativeLayout.setBackgroundDrawable(drawable);
    	    	LoadLargerBitmap task = new LoadLargerBitmap(0, data.ID, this);
    	    	Void arr[] = new Void[2];
    	    	task.execute(arr[0]);
    	    }
		    int actionBarHt = 0;
		    TypedValue tv = new TypedValue();
		    if(getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)){
		    	actionBarHt = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
		    }
		    ImageView v1 = (ImageView)this.findViewById(R.id.imageViewopenvenuelayout);
			DisplayMetrics metrics = this.getResources().getDisplayMetrics();
			
			int ht = metrics.heightPixels;
			//LayoutParams params = new LayoutParams(source); 
			v1.requestLayout();
			v1.getLayoutParams().height  = ht-90-actionBarHt;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            //openSearch();
	            return true;
	        case R.id.action_navigate:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	private void openSettings() {
		// TODO Auto-generated method stub
		//String uri = "http://maps.google.com/maps?saddr=" + location.getLatitude()+","+location.getLongitude()+"&daddr="+venueLat+","+venueLon ;
		 LocationGetter.getInstance().getLocation();
         String longitude = new String();
         longitude += LocationGetter.getInstance().longitude;
         String latitude = new String();
         latitude += LocationGetter.getInstance().latitide;
		 String uri = "http://maps.google.com/maps?saddr=" + latitude+","+
				longitude+"&daddr="+venueLat+","+venueLon ;
		/*Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
	    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");*/
		//String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", location.getLatitude(), location.getLatitude(), "Home Sweet Home", 28.523056, 77.2075, "Where the party is at");
		 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		 intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		 startActivity(intent);
	}
	public void ChangeBitmap(Bitmap btmp){
		Drawable drawable = new BitmapDrawable(this.getResources(), btmp);
    	RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.openvenuelayout);
    	relativeLayout.setBackgroundDrawable(drawable);
	}
 }


