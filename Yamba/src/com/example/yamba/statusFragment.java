package com.example.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class statusFragment extends Fragment implements View.OnClickListener{

	private static final String TAG = "StatusFragment";
    private EditText editStatus;
    private Button buttonTweet;
    private TextView textCount;
    private int defaultTextColor;
    Twitter twitter;	
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view =inflater.inflate(R.layout.fragment_status, container, false);
        
        editStatus=(EditText)view.findViewById(R.id.editStatus);
        buttonTweet=(Button)view.findViewById(R.id.button_tweet);
        textCount=(TextView)view.findViewById(R.id.textCount);
        buttonTweet.setOnClickListener(this);
        textCount.setTextColor(Color.GREEN);
        defaultTextColor=textCount.getTextColors().getDefaultColor();
        editStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = 140-editStatus.length();
                textCount.setText(Integer.toString(count));
                textCount.setTextColor(Color.GREEN);
                if(count<10)
                    textCount.setTextColor(Color.RED);
                else
                	textCount.setTextColor(defaultTextColor);
            }
        });

        
		return view;
    }

    @Override
    public void onClick(View v) {
        String status = editStatus.getText().toString();
        new PostToTwitter().execute(status);
        Log.d(TAG, "Tweet Enviado");
    }

    private final class PostToTwitter extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... statuses) {
        	try{
            	SharedPreferences prefs = PreferenceManager
            			.getDefaultSharedPreferences(getActivity()); //
            			String username = prefs.getString("username", ""); //
            			String password = prefs.getString("password", "");
            			if (TextUtils.isEmpty(username) ||
            			TextUtils.isEmpty(password)) { //
            			getActivity().startActivity(
            			new Intent(getActivity(), SettingsActivity.class));
            			return "Please update your username and password";
            			}
            			//YambaClient yambaCloud=new YambaClient(username, password);
            			twitter = new Twitter("student","password");
            			twitter.setAPIRootUrl("http://yamba.marakana.com/api");
            			Twitter.Status status = twitter.updateStatus(statuses[0]);
            			return status.text;
            }catch (TwitterException e){
                Log.e(TAG, e.toString());
                e.printStackTrace();
                return "Failed to post";
            }
        }

        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(String result){
        	super.onPostExecute(result);
            Toast.makeText(statusFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
        }
    }


}



