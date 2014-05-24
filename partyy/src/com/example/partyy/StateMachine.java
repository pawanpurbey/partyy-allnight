package com.example.partyy;

public class StateMachine {
   private StateMachine(){
	   
   }
   static StateMachine instance = new StateMachine();
   public static StateMachine getInstance(){
	    return instance;
   }
   public boolean isFirstTime = false;
   public boolean isUserRegistered = false;
   public boolean isDataRetreived = false;
}
