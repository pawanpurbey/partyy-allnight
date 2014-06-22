package com.example.partyy;

import java.util.TimerTask;

public class RecycleBitmapTask extends Thread{
    public void run(){
    	   while(true){
	    	if(StateMachine.getInstance().currentFragment != 0){
	    		if(DataArray.getInstance().vecVenueData != null){
	    			int len = DataArray.getInstance().vecVenueData.size();
	    			boolean Recycled = false;
	    			for(int i = 0;i<len ;i++){
	    				if(DataArray.getInstance().vecVenueData.elementAt(i).btmmap != null){
	    					DataArray.getInstance().vecVenueData.elementAt(i).btmmap.recycle();
	    					DataArray.getInstance().vecVenueData.elementAt(i).btmmap = null;
	    					Recycled =  true;
	    				}
	    				if(DataArray.getInstance().vecVenueData.elementAt(i).sbtmmap != null){
	    					DataArray.getInstance().vecVenueData.elementAt(i).sbtmmap.recycle();
	    					DataArray.getInstance().vecVenueData.elementAt(i).sbtmmap = null;
	    					Recycled =  true;
	    				}
	    				if(Recycled == true){
	    					break;
	    				}
	    			}
	    		}
	    		
	    	}
	    	if(StateMachine.getInstance().currentFragment != 1){
	    		if(DataArray.getInstance().vecEventData != null){
	    			int len = DataArray.getInstance().vecEventData.size();
	    			boolean Recycled = false;
	    			for(int i = 0;i<len ;i++){
	    				if(DataArray.getInstance().vecEventData.elementAt(i).btmmap != null){
	    					DataArray.getInstance().vecEventData.elementAt(i).btmmap.recycle();
	    					DataArray.getInstance().vecEventData.elementAt(i).btmmap = null;
	    					Recycled = true;
	    				}
	    				if(DataArray.getInstance().vecEventData.elementAt(i).smallBitmap != null){
	    					DataArray.getInstance().vecEventData.elementAt(i).smallBitmap.recycle();
	    					DataArray.getInstance().vecEventData.elementAt(i).smallBitmap= null;
	    					Recycled = true;
	    				}
	    				if(Recycled == true){
	    					break;
	    				}
	    			}
	    		}
	    		
	    	}
	    	if(StateMachine.getInstance().currentFragment != 2){
	    		if(DataArray.getInstance().vecOfferData != null){
	    			int len = DataArray.getInstance().vecOfferData.size();
	    			boolean Recycled = false;
	    			for(int i = 0;i<len ;i++){
	    				if(DataArray.getInstance().vecOfferData.elementAt(i).btmmap != null){
	    					DataArray.getInstance().vecOfferData.elementAt(i).btmmap.recycle();
	    					DataArray.getInstance().vecOfferData.elementAt(i).btmmap = null;
	    					Recycled = true;
	    				}
	    				if(DataArray.getInstance().vecOfferData.elementAt(i).smallBitmap!= null){
	    					DataArray.getInstance().vecOfferData.elementAt(i).smallBitmap.recycle();
	    					DataArray.getInstance().vecOfferData.elementAt(i).smallBitmap = null;
	    					Recycled = true;
	    				}
	    				if(Recycled == true){
	    					break;
	    				}
	    			}
	    		}
	    		
	    	}
    	   }
    	
    }
}
