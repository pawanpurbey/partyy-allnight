package com.example.partyy;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
public class DownloadBitmapTask extends AsyncTask<Void, Void, Bitmap> {
	String url;
	int pos = 0;
	Bitmap mIcon11 = null;
	DownloadBitmapTask(String url,int i){
		this.url = url;
		pos = i;
	}
	@Override
	protected Bitmap doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		
	    try {
	        InputStream in = new java.net.URL(this.url).openStream();
	        mIcon11 = BitmapFactory.decodeStream(in);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	    }
	    return mIcon11;
		//return null;
	}
	protected void onPostExecute(Bitmap result) {
		DataArray.getInstance().vecVenueData.elementAt(pos).btmmap = mIcon11;
		//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
		if(SplashScreenApp.getInstance() != null){
			SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
			    public void run() {
			    	if(eventarrayadapter.getInstance() != null){
			            eventarrayadapter.getInstance().notifyDataSetChanged();
			    	}
			    	if(offerArrayAdapter.getInstance() != null){
			           offerArrayAdapter.getInstance().notifyDataSetChanged();
			    	}
			        if(venueArayAdapater.getInstance() != null){
			          venueArayAdapater.getInstance().notifyDataSetChanged();
			        }
			    }
		});
			
		}
	 }   
		
}/*public DownloadImageTask(ImageView bmImage) {
		    this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
		    String urldisplay = urls[0];
		    Bitmap mIcon11 = null;
		    try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		    } catch (Exception e) {
		        Log.e("Error", e.getMessage());
		        e.printStackTrace();
		    }
		    return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
		 }   bmImage.setImageBitmap(result);
		}
*/
