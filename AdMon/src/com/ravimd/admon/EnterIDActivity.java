package com.ravimd.admon;



import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
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

	TextView idTV;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_id);
		
		idTV = (TextView)findViewById(R.id.main_edit_maintext);
		
		
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
	
	

}
