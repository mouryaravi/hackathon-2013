package com.ravimd.admon;

import java.net.URISyntaxException;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.net.Uri;
import android.os.Bundle;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EnterIDActivity extends Activity {

	private static final int FILE_SELECT_CODE = 0;
	TextView idTV;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_id);
		
		idTV = (TextView)findViewById(R.id.main_edit_code);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_id, menu);
		return true;
	}
	
	public void onSubmit(View v) {
		
		Log.d(this.getClass().getName(),"On Submit is called ");
		
		/* Lets start the activity  */ 
		String id = idTV.getText().toString(); 
		
		Log.d(this.getClass().getName(), "ID IS "+id);
	
		Intent intent = new Intent(this, DetailsActivity.class);
		intent.putExtra(AppUtil.AD_ID, id);
		startActivity(intent);
	}
	
	public void onScan(View v) {
	  Log.d(this.getClass().getName(), "On scan...");
	  IntentIntegrator integrator = new IntentIntegrator(this);
	  integrator.initiateScan();
	}
	
	public void onUpload(View v) {
		  Log.d(this.getClass().getName(), "On Upload...");
		  Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
		    intent.setType("*/*"); 
		    intent.addCategory(Intent.CATEGORY_OPENABLE);

		    try {
		        startActivityForResult(
		                Intent.createChooser(intent, "Select a File to Upload"),
		                FILE_SELECT_CODE);
		    } catch (android.content.ActivityNotFoundException ex) {
		        // Potentially direct the user to the Market with a Dialog
		        Toast.makeText(this, "Please install a File Manager.", 
		                Toast.LENGTH_SHORT).show();
		    }
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    
    
    if (requestCode == FILE_SELECT_CODE ) {
    	
    if (resultCode == RESULT_OK) {
        // Get the Uri of the selected file 
        Uri uri = intent.getData();
        Log.d(this.getClass().getName(), "File Uri: " + uri.toString());
        // Get the path
        String path = null;
		try {
			path = getPath(this, uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.d(this.getClass().getName(), "File Path: " + path);
        // Get the file instance
        // File file = new File(path);
        // Initiate the upload
     }
    
    }else {
    
    // Now look for scanning result 
    IntentResult scanningResult = IntentIntegrator.parseActivityResult(
            requestCode, resultCode, intent);
    
    if (scanningResult != null) {
    	
      String id = 	scanningResult.getContents();	
//      Toast toast = Toast.makeText(this,
//          "Done: " + id, Toast.LENGTH_LONG);
//      toast.show();
      
        intent = new Intent(this, DetailsActivity.class);
		intent.putExtra(AppUtil.AD_ID, id);
		startActivity(intent);
    }
    else {
      Toast toast = Toast.makeText(this, "Unable to scan for", Toast.LENGTH_LONG);
      toast.show();
    }
    
    }
	}
	
	
	public  String getPath(Context context, Uri uri) throws URISyntaxException {
	    if ("content".equalsIgnoreCase(uri.getScheme())) {
	        String[] projection = { "_data" };
	        Cursor cursor = null;

	        try {
	            cursor = context.getContentResolver().query(uri, projection, null, null, null);
	            int column_index = cursor.getColumnIndexOrThrow("_data");
	            if (cursor.moveToFirst()) {
	                return cursor.getString(column_index);
	            }
	        } catch (Exception e) {
	            // Eat it
	        }
	    }
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	} 
	

}
