package com.example.partyy;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
	String jsonStr;
    JSONParser(String s){
	   jsonStr =s;
    }
    Vector<OfferData> vec = new Vector<OfferData>();
    
    void Parse(){
    	//OfferData data = new OfferData();
    	try {
			JSONObject reader = new JSONObject(jsonStr);
			JSONArray arr = reader.getJSONArray("venue");
			int len = arr.length();
			for(int i = 0;i<len ;i++){
				OfferData data = new OfferData();
				JSONObject obj = arr.getJSONObject(i);
				data.Name = obj.getString("name");
				data.sDescription = obj.getString("sDescription");
				data.bDescription = obj.getString("bDescription");
				data.City = obj.getString("city");
				data.timing = obj.getString("timings");
				data.address = obj.getString("address");
				data.phone = obj.getString("phone");
				data.type = obj.getString("type");
				data.ID =   obj.getString("_id");
				//data.url = obj.getString("image");
				data.pos = i;
				vec.add(data);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
