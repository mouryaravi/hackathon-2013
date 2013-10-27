package com.ravimd.admon;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

import com.ravimd.admon.DetailsActivity.ImageLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.VideoView;

public class PicsActivity extends Activity {
	
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pics);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra(AppUtil.IMAGE_URL);	
		imageView = (ImageView)findViewById(R.id.pics_image_fullimage);
		ImageLoader imageLoader = new ImageLoader(imageView);
        imageLoader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
        		url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pics, menu);
		return true;
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
