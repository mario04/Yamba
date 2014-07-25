package com.example.yamba;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

// Se agrega un comentario
public class StatusActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "StatusActivity";
    private EditText editStatus;
    private Button buttonTweet;
    private TextView textCount;
    private int defaultTextColor;
    Twitter twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        editStatus=(EditText)findViewById(R.id.editStatus);
        buttonTweet=(Button)findViewById(R.id.button_tweet);
        textCount=(TextView)findViewById(R.id.textCount);
        buttonTweet.setOnClickListener(this);
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
            }
        });

        Log.d(TAG, "Create twitter Object");
        twitter = new Twitter("student","password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
        Log.d(TAG, "Set twitter object API root URL");
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
            Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }


}
