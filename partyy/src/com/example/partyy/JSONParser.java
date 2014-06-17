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
    Vector<EventData> vecEvent = new Vector<EventData>();
    boolean Parse(){
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
				JSONObject objLoc = obj.getJSONObject("loc");
				if(objLoc != null){
					JSONArray objCoordiantes = objLoc.getJSONArray("coordinates");
					data.lon = objCoordiantes.getString(0);
					data.lat = objCoordiantes.getString(1);
				}
				String sDescription = data.sDescription;
				/*
		        String[] arrSplit =sDescription.split("LAT:");
		        
		        String[] lon = sDescription.split("LON:");
		        data.lat = arrSplit[1].split(",")[0];
		        data.lon = lon[1];
		        
				integer i1 = new integer();
				*/
				
				mapVenue.put(data.Name, data);
				vecVenue.add(data);
			}
			//now fill offerData
			JSONArray arrOffer = reader.getJSONArray("offer");
			len = arrOffer.length();
			for(int i = 0;i<len ;i++){
				OfferData data = new OfferData();
				JSONObject obj = arrOffer.getJSONObject(i);
				data.header = obj.getString("header");
				data.photoString = obj.getString("photoString");
				data.type = obj.getString("type");
				data.isFeatured = obj.getString("isFeatured");
				data.timing = obj.getString("timeInfo");
				data.startDate = obj.getString("startDate");
				data.endData = obj.getString("endDate");
				data.type = obj.getString("type");
				data.ownPosition = i;
				JSONObject venueOffer = obj.getJSONObject("venue");
				//data.url = obj.getString("image");
				
				String venueName = venueOffer.getString("name");
				data.venueName = venueName;
				if(mapVenue.get(venueName) != null){
				data.venuePos = mapVenue.get(venueName).pos;
				vecOffer.add(data);
				}
				
			}
			
		
		//now fill eventData
		JSONArray arrEvent = reader.getJSONArray("event");
		len = arrEvent.length();
		for(int i = 0;i<len ;i++){
			
			EventData data = new EventData();
			JSONObject obj = arrEvent.getJSONObject(i);
			data.title = obj.getString("title");
			data.photoString = obj.getString("photo1");
			data.date = obj.getString("date");
			data.time = obj.getString("time");
			data.Desc = obj.getString("desc");
			
			data.ownPosition = i;
			JSONObject venueOffer = obj.getJSONObject("venue");
			//data.url = obj.getString("image");
			
			String venueName = venueOffer.getString("name");
			data.venueName = venueName;
			if(mapVenue.get(venueName) != null){
			data.venuePos = mapVenue.get(venueName).pos;
			vecEvent.add(data);
			}
			
		}
		mapVenue.clear();
		mapVenue = null;
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
}
