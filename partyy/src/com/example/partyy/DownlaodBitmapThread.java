package com.example.partyy;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class DownlaodBitmapThread extends Thread{
    
    public boolean stopDownloading =  false;
    public boolean AllEventsDownloaded = false;
    public boolean AllVenuesDownloaded = false;
    public boolean AllOffersDownloaded = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(stopDownloading == false){
			int len =0;
			if(StateMachine.getInstance().currentFragment == 0){// || (AllOffersDownloaded && AllEventsDownloaded)){
			if(DataArray.getInstance().vecVenueData != null)
			   len = DataArray.getInstance().vecVenueData.size();
			
			    boolean bitmapRemaining = false;
			    for(int i = 0;i<len;i++){
					VenueData s = DataArray.getInstance().vecVenueData.elementAt(i);
					if(s!= null && s.sbtmmap == null && s.isBitmapRequested == false){
						InputStream in;
						Bitmap mIcon11 = null;
						String path = "/sdcard/Mozito/Venues/" + s.ID +".jpg";
						File imageFile = new File(path);
						boolean correctlyloaded = false;
						if(imageFile.exists()){
							Bitmap bitmap = BitmapFactory.decodeFile(path);
							if(bitmap != null){
							//DataArray.getInstance().vecVenueData.elementAt(s.pos).btmmap = bitmap;
							int width = bitmap.getWidth();
		  	                 //Log.i("Old width................", width + "");
		  	                int   height = bitmap.getHeight();
		  	                 //Log.i("Old height................", height + "");
		  	 
			  	            Matrix matrix = new Matrix();
			  	            float scaleWidth = ((float) 200) / width;
			  	            float scaleHeight = ((float) 200) / height;
			  	            matrix.postScale(scaleWidth, scaleHeight);
			  	    		s.sbtmmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);
			  	    	
							correctlyloaded = true;
							}
						}
						if(correctlyloaded == false){
							//Download the image
							try {
						         in = new java.net.URL(s.url).openStream();
						        mIcon11 = BitmapFactory.decodeStream(in);
						        //DataArray.getInstance().vecVenueData.elementAt(i).btmmap = mIcon11;
								//Now saving the image
						        int width = mIcon11.getWidth();
			  	                 //Log.i("Old width................", width + "");
			  	                int   height = mIcon11.getHeight();
			  	                 //Log.i("Old height................", height + "");
			  	 
				  	            Matrix matrix = new Matrix();
				  	            float scaleWidth = ((float) 200) / width;
				  	            float scaleHeight = ((float) 200) / height;
				  	            matrix.postScale(scaleWidth, scaleHeight);
				  	    		s.sbtmmap = Bitmap.createBitmap(mIcon11, 0, 0,width, height, matrix, true);
				  	    	
						        String fileName = "/sdcard/Mozito/Venues/";
						        
						        File fileDirs = new File(fileName);
						        File file =null;
						        try {
						        	fileDirs.mkdirs();
						        	fileName += s.ID +".jpg";
						        	file = new File(fileName);
						            file.createNewFile();
						        } catch (IOException e) {
						            e.printStackTrace();
						        }
						        FileOutputStream fileOutputStream = new FileOutputStream(file);

						        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

						        mIcon11.compress(CompressFormat.JPEG, 100, bos);

						        bos.flush();

						        bos.close();
						        bitmapRemaining = true;
						    } catch (Exception e) {
						        
						        e.printStackTrace();
						    }
						}
						//if(DataArray.getInstance().vecVenueData.elementAt(s.pos).url == in.)
						
						//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
						if(SplashScreenApp.getInstance() != null){
							SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
							    public void run() {
							    	/*if(eventarrayadapter.getInstance() != null){
							            eventarrayadapter.getInstance().notifyDataSetChanged();
							    	}
							    	if(offerArrayAdapter.getInstance() != null){
							           offerArrayAdapter.getInstance().notifyDataSetChanged();
							    	}*/
							        if(venueArayAdapater.getInstance() != null){
							          venueArayAdapater.getInstance().notifyDataSetChanged();
							        }
							    }
						});
							
			        }
						bitmapRemaining = true;
		    	        break;
				}
			}
			    if(bitmapRemaining == false){
			    	AllVenuesDownloaded = true;
			    }
		    }
		    else if(StateMachine.getInstance().currentFragment == 1 ){//|| (AllOffersDownloaded && AllVenuesDownloaded)){
	    	if(DataArray.getInstance().vecEventData != null)
				   len = DataArray.getInstance().vecEventData.size();
		    boolean bitmapRemaining = false;
		    for(int i = 0;i<len;i++){
				EventData s = DataArray.getInstance().vecEventData.elementAt(i);
				if(s!= null && s.smallBitmap == null && s.isBitmapRequested == false){
					InputStream in;
					Bitmap mIcon11 = null;
					String path = "/sdcard/Mozito/Events/" + s.id +".jpg";
					File imageFile = new File(path);
					boolean correctlyloaded = false;
					if(imageFile.exists()){
						Bitmap bitmap = BitmapFactory.decodeFile(path);
						if(bitmap !=  null){
							int width = bitmap.getWidth();
		  	                  //Log.i("Old width................", width + "");
		  	                int   height = bitmap.getHeight();
		  	                 //Log.i("Old height................", height + "");
		  	 
			  	            Matrix matrix = new Matrix();
			  	            float scaleWidth = ((float) 200) / width;
			  	            float scaleHeight = ((float) 200) / height;
			  	            matrix.postScale(scaleWidth, scaleHeight);
			  	    		s.smallBitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);
			  	    	    correctlyloaded = true;
						}
					}
					if(correctlyloaded == false){
						//Download the image
						try {
					         in = new java.net.URL(s.photoString).openStream();
					        mIcon11 = BitmapFactory.decodeStream(in);
					        int width = mIcon11.getWidth();
		  	                 //Log.i("Old width................", width + "");
		  	                int   height = mIcon11.getHeight();
		  	                 //Log.i("Old height................", height + "");
		  	 
			  	            Matrix matrix = new Matrix();
			  	            float scaleWidth = ((float) 200) / width;
			  	            float scaleHeight = ((float) 200) / height;
			  	            matrix.postScale(scaleWidth, scaleHeight);
			  	    		s.smallBitmap = Bitmap.createBitmap(mIcon11, 0, 0,width, height, matrix, true);
			  	    	
					        //Now saving the image
					        String fileName = "/sdcard/Mozito/Events/";
					        
					        File fileDirs = new File(fileName);
					        File file = null;
					        try {
					        	fileDirs.mkdirs();
					        	fileName += s.id+".jpg";
					        	file = new File(fileName);
					            file.createNewFile();
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					        FileOutputStream fileOutputStream = new FileOutputStream(file);

					        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

					        mIcon11.compress(CompressFormat.JPEG, 100, bos);

					        bos.flush();

					        bos.close();
					        bitmapRemaining = true;
					    } catch (Exception e) {
					        
					        e.printStackTrace();
					    }
					}
					//if(DataArray.getInstance().vecVenueData.elementAt(s.pos).url == in.)
					
					//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
					if(SplashScreenApp.getInstance() != null){
						SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
						    public void run() {
						    	if(eventarrayadapter.getInstance() != null){
						            eventarrayadapter.getInstance().notifyDataSetChanged();
						    	}
						    	/*if(offerArrayAdapter.getInstance() != null){
						           offerArrayAdapter.getInstance().notifyDataSetChanged();
						    	}
						        if(venueArayAdapater.getInstance() != null){
						          venueArayAdapater.getInstance().notifyDataSetChanged();
						        }*/
						    }
					});
						
		        }
					bitmapRemaining = true;
	    	        break;
			}
		}
		    if(bitmapRemaining == false){
		    	AllEventsDownloaded = true;
		    }
	    }
	    else if(StateMachine.getInstance().currentFragment == 2 ){//|| (AllEventsDownloaded && AllVenuesDownloaded)){
	    	if(DataArray.getInstance().vecOfferData != null)
				   len = DataArray.getInstance().vecOfferData.size();
		    boolean bitmapRemaining = false;
		    for(int i = 0;i<len;i++){
				OfferData s = DataArray.getInstance().vecOfferData.elementAt(i);
				if(s!= null && s.smallBitmap == null && s.isBitmapRequested == false){
					InputStream in;
					Bitmap mIcon11 = null;
					String path = "/sdcard/Mozito/Offers/" + s.id +".jpg";
					File imageFile = new File(path);
					boolean correctlyloaded= false;
					if(imageFile.exists()){
						Bitmap bitmap = BitmapFactory.decodeFile(path);
						if(bitmap !=  null){
							int width = bitmap.getWidth();
		  	                 //Log.i("Old width................", width + "");
		  	                int   height = bitmap.getHeight();
		  	                 //Log.i("Old height................", height + "");
		  	 
			  	            Matrix matrix = new Matrix();
			  	            float scaleWidth = ((float) 200) / width;
			  	            float scaleHeight = ((float) 200) / height;
			  	            matrix.postScale(scaleWidth, scaleHeight);
			  	    		s.smallBitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);
			  	    	    correctlyloaded = true;
						}
					}
					if(correctlyloaded == false){
						//Download the image
						try {
					         in = new java.net.URL(s.photoString).openStream();
					        mIcon11 = BitmapFactory.decodeStream(in);
					        if(mIcon11 != null){
					        	int width = mIcon11.getWidth();
			  	                 //Log.i("Old width................", width + "");
			  	                int   height = mIcon11.getHeight();
			  	                 //Log.i("Old height................", height + "");
			  	 
				  	            Matrix matrix = new Matrix();
				  	            float scaleWidth = ((float) 200) / width;
				  	            float scaleHeight = ((float) 200) / height;
				  	            matrix.postScale(scaleWidth, scaleHeight);
				  	    		s.smallBitmap = Bitmap.createBitmap(mIcon11, 0, 0,width, height, matrix, true);
				  	    	
						        //Now saving the image
						      
						        String fileName = "/sdcard/Mozito/Offers/";
						        
						        File fileDIrs = new File(fileName);
						        File file = null;
						        try {
						        	fileDIrs.mkdirs();
						        	fileName +=  s.id +".jpg";
						        	file = new File(fileName);
						            file.createNewFile();
						        } catch (IOException e) {
						            e.printStackTrace();
						        }
						        FileOutputStream fileOutputStream = new FileOutputStream(file);
	
						        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
	
						        mIcon11.compress(CompressFormat.JPEG, 100, bos);
	
						        bos.flush();
	
						        bos.close();
					        }
					        bitmapRemaining = true;
					    } catch (Exception e) {
					        
					        e.printStackTrace();
					    }
					}
					//if(DataArray.getInstance().vecVenueData.elementAt(s.pos).url == in.)
					
					//final ArrayAdapter adapter = ((ArrayAdapter)getListAdapter());
					if(SplashScreenApp.getInstance() != null){
						SplashScreenApp.getInstance().runOnUiThread(new Runnable() {
						    public void run() {
						    	/*if(eventarrayadapter.getInstance() != null){
						            eventarrayadapter.getInstance().notifyDataSetChanged();
						    	}*/
						    	if(offerArrayAdapter.getInstance() != null){
						           offerArrayAdapter.getInstance().notifyDataSetChanged();
						    	}
						        /*if(venueArayAdapater.getInstance() != null){
						          venueArayAdapater.getInstance().notifyDataSetChanged();
						        }*/
						    }
					});
						
		        }
					bitmapRemaining = true;
	    	        break;
			}
		}
		    if(bitmapRemaining == false){
		    	AllOffersDownloaded = true;
		    }
	    }
		if(AllEventsDownloaded == true && AllOffersDownloaded == true && AllVenuesDownloaded == true){
			//stopDownloading = true;
		}
	}

}
}
