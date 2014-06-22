package com.example.partyy;

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

public class eventarrayadapter extends ArrayAdapter<EventData>{
     private Activity context;
     //Later on String will be changed by our own class
     private EventData[] val;
     private static eventarrayadapter _instance;
     public static eventarrayadapter getInstance(){
    	 return _instance;
     }
     
     public eventarrayadapter(Activity activity, EventData[] values) {
		// TODO Auto-generated constructor stub
    	 super(activity, R.layout.eventlayout,values);
    	 this.context = activity;
    	 this.val = values;
    	 _instance = this;
	}
	
	public static class ViewHolder{
    	 public TextView text;
    	 public TextView textSmall;
     }
     public EventData getData(int pos){
    	 if(pos>=0 && pos<val.length){
    	     return val[pos];
    	 }else{
    		 return null;
    	 }
     }
     @SuppressWarnings("deprecation")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	// TODO Auto-generated method stub
    	 View rowView = convertView;
    	 if(rowView == null){
    		 LayoutInflater inflater = this.context.getLayoutInflater();
    	      rowView = inflater.inflate(R.layout.eventlayout, null);
    	      // configure view holder
    	      ViewHolder viewHolder = new ViewHolder();
    	      viewHolder.text = (TextView) rowView.findViewById(R.id.textVieweventLayout);
    	      viewHolder.textSmall = (TextView)rowView.findViewById(R.id.venue_DistlEventLayout);
    	      /*viewHolder.view = (ImageView) rowView
    	          .findViewById(R.id.imageViewEvent);*/
    	      rowView.setTag(viewHolder);
    	 }
    	 ViewHolder holder = (ViewHolder) rowView.getTag();
    	    EventData s = val[position];
    	    if(s != null){
    	       holder.text.setText(s.title);
    	    
    	    }
    	   
    	    if(s== null || s.btmmap == null){
    	        rowView.setBackgroundResource(R.drawable.striker);
    	        
    	    }
    	    else  if (s!= null){
    	    	if(s.smallBitmap == null && s.btmmap != null){
                    
    	    		  int width = s.btmmap.getWidth();
    	                 //Log.i("Old width................", width + "");
    	              int   height = s.btmmap.getHeight();
    	                 //Log.i("Old height................", height + "");
    	 
    	            Matrix matrix = new Matrix();
    	            float scaleWidth = ((float) 200) / width;
    	            float scaleHeight = ((float) 200) / height;
    	            matrix.postScale(scaleWidth, scaleHeight);
    	    		s.smallBitmap = Bitmap.createBitmap(s.btmmap, 0, 0,width, height, matrix, true);
    	    		
    	    	}
    	    	Drawable drawable = new BitmapDrawable(this.context.getResources(), s.smallBitmap);
    	    	rowView.setBackgroundDrawable(drawable);
    	    }
    	    int venuePos = s.venuePos;
    	    String venueDetails = DataArray.getInstance().vecVenueData.elementAt(venuePos).Name;
    	    venueDetails += "\n";
    	    venueDetails += DataArray.getInstance().vecVenueData.elementAt(venuePos).distance;
    	    if(holder.textSmall != null)
    	    holder.textSmall.setText(venueDetails);
            return rowView;
    	//return super.getView(position, convertView, parent);
    }
}

