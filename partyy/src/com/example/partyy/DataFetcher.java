package com.example.partyy;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class DataFetcher extends AsyncTask<Void,Void,Void>{
	private DataFetcher(){}
	public String getResult(){
		
		return jsonStr;
	}
	private static DataFetcher instance = new DataFetcher();
	public static DataFetcher getInstance(){
		return instance;
	}
	private static String url = "http://api.androidhive.info/contacts/";
	public static String jsonStr;
	protected void  onPreExecute(){
		super.onPreExecute();
		
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		ServiceHandler sh = new ServiceHandler();
		// Making a request to url and getting response
        jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
        Log.d("Response: ", "> " + jsonStr);
		// TODO Auto-generated method stub
		return null;
	}
	 protected void onPostExecute(Void result) {
         super.onPostExecute(result);
         // Dismiss the progress dialog
      }

}
