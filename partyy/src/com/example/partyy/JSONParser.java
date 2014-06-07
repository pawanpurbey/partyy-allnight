package com.example.partyy;

import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

public class JSONParser {
	String jsonStr;
    JSONParser(String s){
	   jsonStr =s;
    }
    Vector<VenueData> vecVenue = new Vector<VenueData>();
    Vector<OfferData> vecOffer = new Vector<OfferData>();
    void Parse(){
    	//OfferData data = new OfferData();
    	HashMap<String,VenueData> mapVenue = new HashMap<String, VenueData>();
    	try {
			JSONObject reader = new JSONObject(jsonStr);
			JSONArray arr = reader.getJSONArray("venue");
			int len = arr.length();
			for(int i = 0;i<len ;i++){
				VenueData data = new VenueData();
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
				data.url = obj.getString("image");
				data.pos = i;
				integer i1 = new integer();
				
				mapVenue.put(data.Name, data);
				vecVenue.add(data);
			}
			//now fill offerData
			JSONArray arrOffer = reader.getJSONArray("offer");
			len = arrOffer.length();
			for(int i = 0;i<len ;i++){
				/*"header": "Free drinks for 2",
			      "photoString": "asdadaa",
			      "type": "Food",
			      "isFeatured": "1",
			      "timeInfo": "11AM - 12 AM",
			      "startDate": "06/18/2014",
			      "endDate": "06/10/2014",
			      "venue": {
			        "_id": "5380782bd993780200d20bb3",
			        "name": "ABCD"
			      },
			      "_id": "538ad3349c764f02007a4000"*/
				OfferData data = new OfferData();
				JSONObject obj = arrOffer.getJSONObject(i);
				data.header = obj.getString("header");
				data.photoString = obj.getString("photoString");
				data.type = obj.getString("type");
				data.isFeatured = obj.getString("isFeatured");
				data.timing = obj.getString("timeInfo");
				
				data.type = obj.getString("type");
				JSONObject venueOffer = obj.getJSONObject("venue");
				//data.url = obj.getString("image");
				
				String venueName = venueOffer.getString("name");
				data.venueName = venueName;
				if(mapVenue.get(venueName) != null){
				data.venuePos = mapVenue.get(venueName).pos;
				}
				vecOffer.add(data);
			}
			mapVenue = null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
