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
		User user;
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(function == 0){
				Intent i = new Intent(context, MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
	            context.startActivity(i);
		        
			}
			else if(function == 1){
				if(result == false || user ==  null || user.name  == ""){
					Intent i = new Intent(context, RegistrationActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					
		           context.startActivity(i);
				}else
				{
					Intent i = new Intent(context, MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					
		           SplashScreenApp.getInstance().getApplicationContext().startActivity(i);
	   		        
				}
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
				user = controller.getUser();
				if(user ==  null || user.name  == ""){
					return false;
				}
			}
			return true;
		}

	}

