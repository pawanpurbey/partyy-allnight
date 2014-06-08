package com.example.partyy;

import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class JSONStringRetreiver extends AsyncTask<String,Void,String>{
	private Context context;
	public JSONStringRetreiver(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		ServiceHandler sh = new ServiceHandler();
		// Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(params[0], ServiceHandler.GET);
        Log.d("Response: ", "> " + jsonStr);
		// TODO Auto-generated method stub
		return jsonStr;
		
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		//Start activity from here
		JSONParser parser = new JSONParser(result);
		parser.Parse();
		//Now we have the retreived vector in parser
		DataArray.getInstance().vecVenueData = parser.vecVenue;
		DataArray.getInstance().vecOfferData = parser.vecOffer;
		DataArray.getInstance().vecEventData = parser.vecEvent;
		if(StateMachine.getInstance().isFirstTime == false || StateMachine.getInstance().isUserRegistered ==true){
		   Intent i = new Intent(context, MainActivity.class);
		   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   context.startActivity(i);
		   
		}
		StateMachine.getInstance().setDataRetreived(true);
	}
    
}
