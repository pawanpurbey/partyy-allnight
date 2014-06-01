package com.example.partyy;

public class StateMachine {
   private StateMachine(){
	   
   }
   static Object _lock = new Object();
   static StateMachine instance = new StateMachine();
   public static StateMachine getInstance(){
	    return instance;
   }
   public boolean isFirstTime = false;
   public boolean isUserRegistered = false;
   public boolean isDataRetreived = false;
   public  void setDataRetreived(boolean b){
	   synchronized (_lock) {
		isDataRetreived = true;
		
	}
   }
}
