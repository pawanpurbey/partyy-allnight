package com.example.partyy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class eventarrayadapter extends ArrayAdapter<OfferData>{
     private Activity context;
     //Later on String will be changed by our own class
     private OfferData[] val;
     private static eventarrayadapter _instance;
     public static eventarrayadapter getInstance(){
    	 return _instance;
     }
     public eventarrayadapter(Activity c,OfferData[] val){
    	 super(c, R.layout.eventlayout,val);
    	 this.context = c;
    	 this.val = val;
    	 _instance = this;
     }
     public static class ViewHolder{
    	 public TextView text;
    	 public ImageView view;
     }
     public OfferData getData(int pos){
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
    	      viewHolder.text = (TextView) rowView.findViewById(R.id.textView1);
    	      /*viewHolder.view = (ImageView) rowView
    	          .findViewById(R.id.imageViewEvent);*/
    	      rowView.setTag(viewHolder);
    	 }
    	 ViewHolder holder = (ViewHolder) rowView.getTag();
    	    OfferData s = val[position];
    	    if(s != null)
    	    holder.text.setText(s.header);
    	    
    	    /*if (s.Name.startsWith("Windows7") || s.Name.startsWith("iPhone")
    	        || s.Name.startsWith("Solaris")) {
    	      holder.view.setImageResource(R.drawable.ic_launcher);
    	    } else {
    	      holder.view.setImageResource(R.drawable.party);
    	    }*/
    	    if(s== null || s.btmmap == null){
    	        rowView.setBackgroundResource(R.drawable.party);
    	        if(s!= null && s.isBitmapRequested == false){
	    	        /*DownloadBitmapTask task = new DownloadBitmapTask(s.url, s.pos);
	    	        Void arr[] = null;
	    	        task.execute(arr);
	    	        s.isBitmapRequested = true;*/
    	        }
    	    }
    	    else  if (s!= null){
    	    	Bitmap bitmap = s.btmmap;
    	    	
    	    	Drawable drawable = new BitmapDrawable(this.context.getResources(), bitmap);
    	    	rowView.setBackgroundDrawable(drawable);
    	    }
    	    	
        return rowView;
    	//return super.getView(position, convertView, parent);
    }
}
