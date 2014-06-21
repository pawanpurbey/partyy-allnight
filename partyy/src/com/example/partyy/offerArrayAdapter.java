package com.example.partyy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class offerArrayAdapter extends ArrayAdapter<OfferData>{
     private Activity context;
     //Later on String will be changed by our own class
    
     private static offerArrayAdapter _instance;
     public static final int  SHOW_ALL = 0 ;
     public static final int  SHOW_DRINKS = 2;
     public static final int  SHOW_FOOD = 1 ;
     public static final int  SHOW_UNKNOWN = 3 ;
     public static final int  SHOW_GUESTLIST = 4 ;
     public int showString =0;
     private  ArrayList<OfferData> list =new ArrayList<OfferData>();

     public offerArrayAdapter(Activity c,List<OfferData> val){
    	// super(c, R.layout.offerlayoutnew,val);
    	 super(c, R.layout.offerlayoutnew, val);
         for (int i = 0; i < val.size(); ++i) {
           list.add(val.get(i));  
         }
    	 this.context = c;
    	
    	 _instance = this;
     }
    

     @Override
     public boolean hasStableIds() {
       return true;
     }

     public static offerArrayAdapter getInstance(){
    	 return _instance;
     }
     public static class ViewHolder{
    	 public TextView textHeader;
    	 public TextView textTiming;
    	 public TextView textVenueName;
    	 public ImageView view;
     }
    /* public OfferData getData(int pos){
    	 if(pos>=0 && pos<val.length){
    	     return val[pos];
    	 }else{
    		 return null;
    	 }
     }*/
     public void ChangeData(){
    	 int len = 0;
   		 if(DataArray.getInstance().vecOfferData != null)
              len =  DataArray.getInstance().vecOfferData.size();
    	 if(showString == 0 ){
    		 list.clear();
    		 
             for(int i =0 ;i<len;i++){
            	 list.add( DataArray.getInstance().vecOfferData.elementAt(i));
             }
 	    }else if(showString == 1 ){
 	    	 
          	
             list.clear();
             
              for(int i =0 ;i<len;i++){
            	  if(DataArray.getInstance().vecOfferData.elementAt(i).type.equals("Food")){
             	 list.add( DataArray.getInstance().vecOfferData.elementAt(i));
              }
              }
 	    }else if(showString == 2 ){
 	    	
            list.clear();
          
            for(int i =0 ;i<len;i++){
          	  if(DataArray.getInstance().vecOfferData.elementAt(i).type.equals("Drink")){
           	 list.add( DataArray.getInstance().vecOfferData.elementAt(i));
            }
            }
 	    }else if(showString == 3 ){
 	    	
 	    	list.clear();
            for(int i =0 ;i<len;i++){
          	  if(DataArray.getInstance().vecOfferData.elementAt(i).type.equals("Unknown")){
           	 list.add( DataArray.getInstance().vecOfferData.elementAt(i));
            }
            }
 	    }else if(showString == 4 ){
 	    	
            list.clear();
            
            for(int i =0 ;i<len;i++){
          	  if(DataArray.getInstance().vecOfferData.elementAt(i).type.equals("GuestList")){
           	 list.add( DataArray.getInstance().vecOfferData.elementAt(i));
            }
            }
 	    }
    	 notifyDataSetChanged();
     }
     @SuppressWarnings("deprecation")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	// TODO Auto-generated method stub
    	 View rowView = convertView;
    	 if(rowView == null){
    		 LayoutInflater inflater = this.context.getLayoutInflater();
    	      rowView = inflater.inflate(R.layout.offerlayoutnew, null);
    	      // configure view holder
    	      ViewHolder viewHolder = new ViewHolder();
    	      viewHolder.textHeader = (TextView) rowView.findViewById(R.id.HeaderOfferLayout);
    	      viewHolder.textTiming = (TextView) rowView.findViewById(R.id.timingOfferLayout);
    	      viewHolder.textVenueName = (TextView) rowView.findViewById(R.id.venueNameOfferLayout);
    	      viewHolder.view = (ImageView) rowView
    	          .findViewById(R.id.imageViewOfferlayout);
    	      rowView.setTag(viewHolder);
    	 }
    	 ViewHolder holder = (ViewHolder) rowView.getTag();
    	    OfferData s = list.get(position);
    	    
    	    
    	   
    	    if(s== null || s.btmmap == null){
    	        rowView.setBackgroundResource(R.drawable.striker);
    	        if(s!= null && s.isBitmapRequested == false){
	    	        /*DownloadBitmapTask task = new DownloadBitmapTask(s.url, s.pos);
	    	        Void arr[] = null;
	    	        task.execute(arr);
	    	        s.isBitmapRequested = true;*/
    	        }
    	    }
    	    else  if (s!= null ){
    	    
    	    	if(s.smallBitmap == null && s.btmmap != null){
                     
    	    		  int width = s.btmmap.getWidth();
    	                
    	              int   height = s.btmmap.getHeight();
    	                
    	            Matrix matrix = new Matrix();
    	            float scaleWidth = ((float) 200) / width;
    	            float scaleHeight = ((float) 200) / height;
    	            matrix.postScale(scaleWidth, scaleHeight);
    	    		s.smallBitmap = Bitmap.createBitmap(s.btmmap, 0, 0,width, height, matrix, true);
    	    		
    	    	}
    	    	Drawable drawable = new BitmapDrawable(this.context.getResources(), s.smallBitmap);
    	    	rowView.setBackgroundDrawable(drawable);
    	    }
    	    if(holder.view != null){
    	    	Drawable drawable  = null;
    	    	if(s.type.equals("Food")){
    	    		holder.view.setImageResource(R.drawable.fastfood);
    	    	}else if(s.type.equals("Drink")){
    	    		holder.view.setImageResource(R.drawable.bottle_and_glass);
    	    	}else if(s.type.endsWith("Unknown")){
    	    		holder.view.setImageResource(R.drawable.star);
    	    	}else if(s.type.equals("GuestList")){
    	    		holder.view.setImageResource(R.drawable.heart);
    	    	}
    	    	//holder.view.setImageResource(R.drawable.fastfood);
    	    }
    	    if(holder.textHeader != null)
    	    holder.textHeader.setText(s.header);
    	    if(holder.textTiming != null)
    	    holder.textTiming.setText(s.timing);
    	    if(holder.textVenueName != null){
    	    String venueName = s.venueName;
    	      venueName+= "\n";
    	      venueName+= DataArray.getInstance().vecVenueData.elementAt(s.venuePos).distance;
    	      holder.textVenueName.setText(venueName);
    	    }
    	    int venuePos = s.venuePos;
    	    String dist = DataArray.getInstance().vecVenueData.elementAt(venuePos).distance;
    	   
            return rowView;
    	//return super.getView(position, convertView, parent);
    }
     @Override
     public int getCount() {
    	 
    		 return list.size();
    	 
     }
}

