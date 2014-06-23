package com.example.partyy;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.RelativeLayout;

public class LoadLargerBitmap extends AsyncTask<Void, Void, Bitmap>{
    private String Id;
    private int Screen;
    private Activity activity;
    static Bitmap mIcon11 = null;
	public LoadLargerBitmap(int screen,String id,Activity act) {
		// TODO Auto-generated constructor stub
		Id = id;
		Screen = screen;
		activity =act;
	}

	@Override
	protected Bitmap doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		String path = null;
		mIcon11 = null;
		if(Screen == 0){
			path = "/sdcard/Mozito/Venues/" + Id +".jpg";
		}else if (Screen ==1){
			path = "/sdcard/Mozito/Events/" + Id +".jpg";
		}else if(Screen ==2){
			path = "/sdcard/Mozito/Offers/" + Id +".jpg";
		}
		File imageFile = new File(path);
		
		if(imageFile.exists()){
			mIcon11 = BitmapFactory.decodeFile(path);
		}
		return mIcon11;
	}
	protected void onPostExecute(Bitmap result) {
		if(Screen == 0){
			Drawable drawable = new BitmapDrawable(this.activity.getResources(), result);
	    	RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.openvenuelayout);
	    	relativeLayout.setBackgroundDrawable(drawable);}
		if(Screen == 1){
			Drawable drawable = new BitmapDrawable(this.activity.getResources(), result);
	    	RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.openeventlayout);
	    	relativeLayout.setBackgroundDrawable(drawable);
		}
		if(Screen ==2){
			Drawable drawable = new BitmapDrawable(this.activity.getResources(), result);
	    	RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.openofferlayoutNew);
	    	relativeLayout.setBackgroundDrawable(drawable);
		}
		
	}

}
