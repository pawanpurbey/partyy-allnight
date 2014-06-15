package com.example.partyy;

import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class JSONStringRetreiver extends AsyncTask<String,Void,String>{
	private Context context;
	public int method = 0;
	public JSONStringRetreiver(Context context, int method) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.method = method;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		ServiceHandler sh = new ServiceHandler();
		String jsonStr = null;
		// Making a request to url and getting response
		if(method ==0 ){
           jsonStr = sh.makeServiceCall(params[0], ServiceHandler.POST);
           Log.d("Response: ", "> " + jsonStr);
		}
		else if (method ==1){
			jsonStr = sh.makeServiceCall(params[0], ServiceHandler.GET);
	        Log.d("Response: ", "> " + jsonStr);
		}
		// TODO Auto-generated method stub
		return jsonStr;
		
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		//Start activity from here
		JSONParser parser = new JSONParser(result);
		if(parser.Parse() ==true){
		//Now we have the retreived vector in parser
			DataArray.getInstance().vecVenueData = parser.vecVenue;
			DataArray.getInstance().vecOfferData = parser.vecOffer;
			DataArray.getInstance().vecEventData = parser.vecEvent;
			if(StateMachine.getInstance().isFirstTime == false || StateMachine.getInstance().isMainactivityLaunched == false){
				StateMachine.getInstance().isMainactivityLaunched = true;
			   Intent i = new Intent(context, MainActivity.class);
			   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   context.startActivity(i);
			   
			}else if(StateMachine.getInstance().isFirstTime == true && StateMachine.getInstance().isMainactivityLaunched == true){
				if(SplashScreenApp.getInstance() != null){
					SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
					    public void run() {
					    	if(eventarrayadapter.getInstance() != null){
					            eventarrayadapter.getInstance().notifyDataSetChanged();
					    	}
					    	if(offerArrayAdapter.getInstance() != null){
					           offerArrayAdapter.getInstance().notifyDataSetChanged();
					    	}
					        if(venueArayAdapater.getInstance() != null){
					          venueArayAdapater.getInstance().notifyDataSetChanged();
					        }
					    }
				});
			}
			}
			StateMachine.getInstance().setDataRetreived(true);
		}
	}
    
}
