
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

public class venueArayAdapater extends ArrayAdapter<VenueData>{
     private Activity context;
     //Later on String will be changed by our own class
     private VenueData[] val;
     private static venueArayAdapater _instance;
     public venueArayAdapater(Activity c,VenueData[] val){
    	 super(c, R.layout.venuelayout,val);
    	 this.context = c;
    	 this.val = val;
    	 _instance = this;
     }
     public static venueArayAdapater getInstance(){
    	 return _instance;
     }
     public static class ViewHolder{
    	 public TextView text;
    	 public ImageView view;
    	 public TextView textSmall;
     }
     public VenueData getData(int pos){
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
    	      rowView = inflater.inflate(R.layout.venuelayout, null);
    	      // configure view holder
    	      ViewHolder viewHolder = new ViewHolder();
    	      viewHolder.text = (TextView) rowView.findViewById(R.id.textViewEventLayout);
    	      /*viewHolder.view = (ImageView) rowView
    	          .findViewById(R.id.imageViewEvent);*/
    	      viewHolder.textSmall = (TextView)rowView.findViewById(R.id.venue_DistlTextViewVenueLayout);
    	      rowView.setTag(viewHolder);
    	 }
    	 ViewHolder holder = (ViewHolder) rowView.getTag();
    	 VenueData s = val[position];
    	    if(s != null)
    	    holder.text.setText(s.Name);
    	    if(holder.textSmall != null &&  s.distance.equals("-1") == false){
    	    	holder.textSmall.setText(s.distance);
    	    }
    	    //holder.textSmall.setText(s.Name);
    	    
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
    	    	if(s.sbtmmap == null && s.btmmap != null){
                    
  	    		  int width = s.btmmap.getWidth();
  	                 //Log.i("Old width................", width + "");
  	              int   height = s.btmmap.getHeight();
  	                 //Log.i("Old height................", height + "");
  	 
  	            Matrix matrix = new Matrix();
  	            float scaleWidth = ((float) 200) / width;
  	            float scaleHeight = ((float) 200) / height;
  	            matrix.postScale(scaleWidth, scaleHeight);
  	    		s.sbtmmap = Bitmap.createBitmap(s.btmmap, 0, 0,width, height, matrix, true);
  	    		
  	    	}
  	    	Drawable drawable = new BitmapDrawable(this.context.getResources(), s.sbtmmap);
  	    	rowView.setBackgroundDrawable(drawable);
    	   }
    	    	
        return rowView;
    	//return super.getView(position, convertView, parent);
    }
}
