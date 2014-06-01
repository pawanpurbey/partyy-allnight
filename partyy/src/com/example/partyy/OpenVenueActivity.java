package com.example.partyy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenVenueActivity extends Activity{
	private TextView view;
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.venuelayout);
			Bundle bundle = getIntent().getExtras();

		    //Extract the data…
		    int  valPos = bundle.getInt("Pos");
		    view = (TextView)findViewById(R.id.textViewEventLayout);
		    //Now get actual data to show 
		    OfferData data = DataArray.getInstance().vec.elementAt(valPos);
		   
		    if(data== null || data.btmmap == null){
		    	//LayoutInflater inflater = getLayoutInflater();
		    	getWindow().getDecorView().setBackgroundResource(R.drawable.party);
		        
		       if(data != null){
	    	        DownloadBitmapTask task = new DownloadBitmapTask(data.url, data.pos);
	    	        Void arr[] = null;
	    	        task.execute(arr);
    	        }
    	    }
    	    else  if (data != null){
    	    	Bitmap bitmap = data.btmmap;
    	    	
    	    	Drawable drawable = new BitmapDrawable(this.getResources(), bitmap);
    	    	getWindow().getDecorView().setBackgroundDrawable(drawable);
    	    }
		    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr="+28.523056+","+77.2075));
		    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

            startActivity(intent);
	}
 }


