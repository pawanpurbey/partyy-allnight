package com.example.partyy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class eventarrayadapter extends ArrayAdapter<OfferData>{
     private Activity context;
     //Later on String will be changed by our own class
     private OfferData[] val;
     
     public eventarrayadapter(Activity c,OfferData[] val){
    	 super(c, R.layout.eventlayout,val);
    	 this.context = c;
    	 this.val = val;
     }
     public static class ViewHolder{
    	 public TextView text;
    	 public ImageView view;
     }
     @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	// TODO Auto-generated method stub
    	 View rowView = convertView;
    	 if(rowView == null){
    		 LayoutInflater inflater = this.context.getLayoutInflater();
    	      rowView = inflater.inflate(R.layout.eventlayout, null);
    	      // configure view holder
    	      ViewHolder viewHolder = new ViewHolder();
    	      viewHolder.text = (TextView) rowView.findViewById(R.id.textViewEvent);
    	      viewHolder.view = (ImageView) rowView
    	          .findViewById(R.id.imageViewEvent);
    	      rowView.setTag(viewHolder);
    	 }
    	 ViewHolder holder = (ViewHolder) rowView.getTag();
    	    OfferData s = val[position];
    	    holder.text.setText(s.Name);
    	    if (s.Name.startsWith("Windows7") || s.Name.startsWith("iPhone")
    	        || s.Name.startsWith("Solaris")) {
    	      holder.view.setImageResource(R.drawable.ic_launcher);
    	    } else {
    	      holder.view.setImageResource(R.drawable.party);
    	    }
        return rowView;
    	//return super.getView(position, convertView, parent);
    }
}
