package com.example.yamba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StatusActivity extends Activity {
	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Check if this activity was created before
		if (savedInstanceState == null) { //
		// Create a fragment
		statusFragment fragment = new statusFragment(); //
		getFragmentManager().beginTransaction().add(android.R.id.content, fragment,fragment.getClass().getSimpleName()).commit(); //
		}
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.menu, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
				case R.id.itemServiceStart:
						startService(new Intent(this, RefreshService.class)); //
						break;
				case R.id.itemPrefs:
						startActivity(new Intent(this, SettingsActivity.class));
						break;
				default:
						return false;
			}
			return true;
		}
		
		
		
}
