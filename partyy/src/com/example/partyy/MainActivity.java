package com.example.partyy;

import java.util.ArrayList;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	//removes the title bars
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.activity = this;
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        setRequestedOrientation(ActivityInfo  
        		  .SCREEN_ORIENTATION_PORTRAIT);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */
    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public Activity activity;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
        	if (BuildConfig.DEBUG) {
        		Log.d("screen number: ", "> " + position);
        		} 
        	
        	DummySectionFragment fragment = new DummySectionFragment();
            fragment.activity = this.activity;
            Bundle args = new Bundle();
            String s= new String();
            if(position == 0){
            	s = "Offer";
            }else if(position == 1){
            	s = "event";
            }else{
            	s = "venue";
            }
            fragment.description = s;
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    // Changes by Summved
    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment implements OnLongClickListener{
        public static final String ARG_SECTION_NUMBER = null;
		/**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public Activity activity;
        private ListView viewEvent;
        private ListView viewOffer;
        private ListView viewVenue;
        
        public  String description;
        public DummySectionFragment() {
        	
        }

        @SuppressLint("NewApi")
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	View rootView = null;
                if(this.description == "Offer" ){
                	 rootView = inflater.inflate(R.layout.offerfragment, container, false);
                    /*OfferData[] values = new OfferData[10];
                    for(int i = 0;i<10;i++){
                    	if(values[i] != null)
                    	values[i].Name = "Name" + i;
                    	else{
                    		values[i] = new OfferData();
                    		values[i].Name = "Name" + i;
                    	}
                    	
                    }*/
                	//ArrayList<OfferData> value =  new ArrayList<OfferData>();
                	Object[] val = DataArray.getInstance().vec.toArray();
                	int len = val.length;
                	OfferData[] values = new OfferData[len];
                    for(int i = 0;i<len;i++){
                    	
                    	  values[i] = (OfferData)val[i];
                    	
                    	
                    }
	                eventarrayadapter adapter = new eventarrayadapter(this.activity, values);
	                viewOffer = (ListView)rootView.findViewById(R.id.listViewOffer);
	                viewOffer.setAdapter(adapter);
	                //view.setLongClickable(true);
	                viewOffer.setOnLongClickListener(this);
	                viewOffer.setOnItemClickListener(new OnItemClickListener() {
	                	  

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							ListView v= (ListView)arg0;
							//int pos = v.getSelectedItemPosition();
							//int id = v.getSelectedItemId();
							OfferData data = DataArray.getInstance().vec.elementAt(arg2);
							Toast.makeText(getActivity(),data.Name , Toast.LENGTH_SHORT).show();
						}
	                	}); 
	              
					
	                
                }
                else if( this.description == "event"){
                	rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
                	Object[] val = DataArray.getInstance().vec.toArray();
                	int len = val.length;
                	OfferData[] values = new OfferData[1];
                    for(int i = 0;i<len;i++){
                    	  String s =  DataArray.getInstance().vec.elementAt(i).Name;
                    	  if(DataArray.getInstance().vec.elementAt(i).Name.equals("ABCD")){
                    	       values[0] = (OfferData)val[i];
                    	  }
                    	
                    	
                    }
	                eventarrayadapter adapter = new eventarrayadapter(this.activity, values);
	                viewEvent = (ListView)rootView.findViewById(R.id.listViewEvents);
	                viewEvent.setAdapter(adapter);
	                //view.setLongClickable(true);
	                viewEvent.setOnLongClickListener(this);
	                viewEvent.setOnItemClickListener(new OnItemClickListener() {
	                	  

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							ListView v= (ListView)arg0;
							ListAdapter listadapter = v.getAdapter();
							eventarrayadapter adapter = (eventarrayadapter)listadapter;
							OfferData viewdata = adapter.getData(arg2);
							
							//int pos = v.getSelectedItemPosition();
							//int id = v.getSelectedItemId();
							OfferData data = DataArray.getInstance().vec.elementAt(viewdata.pos);
							Toast.makeText(getActivity(),data.Name , Toast.LENGTH_SHORT).show();
						}
	                	}); 
                }
                else if( this.description == "venue"){
                	rootView = inflater.inflate(R.layout.venuesfragment, container, false);
                	Object[] val = DataArray.getInstance().vec.toArray();
                	int len = val.length;
                	OfferData[] values = new OfferData[1];
                    for(int i = 0;i<len;i++){
                    	 if(DataArray.getInstance().vec.elementAt(i).Name.equals("Striker")){
                  	       values[0] = (OfferData)val[i];
                  	  }
                    	
                    	
                    }
	                eventarrayadapter adapter = new eventarrayadapter(this.activity, values);
	                viewVenue = (ListView)rootView.findViewById(R.id.listViewVenues);
	                viewVenue.setAdapter(adapter);
	                //view.setLongClickable(true);
	                viewVenue.setOnLongClickListener(this);
	                final Context context = this.getActivity().getApplicationContext();
	                viewVenue.setOnItemClickListener(new OnItemClickListener() {
	                	  

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							ListView v= (ListView)arg0;
							ListAdapter listadapter = v.getAdapter();
							eventarrayadapter adapter = (eventarrayadapter)listadapter;
							OfferData viewdata = adapter.getData(arg2);
							
							
							Intent i = new Intent(context, OpenVenueActivity.class);
							 
							  //Create the bundle
							  Bundle bundle = new Bundle();
							  //Add your data to bundle
							  bundle.putInt("Pos", viewdata.pos);
							  //Add the bundle to the intent
							  i.putExtras(bundle);
							  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(i);
							OfferData data = DataArray.getInstance().vec.elementAt(viewdata.pos);
							Toast.makeText(getActivity(),data.Name , Toast.LENGTH_SHORT).show();
						}
	                	}); 
                }
                
            return rootView;
        }

		@Override
		public boolean onLongClick(View arg0) {
			// TODO Auto-generated method stub
			int k = 0;
			return false;
		}
    }
  


}
