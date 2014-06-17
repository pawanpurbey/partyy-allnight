package com.example.partyy;

import java.util.Vector;

import android.graphics.Bitmap;

public class VenueData {
	public VenueData(){}
	public String ID = new String();
	public String Name = new String();
	public String sDescription = new String();
	public String bDescription = new String();
	public String City = new String();
	public String address =  new String();
	public String bitMapData = new String();
	public String timing = new String();
	public String type = new String();
	public String phone = new String();
	public String lat = new String();
	public String lon =  new String();
    public Bitmap btmmap = null;
    public Bitmap sbtmmap = null;
    public String url = "http://www.mobiwebdev.com/wp-content/uploads/2014/06/PartyRock-Venue-Ggn-7Brauhaus1.jpg";
    public boolean isBitmapRequested = false;
    public int pos = 0;
    public String distance = "-1";
    
}
