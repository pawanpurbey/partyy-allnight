package com.example.partyy;

import java.util.HashMap;











import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;


	public class SQLiteAsyncTask extends AsyncTask<User ,Integer, Boolean>{
		//private Context context;
		private Activity activity;
		public int function ;
		public SQLiteAsyncTask(Activity act) {
			// TODO Auto-generated constructor stub
			this.activity = act;
			
		}
		User user;
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(function == 0){
				StateMachine.getInstance().isUserRegistered = true;
				////if(StateMachine.getInstance().isDataRetreived == true){
					StateMachine.getInstance().isMainactivityLaunched = true;
					Intent i = new Intent(this.activity.getApplicationContext(), MainActivity.class);
				    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				    this.activity.startActivity(i);
				    this.activity.finish();
					
				//}
				/*else{
					JSONStringRetreiver receiver = new JSONStringRetreiver(this.context,1);
					StateMachine.getInstance().isMainactivityLaunched = true;
				    receiver.execute("http://safe-wave-7903.herokuapp.com/venues/totaldata");
				    Intent i = new Intent(this.context, MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					this.context.startActivity(i);
				}*/
		    }
			else if(function == 1){
				if(result == false || user ==  null || user.name  == ""){
					Intent i = new Intent(this.activity, RegistrationActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					
					this.activity.startActivity(i);
				}else
				{
					if(DataArray.getInstance().vecVenueData ==null || DataArray.getInstance().vecVenueData.size() ==0 ||DataArray.getInstance().vecOfferData ==null || DataArray.getInstance().vecOfferData.size() == 0 ){
							JSONStringRetreiver receiver = new JSONStringRetreiver(this.activity,1);
							
						    receiver.execute("http://safe-wave-7903.herokuapp.com/venues/totaldata");
						
					}
					
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
			SqliteController controller = new SqliteController(this.activity.getApplicationContext());
			controller.SaveUser(params[0]);
			
			}else {
				SqliteController controller = new SqliteController(this.activity.getApplicationContext());
				user = controller.getUser();
				if(user ==  null || user.name  == ""){
					return false;
				}
			}
			return true;
		}

	}

