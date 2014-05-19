package com.example.partyy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class SplashScreenReceiver extends BroadcastReceiver{
	public static final String ACTION_RESP = "CallAccept";

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		if(arg1.getAction() == "UserRegistered"){
			Intent i = new Intent(arg0, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
            SplashScreenApp.getInstance().getApplicationContext().startActivity(i);
		}
		else if(arg1.getAction() == "UserAlreadyRegistered"){
			Intent i = new Intent(arg0, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
           SplashScreenApp.getInstance().getApplicationContext().startActivity(i);
		}
		else if(arg1.getAction() == "FirstTime"){
			Intent i = new Intent(arg0, RegistrationActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
           SplashScreenApp.getInstance().getApplicationContext().startActivity(i);
		}
	}

}
