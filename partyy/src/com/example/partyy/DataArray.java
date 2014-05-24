package com.example.partyy;
import java.util.Vector;
public class DataArray {
       private DataArray(){
	    	 
	     }
	     static DataArray instance = new DataArray();
	     public static DataArray getInstance(){
	    	 return instance;
	     }
	     public Vector<OfferData> vec = null;
	

}
