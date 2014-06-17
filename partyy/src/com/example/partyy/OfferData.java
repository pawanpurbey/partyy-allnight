package com.example.partyy;

import java.util.Vector;

import android.graphics.Bitmap;

public class OfferData {
	public OfferData(){}
	
	public String header = new String();
	public String photoString = new String();
	public String type = new String();
	public String isFeatured =  new String();
	public String timing = new String();
    public String startDate = new String();
	public String endData =new String();
	public String venueName = new String();
    public Bitmap btmmap = null;
    public Bitmap smallBitmap = null;
     public boolean isBitmapRequested = false;
    public int venuePos = 0;
    public int ownPosition = 0;
    
    
}
