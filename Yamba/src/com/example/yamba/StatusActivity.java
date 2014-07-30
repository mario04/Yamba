package com.example.yamba;

import android.app.Activity;
import android.os.Bundle;



// Se agrega un comentario
public class StatusActivity extends Activity {
	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Check if this activity was created before
		if (savedInstanceState == null) { //
		// Create a fragment
		statusFragment fragment = new statusFragment(); //
		getFragmentManager()
		.beginTransaction()
		.add(android.R.id.content, fragment,
		fragment.getClass().getSimpleName())
		.commit(); //
		}
		}	
		
}
