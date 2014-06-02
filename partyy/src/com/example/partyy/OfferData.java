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
    public Bitmap btmmap = null;
    public String url = "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";
    public boolean isBitmapRequested = false;
    public int venuePos = 0;
    
}
