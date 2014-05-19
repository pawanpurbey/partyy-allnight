package com.example.partyy;

import java.util.HashMap;



import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;


	public class SQLiteAsyncTask extends AsyncTask<User ,Integer, Boolean>{
		private Context context;
		public int function ;
		public SQLiteAsyncTask(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(function == 0){
				Intent intent =  new Intent();
		           intent.setAction("UserRegistered");
		           SplashScreenApp.getInstance().getApplicationContext().sendBroadcast(intent);
		        
			}
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		@Override
		protected Boolean doInBackground(User... params) {
			// TODO Auto-generated method stub
			if(function == 0){
			SqliteController controller = new SqliteController(this.context);
			controller.SaveUser(params[0]);
			
			}else {
				SqliteController controller = new SqliteController(this.context);
				User user = controller.getUser();
				if(user ==  null || user.name  == ""){
					 Intent intent =  new Intent();
	   		           intent.setAction("FirstTime");
	   		           SplashScreenApp.getInstance().getApplicationContext().sendBroadcast(intent);
	   		        
					return false;
				}else
				{
					Intent intent =  new Intent();
	   		           intent.setAction("UserAlreadyRegistered");
	   		           SplashScreenApp.getInstance().getApplicationContext().sendBroadcast(intent);
	   		        
				}
			}
			return true;
		}

	}

