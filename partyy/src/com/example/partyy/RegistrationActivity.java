package com.example.partyy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegistrationActivity extends Activity implements OnClickListener{
	private Button SaveButton;
	private EditText name;
	private RadioGroup grpAge;
	private RadioGroup grpSex;
	private RadioButton rbAge;
	private RadioButton rbSex;
	private String phoneNumber = "";
	public SqliteController controller = new SqliteController(this);
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationlayout);
		SaveButton = (Button)findViewById(R.id.btnSave);
		name = (EditText)findViewById(R.id.editText1);
		SaveButton.setOnClickListener(this);
		grpAge = (RadioGroup)findViewById(R.id.radioGroup1);
		grpSex = (RadioGroup)findViewById(R.id.radioGroup2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		JSONStringRetreiver receiver = new JSONStringRetreiver(this,1);
		//receiver.execute("http://safe-wave-7903.herokuapp.com/venues/totaldata");
		receiver.execute("http://safe-wave-7903.herokuapp.com/venues/totaldata");
		StateMachine.getInstance().isDataRetreived = false;
		StateMachine.getInstance().isFirstTime = true;
		StateMachine.getInstance().isUserRegistered = false;
		TelephonyManager Mgr=(TelephonyManager)this.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);  
		phoneNumber = Mgr.getLine1Number();
		phoneNumber = Mgr.getSimSerialNumber();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		User user = new User();
		String usename = name.getText().toString();
		if(usename != ""){
			int AgeId = grpAge.getCheckedRadioButtonId();
			rbAge = (RadioButton)findViewById(AgeId);
			user.age= rbAge.getText().toString();
			int SexId = grpSex.getCheckedRadioButtonId();
			rbSex = (RadioButton)findViewById(SexId);
			user.sex = rbSex.getText().toString();
			SQLiteAsyncTask task = new SQLiteAsyncTask(this);
			task.function = 0;
			task.execute(user);
			
			
			finish();
		}
		
		
	}

}
