package com.example.yamba;
import android.os.Bundle;
import android.app.Activity;

public class SettingsActivity extends Activity{
	@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState == null){
			SettingsFragment fragment = new SettingsFragment();
			getFragmentManager()
							.beginTransaction()
							.add(android.R.id.content, fragment, fragment.getClass().getSimpleName()).commit();
		}
	};

}
