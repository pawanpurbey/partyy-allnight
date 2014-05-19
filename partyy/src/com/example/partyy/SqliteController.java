package com.example.partyy;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteController extends SQLiteOpenHelper{
      public SqliteController(Context applicationContext) {
		// TODO Auto-generated constructor stub
    	  super(applicationContext, "PartyDB", null, 1);
	}
      @Override
    public void onCreate(SQLiteDatabase db) {
    	// TODO Auto-generated method stub
    	  String query;
          query = "CREATE TABLE if not exists UserParty (UserName TEXT,UserAge TEXT,Sex TEXT, PhoneNumber TEXT)";
          db.execSQL(query);
          

    }
      @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	// TODO Auto-generated method stub
    	
    }
      
    public void SaveUser(User user){
    	SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserName", user.name);
        values.put("UserAge", user.age);
        values.put("Sex", user.sex);
        values.put("PhoneNumber", user.phoneNumber);
        database.insert("UserParty", null, values);
        database.close();

    }
    public User  getUser(){
    	//ArrayList<StudentCredentials> retList = new ArrayList<StudentCredentials>();
    	SQLiteDatabase database = this.getWritableDatabase();
    	String Query =  "SELECT * from UserParty";
    	Cursor cursor = database.rawQuery(Query,null);
    	User user = null;
    	if(cursor.moveToFirst()){
    		do
    		{
    			 user = new User();
    			user.name = cursor.getString(0);
    			user.age = cursor.getString(1);
    			user.sex = cursor.getString(2);
    			user.phoneNumber = cursor.getString(3);
    			return user;
    		}
    	    while(cursor.moveToNext());
    		
    	}
    	return user;
    }
	
}
