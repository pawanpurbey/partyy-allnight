package com.example.partyy;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
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
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OpenOfferActivity extends ActionBarActivity{
	private TextView viewHeader;
	private TextView viewTimings;
	private TextView viewdate;
	private Location location;
	private String venueLocationlat;
	private String venueLocationLon;
	// The minimum distance to change Updates in meters 
		private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	 
		// The minimum time between updates in milliseconds 
		private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	 
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.openofferlayout);
			
			Bundle bundle = getIntent().getExtras();

		    //Extract the data…
		    int  valPos = bundle.getInt("Pos");
		   // view = (TextView)findViewById(R.id.textViewEventLayout);
		    //Now get actual data to show 
		    OfferData data = DataArray.getInstance().vecOfferData.elementAt(valPos);
		    int venueId = data.venuePos;
		    VenueData dataVenue = DataArray.getInstance().vecVenueData.elementAt(venueId);
		    venueLocationlat = dataVenue.lat;
		    venueLocationLon = dataVenue.lon;
		   
            viewHeader = (TextView)findViewById(R.id.headeropenoffer);
            View v = findViewById(R.id.timingOpenOfferLayoutNew);
            viewTimings = (TextView)findViewById(R.id.timingOpenOfferLayoutNew);
            viewdate = (TextView)findViewById(R.id.dateOpenOffer);
	       
	        
	        if(viewHeader!=null)
		    viewHeader.setText(data.header);
		    if(viewTimings != null)
		    viewTimings.setText(data.timing);
		    if(viewdate!=null)
		    viewdate.setText(data.startDate+ "-"+data.endData);
		    if(data== null || data.smallBitmap == null){
		    	RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.openofferlayoutNew);
    	    	relativeLayout.setBackgroundResource(R.drawable.striker);
    	    }
    	    else  if (data != null && data.smallBitmap != null){
    	    	
    	    	
    	    	Drawable drawable = new BitmapDrawable(this.getResources(), data.smallBitmap);
    	    
    	    	RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.openofferlayoutNew);
    	    	relativeLayout.setBackgroundDrawable(drawable);
    	    	LoadLargerBitmap task = new LoadLargerBitmap(2, data.id, this);
    	    	Void arr[] = new Void[2];
    	    	task.execute(arr[0]);
    	    }
		    TypedValue tv = new TypedValue();
		    int actionBarHt = 0;
		    if(getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)){
		    	actionBarHt = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
		    }
		    ImageView v1 = (ImageView)this.findViewById(R.id.imageViewopenOfferlayout);
			DisplayMetrics metrics = this.getResources().getDisplayMetrics();
			
			int ht = metrics.heightPixels;
			//LayoutParams params = new LayoutParams(source);
			v1.requestLayout();
			v1.getLayoutParams().height  = ht-80-actionBarHt;
			
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
		LocationGetter.getInstance().getLocation();
        String longitude = new String();
        longitude += LocationGetter.getInstance().longitude;
        String latitude = new String();
        latitude += LocationGetter.getInstance().latitide;
		String uri = "http://maps.google.com/maps?saddr=" + latitude+","+
				longitude+"&daddr="+venueLocationlat+","+venueLocationLon;
		
		
		/*Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
	    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");*/
		//String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", location.getLatitude(), location.getLatitude(), "Home Sweet Home", 28.523056, 77.2075, "Where the party is at");
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		startActivity(intent);
	}
 }


