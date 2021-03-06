package com.example.partyy;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import android.widget.Button;
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
    PagerTitleStrip titleStrip;
    public static int Screen = 0;
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
         titleStrip = (PagerTitleStrip)this.findViewById(R.id.pager_title_strip);
         titleStrip.setBackgroundColor(Color.rgb(51, 181, 229));
         int h = titleStrip.getLayoutParams().height;
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
				if(arg0 == 0){
					titleStrip.setBackgroundColor(Color.rgb(51, 181, 229));
					int h = titleStrip.getLayoutParams().height;
					int k = 0;
					Screen =  0;
				}
				else if(arg0 == 1){
					titleStrip.setBackgroundColor(Color.rgb(0, 0, 0));
					int h = titleStrip.getLayoutParams().height;
					int k = 0;
					Screen = 1;
				}else{
					titleStrip.setBackgroundColor(Color.rgb(255, 165, 0));
					int h = titleStrip.getLayoutParams().height;
					int k = 0;
					Screen = 2;
				}
				StateMachine.getInstance().currentFragment = arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
       
        Timer timerUpdateDistance = new Timer();
        CalculateDistanceTimerTask taskCalcDist = new CalculateDistanceTimerTask();
        timerUpdateDistance.schedule(taskCalcDist, 100, 1000*60);
        //timerUpdateDistance.
        DownlaodBitmapThread thread = new DownlaodBitmapThread();
        thread.start();
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
            	s = "venue";
            }else if(position == 1){
            	s = "event";
            }else{
            	s = "Offer";
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

    
    public static class DummySectionFragment extends Fragment implements OnClickListener{
        public static final String ARG_SECTION_NUMBER = null;
		/**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public Activity activity;
        private ListView viewEvent;
        private ListView viewOffer;
        private ListView viewVenue;
        Button offerDrinks;
        Button offerFood;
        Button offerAll;
        Button offerGuestList;
        Button offerUnknown;
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
                    
                	
                	int len  = 0;
                	if(DataArray.getInstance().vecOfferData != null){
                	   Object[] val = DataArray.getInstance().vecOfferData.toArray();
                	   len = val.length;
                	}
                	ArrayList<OfferData> values = new ArrayList<OfferData>();
                    for(int i = 0;i<len;i++){
                    	
                    	  values.add(DataArray.getInstance().vecOfferData.elementAt(i));
                    	
                    	
                    }
                    offerArrayAdapter adapter;
                    if(offerArrayAdapter.getInstance()== null){
	                 adapter = new offerArrayAdapter(this.activity, values);
	                 
                    }else{
                    	adapter = offerArrayAdapter.getInstance();
                    }
	                viewOffer = (ListView)rootView.findViewById(R.id.listViewOffer);
	                View viewHeader = inflater.inflate(R.layout.offerheaderview, viewOffer,false);
	                viewOffer.addHeaderView(viewHeader);
	                viewOffer.setAdapter(adapter);
	                
	                offerDrinks = (Button) viewOffer.findViewById(R.id.offer_drinks);
	                if(offerDrinks!= null){
	                	offerDrinks.setOnClickListener(this);
	                	
	                }
	                offerFood =  (Button) viewOffer.findViewById(R.id.offer_food);
	                if(offerFood != null){
	                	offerFood.setOnClickListener(this);
	                }
	                 offerAll =  (Button) viewOffer.findViewById(R.id.offer_all);
	                if(offerAll != null){
	                	offerAll.setOnClickListener(this);
	                }
	                offerGuestList =  (Button) viewOffer.findViewById(R.id.offer_guest_list);
	                if(offerGuestList != null){
	                	offerGuestList.setOnClickListener(this);
	                }
	                offerUnknown =  (Button) viewOffer.findViewById(R.id.offer_others);
	                if(offerUnknown != null){
	                	offerUnknown.setOnClickListener(this);
	                }
	                final Context context = this.getActivity().getApplicationContext();
	                viewOffer.setOnItemClickListener(new OnItemClickListener() {
	                	  

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							ListView v= (ListView)arg0;
							
							  OfferData data = DataArray.getInstance().vecOfferData.elementAt(arg2-1);
							  Intent i = new Intent(context, OpenOfferActivity.class);
							 
							  //Create the bundle
							  Bundle bundle = new Bundle();
							  //Add your data to bundle
							  bundle.putInt("Pos", data.ownPosition);
							  //Add the bundle to the intent
							  i.putExtras(bundle);
							  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(i);
							
						}
	                	}); 
	               
					
	                
                }
                else if( this.description == "event"){
                	rootView = inflater.inflate(R.layout.eventfragment, container, false);
                	int len =0;
                	if(DataArray.getInstance().vecEventData != null)
                	   len = DataArray.getInstance().vecEventData.size();
                	ArrayList<EventData> list = new ArrayList<EventData>();
                    for(int i = 0;i<len;i++){
                    	   list.add(DataArray.getInstance().vecEventData.elementAt(i));
                    }
	                eventarrayadapter adapter = new eventarrayadapter(this.activity, list);
	                viewEvent = (ListView)rootView.findViewById(R.id.listView121Events);
	                viewEvent.setAdapter(adapter);
	               
	                final Context context = this.getActivity().getApplicationContext();
		               
	                viewEvent.setOnItemClickListener(new OnItemClickListener() {
	                	  

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							ListView v= (ListView)arg0;
							ListAdapter listadapter = v.getAdapter();
							eventarrayadapter adapter = (eventarrayadapter)listadapter;
							EventData viewdata = DataArray.getInstance().vecEventData.elementAt(arg2);
							Intent i = new Intent(context, openEventActivity.class);
							 
							  //Create the bundle
							  Bundle bundle = new Bundle();
							  //Add your data to bundle
							  bundle.putInt("Pos", viewdata.ownPosition);
							  //Add the bundle to the intent
							  i.putExtras(bundle);
							  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(i);
							//VenueData data = DataArray.getInstance().vecVenueData.elementAt(viewdata.pos);
							
							
						}
	                	}); 
	               
                }
                else if(  this.description == "venue"){
                	rootView = inflater.inflate(R.layout.venuesfragment, container, false);
                	int len =0;
                	
                	if(DataArray.getInstance().vecVenueData != null){
                	 len = DataArray.getInstance().vecVenueData.size();
                	 
                	}
                	ArrayList<VenueData> list = new ArrayList<VenueData>();
                    for(int i = 0;i<len;i++){
                    	list.add(DataArray.getInstance().vecVenueData.elementAt(i));
                    }
	                venueArayAdapater adapter = new venueArayAdapater(this.activity, list);
	                viewVenue = (ListView)rootView.findViewById(R.id.listViewVenues);
	                viewVenue.setAdapter(adapter);
	                //view.setLongClickable(true);
	                
	                final Context context = this.getActivity().getApplicationContext();
	                viewVenue.setOnItemClickListener(new OnItemClickListener() {
	                	  

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							 ListView v= (ListView)arg0;
							 ListAdapter listadapter = v.getAdapter();
							 venueArayAdapater adapter = (venueArayAdapater)listadapter;
							 VenueData viewdata = DataArray.getInstance().vecVenueData.elementAt(arg2);
							
							
							  Intent i = new Intent(context, OpenVenueActivity.class);
							 
							  //Create the bundle
							  Bundle bundle = new Bundle();
							  //Add your data to bundle
							  bundle.putInt("Pos", viewdata.pos);
							  //Add the bundle to the intent
							  i.putExtras(bundle);
							  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(i);
                             
							//VenueData data = DataArray.getInstance().vecVenueData.elementAt(viewdata.pos);
							//Toast.makeText(getActivity(),data.Name , Toast.LENGTH_SHORT).show();
						}
	                	}); 
	                
                }
                
            return rootView;
        }

		

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id  = v.getId();
			if(id == R.id.offer_drinks){
				if(offerArrayAdapter.getInstance().showString != offerArrayAdapter.getInstance().SHOW_DRINKS){
					offerArrayAdapter.getInstance().showString = offerArrayAdapter.getInstance().SHOW_DRINKS;
					offerArrayAdapter.getInstance().ChangeData();
					offerDrinks.setBackgroundColor(Color.rgb(255, 165, 0));
			        offerFood.setBackgroundColor(Color.rgb(0,0,0));
			        offerAll.setBackgroundColor(Color.rgb(0,0,0));
			        offerGuestList.setBackgroundColor(Color.rgb(0,0,0));
			        offerUnknown.setBackgroundColor(Color.rgb(0,0,0));

			    }
			}else if(id == R.id.offer_food){
				if(offerArrayAdapter.getInstance().showString != offerArrayAdapter.getInstance().SHOW_FOOD){
					offerArrayAdapter.getInstance().showString = offerArrayAdapter.getInstance().SHOW_FOOD;
					offerArrayAdapter.getInstance().ChangeData();
					offerDrinks.setBackgroundColor(Color.rgb(0,0,0));
			        offerFood.setBackgroundColor(Color.rgb(255, 165, 0));
			        offerAll.setBackgroundColor(Color.rgb(0,0,0));
			        offerGuestList.setBackgroundColor(Color.rgb(0,0,0));
			        offerUnknown.setBackgroundColor(Color.rgb(0,0,0));
			    }
			}else if(id == R.id.offer_all){
				if(offerArrayAdapter.getInstance().showString != offerArrayAdapter.getInstance().SHOW_ALL){
					offerArrayAdapter.getInstance().showString = offerArrayAdapter.getInstance().SHOW_ALL;
					offerArrayAdapter.getInstance().ChangeData();
					offerDrinks.setBackgroundColor(Color.rgb(0,0,0));
			        offerFood.setBackgroundColor(Color.rgb(0,0,0));
			        offerAll.setBackgroundColor(Color.rgb(255, 165, 0));
			        offerGuestList.setBackgroundColor(Color.rgb(0,0,0));
			        offerUnknown.setBackgroundColor(Color.rgb(0,0,0));
			    }
			}else if(id == R.id.offer_guest_list){
				if(offerArrayAdapter.getInstance().showString != offerArrayAdapter.getInstance().SHOW_GUESTLIST){
					offerArrayAdapter.getInstance().showString = offerArrayAdapter.getInstance().SHOW_GUESTLIST;
					offerArrayAdapter.getInstance().ChangeData();
					offerDrinks.setBackgroundColor(Color.rgb(0,0,0));
			        offerFood.setBackgroundColor(Color.rgb(0,0,0));
			        offerAll.setBackgroundColor(Color.rgb(0,0,0));
			        offerGuestList.setBackgroundColor(Color.rgb(255, 165, 0));
			        offerUnknown.setBackgroundColor(Color.rgb(0,0,0));
			    }
			}else if(id == R.id.offer_others){
				if(offerArrayAdapter.getInstance().showString != offerArrayAdapter.getInstance().SHOW_UNKNOWN){
					offerArrayAdapter.getInstance().showString = offerArrayAdapter.getInstance().SHOW_UNKNOWN;
					offerArrayAdapter.getInstance().ChangeData();
					offerDrinks.setBackgroundColor(Color.rgb(0,0,0));
			        offerFood.setBackgroundColor(Color.rgb(0,0,0));
			        offerAll.setBackgroundColor(Color.rgb(0,0,0));
			        offerGuestList.setBackgroundColor(Color.rgb(0,0,0));
			        offerUnknown.setBackgroundColor(Color.rgb(255, 165, 0));
			    }
			}
		}
    }
  


}
