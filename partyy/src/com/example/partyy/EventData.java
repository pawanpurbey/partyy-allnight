package com.example.partyy;

import android.graphics.Bitmap;


public class EventData {
public EventData(){}
	public String id = new String();
	public String title = new String();
	public String photoString = new String();
	public String date = new String();
	//public String isFeatured =  new String();
	public String time = new String();
    public String Desc = new String();
	//public String endData =new String();
	public String venueName = new String();
    public Bitmap btmmap = null;
    public Bitmap smallBitmap = null;
     public boolean isBitmapRequested = false;
    public int venuePos = 0;
    public int ownPosition = 0;
    
}
