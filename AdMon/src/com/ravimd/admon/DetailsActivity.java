package com.ravimd.admon;

import java.io.FileInputStream;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import android.util.Log;

public class DetailsActivity extends Activity {
	
	TextView nameTV;
	TextView priceTV; 
	TextView modelTV;
	ImageView imageView;
	
	VideoView videoV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Intent intent = getIntent();
		
		String id = intent.getStringExtra(AppUtil.AD_ID);	
		
		Log.d(this.getClass().getName(), "ID IN DETAILS "+id);
		
		ProductInfo pinfo = getInfo1(id);
		
		Log.d(this.getClass().getName(), "Name of the Campaigne is "+pinfo.getId());
		
		nameTV = (TextView)findViewById(R.id.details_text_name);
		priceTV = (TextView)findViewById(R.id.details_text_price);
		modelTV= (TextView)findViewById(R.id.details_text_model);
		imageView = (ImageView)findViewById(R.id.details_image_thumb);
		
		videoV = (VideoView)findViewById(R.id.videoView1);
		

		nameTV.setText("Name :"+pinfo.getCampaignName());
		priceTV.setText("Price :"+pinfo.getPrice());
		modelTV.setText("Model :"+pinfo.getId());
		
		Log.d(this.getClass().getName(),"URL "+ pinfo.getVideoUrl());
		
		 Uri uri=Uri.parse(pinfo.getVideoUrl());
		 
		 videoV.setVideoURI(uri);
		 
		 
		 try     
		    {
			 	URL url = new URL(pinfo.getPicURL());
		        final int THUMBNAIL_SIZE = 64;
		        FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "/Download/"+pinfo.getId()+".JPG");
		        Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
		        imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);
		        imageView.setImageBitmap(imageBitmap);
		    }
		    catch(Exception ex) {
		    	Log.d(this.getClass().getName(), "EXC1"+ex.getMessage());
		    }
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}
	
	
	private ProductInfo getInfo1(String id) {
			return new ProductInfo("GALS4", "Samsung Galaxy S4","32000", "http://www.mysmartprice.com/mobile/samsung-galaxy-s4-msp2439", false, "http://www.youtube.com/watch?v=oUynugn9AYs&feature=player_detailpage", "http://www.samsung.com/global/microsite/galaxys4/mobile/images/img_gallery_w1.jpg") ;
	}

}
