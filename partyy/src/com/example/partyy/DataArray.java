package com.example.partyy;
import java.util.Vector;
public class DataArray {
       private DataArray(){
	    	 
	     }
	     static DataArray instance = new DataArray();
	     public static DataArray getInstance(){
	    	 return instance;
	     }
	     public Vector<VenueData> vecVenueData = null;
	     public Vector<OfferData> vecOfferData = null;
	     public Vector<EventData> vecEventData = null;
	     static int numOffers = 0;
	     static int numEvents = 0 ;

}
