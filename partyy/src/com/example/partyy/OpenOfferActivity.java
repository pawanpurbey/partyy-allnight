package com.example.partyy;
import java.util.Locale;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenOfferActivity extends ActionBarActivity{
	private TextView viewHeader;
	private TextView viewTimings;
	private TextView viewdate;
	private LocationManager locationManager;
	private Location location;
	private String venueLocationlat;
	private String venueLocationLon;
	// The minimum distance to change Updates in meters 
		private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	 
		// The minimum time between updates in milliseconds 
		private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	 
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
		    locationManager = (LocationManager) this.getApplicationContext()
	                .getSystemService(Context.LOCATION_SERVICE);
            viewHeader = (TextView)findViewById(R.id.headeropenoffer);
            View v = findViewById(R.id.timingOpenOfferLayoutNew);
            viewTimings = (TextView)findViewById(R.id.timingOpenOfferLayoutNew);
            viewdate = (TextView)findViewById(R.id.dateOpenOffer);
	        // getting GPS status 
	       boolean  isGPSEnabled = locationManager
	                .isProviderEnabled(LocationManager.GPS_PROVIDER);
 
	        // getting network status 
	        boolean isNetworkEnabled = locationManager
	                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
 
	        if (!isGPSEnabled && !isNetworkEnabled) {
	            //  no network provider is enabled 
	        } else { 
	            //this.canGetLocation = true;
	            if (isNetworkEnabled) {
	                
	                if (locationManager != null) {
	                    location = locationManager
	                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                    if (location != null) {
	    	                //Log.d("activity", "LOC by Network");
	                        double latitude = location.getLatitude();
	                        double longitude = location.getLongitude();
	                    } 
	                } 
	            } 
	        }
	        if(viewHeader!=null)
		    viewHeader.setText(data.header);
		    if(viewTimings != null)
		    viewTimings.setText(data.timing);
		    if(viewdate!=null)
		    viewdate.setText(data.startDate+ "-"+data.endData);
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
		String uri = "http://maps.google.com/maps?saddr=" + location.getLatitude()+","+location.getLongitude()+"&daddr="+venueLocationlat+","+venueLocationLon ;

		/*Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
	    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");*/
		//String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", location.getLatitude(), location.getLatitude(), "Home Sweet Home", 28.523056, 77.2075, "Where the party is at");
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		startActivity(intent);
	}
 }


