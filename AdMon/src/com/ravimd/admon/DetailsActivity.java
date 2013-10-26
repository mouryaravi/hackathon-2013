package com.ravimd.admon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import android.util.Log;

public class DetailsActivity extends Activity {
	
	TextView nameTV;
	TextView priceTV; 
	TextView modelTV;
	ImageView imageView;
	ProductInfo pInfo;
	VideoView videoV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Intent intent = getIntent();
		
		String id = intent.getStringExtra(AppUtil.AD_ID);	
		
		Log.d(this.getClass().getName(), "ID IN DETAILS "+id);
		
		nameTV = (TextView)findViewById(R.id.details_text_name);
		priceTV = (TextView)findViewById(R.id.details_text_price1);
		modelTV= (TextView)findViewById(R.id.details_text_model);
		imageView = (ImageView)findViewById(R.id.details_image_thumb);
		videoV = (VideoView)findViewById(R.id.videoView1);
		
		updateProductDetails(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}
	
	public void onBuy(View view) {
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pInfo.getBuyUrl()));
		startActivity(myIntent);
	}

  private void updateProductDetails(String id) {
    AsyncHttpClient.getDefaultInstance().getJSONObject(
        "http://admon.meteor.com/product/" + id,
        new AsyncHttpClient.JSONObjectCallback() {

          @Override
          public void onCompleted(Exception arg0, AsyncHttpResponse arg1,
              JSONObject prodInfo) {
            Log.d(this.getClass().getName(),
                "----- In final -----" + prodInfo.toString());
            pInfo = null;
            try {
              pInfo = new ProductInfo(prodInfo);
              nameTV.setText("Name :" + pInfo.getCampaignName());
              priceTV.setText("Price :" + pInfo.getPrice());

              ImageLoader imageLoader = new ImageLoader(imageView);
              imageLoader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                  pInfo.getPicURL());

            } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        });
  }
	
	public class ImageLoader extends AsyncTask<String, Integer, Bitmap> {
		 
		  private final WeakReference<ImageView> viewReference;
		 
		  public ImageLoader( ImageView view ) {
		    viewReference = new WeakReference<ImageView>( view );
		  }
		 
		  @Override
		  protected Bitmap doInBackground( String... params ) {
		    return loadBitmap( params[ 0 ] );
		  }
		 
		  @Override
		  protected void onPostExecute( Bitmap bitmap ) {
		    ImageView imageView = viewReference.get();
		    if( imageView != null ) {
		      imageView.setImageBitmap( bitmap );
		    }
		  }
		 
		  public Bitmap loadBitmap(String url) {
			  try 
			  {
				  URLConnection conn = new URL( url ).openConnection();
				  conn.connect();
				  return BitmapFactory.decodeStream( conn.getInputStream() );
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
			  return null;
		  }
		 
		}
	
	

}
