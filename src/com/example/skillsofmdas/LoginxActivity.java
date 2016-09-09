package com.example.skillsofmdas;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginxActivity extends Activity implements OnClickListener{

	public MediaPlayer mp;
	MediaPlayer mpButton;
	
	
	public EditText user, pass;
	private Button mSubmit, mRegister;

	 // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //php login script location:

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://10.0.2.2/Projects/skillsofmdas/login.php";

  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";

    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginx);

		mpButton = MediaPlayer.create(LoginxActivity.this, R.raw.click);
		//setup input fields
		user = (EditText)findViewById(R.id.username);
		pass = (EditText)findViewById(R.id.password);

		//setup buttons
		mSubmit = (Button)findViewById(R.id.login);
		mRegister = (Button)findViewById(R.id.register);

		//register listeners
		mSubmit.setOnClickListener(this);
		mRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
				mpButton.start();
				new AttemptLogin().execute();
			break;
		case R.id.register:
				mpButton.start();
				Intent i = new Intent(this, Registerx.class);
				startActivity(i);
			break;

		default:
			break;
		}
	}

	class AttemptLogin extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginxActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            Log.d("do in background username password", username + password);
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                       LOGIN_URL, "POST", params);
                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Login Successful!", json.toString());

                	Log.d("json detail userID: ", json.getString("userID"));
                	Log.d("json detail username: ", json.getString("username"));
                	Log.d("json detail firstname: ", json.getString("firstname"));
                	Log.d("json detail middlename: ", json.getString("middlename"));
                	Log.d("json detail lastname: ", json.getString("lastname"));
                	Log.d("json detail userlevel: ", json.getString("userlevel"));
                	Log.d("json detail counting1: ", json.getString("counting1"));
                	Log.d("json detail counting2: ", json.getString("counting2"));
                	Log.d("json detail counting3: ", json.getString("counting3"));
                	Log.d("json detail counting4: ", json.getString("counting4"));
                	Log.d("json detail addition1: ", json.getString("addition1"));
                	Log.d("json detail addition2: ", json.getString("addition2"));
                	Log.d("json detail addition3: ", json.getString("addition3"));
                	Log.d("json detail addition4: ", json.getString("addition4"));
                	Log.d("json detail subtraction1: ", json.getString("subtraction1"));
                	Log.d("json detail subtraction2: ", json.getString("subtraction2"));
                	Log.d("json detail subtraction3: ", json.getString("subtraction3"));
                	Log.d("json detail subtraction4: ", json.getString("subtraction4"));
                	Log.d("json detail multiplication1: ", json.getString("multiplication1"));
                	Log.d("json detail multiplication2: ", json.getString("multiplication2"));
                	Log.d("json detail multiplication3: ", json.getString("multiplication3"));
                	Log.d("json detail multiplication4: ", json.getString("multiplication4"));
                	Log.d("json detail division1: ", json.getString("division1"));
                	Log.d("json detail division2: ", json.getString("division2"));
                	Log.d("json detail division3: ", json.getString("division3"));
                	Log.d("json detail division4: ", json.getString("division4"));
                	
                	final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
                			Context.MODE_PRIVATE);
                    SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
                    
                    skillsOfMDASEditor.putString("userID", json.getString("userID"));
					skillsOfMDASEditor.putString("userName", json.getString("username"));
					skillsOfMDASEditor.putString("firstName", json.getString("firstname"));
					skillsOfMDASEditor.putString("middleName", json.getString("middlename"));
					skillsOfMDASEditor.putString("lastName", json.getString("lastname"));
					skillsOfMDASEditor.putString("userLevel", json.getString("userlevel"));
					
					//counting
					if(json.getString("counting1") == null || json.getString("counting1").equals("null")){
                    	Log.d("null test counting1: ", "null");
    					skillsOfMDASEditor.putString("counting1", null);
                	}else{
                    	Log.d("null test counting1: ", "not null");
    					skillsOfMDASEditor.putString("counting1", json.getString("counting1"));
                	}
					
					if(json.getString("counting2") == null || json.getString("counting2").equals("null")){
                    	Log.d("null test counting2: ", "null");
    					skillsOfMDASEditor.putString("counting2", null);
                	}else{
                    	Log.d("null test counting2: ", "not null");
    					skillsOfMDASEditor.putString("counting2", json.getString("counting2"));
                	}
					
					if(json.getString("counting3") == null || json.getString("counting3").equals("null")){
                    	Log.d("null test counting3: ", "null");
    					skillsOfMDASEditor.putString("counting3", null);
                	}else{
                    	Log.d("null test counting3: ", "not null");
    					skillsOfMDASEditor.putString("counting3", json.getString("counting3"));
                	}
					
					if(json.getString("counting4") == null || json.getString("counting4").equals("null")){
                    	Log.d("null test counting4: ", "null");
    					skillsOfMDASEditor.putString("counting4", null);
                	}else{
                    	Log.d("null test counting4: ", "not null");
    					skillsOfMDASEditor.putString("counting4", json.getString("counting4"));
                	}
					
					//addition
					if(json.getString("addition1") == null || json.getString("addition1").equals("null")){
                    	Log.d("null test addition1: ", "null");
    					skillsOfMDASEditor.putString("addition1", null);
                	}else{
                    	Log.d("null test addition1: ", "not null");
    					skillsOfMDASEditor.putString("addition1", json.getString("addition1"));
                	}
					
					if(json.getString("addition2") == null || json.getString("addition2").equals("null")){
                    	Log.d("null test addition2: ", "null");
    					skillsOfMDASEditor.putString("addition2", null);
                	}else{
                    	Log.d("null test addition2: ", "not null");
    					skillsOfMDASEditor.putString("addition2", json.getString("addition2"));
                	}
					
					if(json.getString("addition3") == null || json.getString("addition3").equals("null")){
                    	Log.d("null test addition3: ", "null");
    					skillsOfMDASEditor.putString("addition3", null);
                	}else{
                    	Log.d("null test addition3: ", "not null");
    					skillsOfMDASEditor.putString("addition3", json.getString("addition3"));
                	}
					
					if(json.getString("addition4") == null || json.getString("addition4").equals("null")){
                    	Log.d("null test addition4: ", "null");
    					skillsOfMDASEditor.putString("addition4", null);
                	}else{
                    	Log.d("null test addition4: ", "not null");
    					skillsOfMDASEditor.putString("addition4", json.getString("addition4"));
                	}
					
					//subtraction
					if(json.getString("subtraction1") == null || json.getString("subtraction1").equals("null")){
                    	Log.d("null test subtraction1: ", "null");
    					skillsOfMDASEditor.putString("subtraction1", null);
                	}else{
                    	Log.d("null test subtraction1: ", "not null");
    					skillsOfMDASEditor.putString("subtraction1", json.getString("subtraction1"));
                	}
					
					if(json.getString("subtraction2") == null || json.getString("subtraction2").equals("null")){
                    	Log.d("null test subtraction2: ", "null");
    					skillsOfMDASEditor.putString("subtraction2", null);
                	}else{
                    	Log.d("null test subtraction2: ", "not null");
    					skillsOfMDASEditor.putString("subtraction2", json.getString("subtraction2"));
                	}
					
					if(json.getString("subtraction3") == null || json.getString("subtraction3").equals("null")){
                    	Log.d("null test subtraction3: ", "null");
    					skillsOfMDASEditor.putString("subtraction3", null);
                	}else{
                    	Log.d("null test subtraction3: ", "not null");
    					skillsOfMDASEditor.putString("subtraction3", json.getString("subtraction3"));
                	}
					
					if(json.getString("subtraction4") == null || json.getString("subtraction4").equals("null")){
                    	Log.d("null test subtraction4: ", "null");
    					skillsOfMDASEditor.putString("subtraction4", null);
                	}else{
                    	Log.d("null test subtraction4: ", "not null");
    					skillsOfMDASEditor.putString("subtraction4", json.getString("subtraction4"));
                	}
					
					//multiplication
					if(json.getString("multiplication1") == null || json.getString("multiplication1").equals("null")){
                    	Log.d("null test multiplication1: ", "null");
    					skillsOfMDASEditor.putString("multiplication1", null);
                	}else{
                    	Log.d("null test multiplication1: ", "not null");
    					skillsOfMDASEditor.putString("multiplication1", json.getString("multiplication1"));
                	}
					
					if(json.getString("multiplication2") == null || json.getString("multiplication2").equals("null")){
                    	Log.d("null test multiplication2: ", "null");
    					skillsOfMDASEditor.putString("multiplication2", null);
                	}else{
                    	Log.d("null test multiplication2: ", "not null");
    					skillsOfMDASEditor.putString("multiplication2", json.getString("multiplication2"));
                	}
					
					if(json.getString("multiplication3") == null || json.getString("multiplication3").equals("null")){
                    	Log.d("null test multiplication3: ", "null");
    					skillsOfMDASEditor.putString("multiplication3", null);
                	}else{
                    	Log.d("null test multiplication3: ", "not null");
    					skillsOfMDASEditor.putString("multiplication3", json.getString("multiplication3"));
                	}
					
					if(json.getString("multiplication4") == null || json.getString("multiplication4").equals("null")){
                    	Log.d("null test multiplication4: ", "null");
    					skillsOfMDASEditor.putString("multiplication4", null);
                	}else{
                    	Log.d("null test multiplication4: ", "not null");
    					skillsOfMDASEditor.putString("multiplication4", json.getString("multiplication4"));
                	}
					
					//division
					if(json.getString("division1") == null || json.getString("division1").equals("null")){
                    	Log.d("null test division1: ", "null");
    					skillsOfMDASEditor.putString("division1", null);
                	}else{
                    	Log.d("null test division1: ", "not null");
    					skillsOfMDASEditor.putString("division1", json.getString("division1"));
                	}
					
					if(json.getString("division2") == null || json.getString("division2").equals("null")){
                    	Log.d("null test division2: ", "null");
    					skillsOfMDASEditor.putString("division2", null);
                	}else{
                    	Log.d("null test division2: ", "not null");
    					skillsOfMDASEditor.putString("division2", json.getString("division2"));
                	}
					
					if(json.getString("division3") == null || json.getString("division3").equals("null")){
                    	Log.d("null test division3: ", "null");
    					skillsOfMDASEditor.putString("division3", null);
                	}else{
                    	Log.d("null test division3: ", "not null");
    					skillsOfMDASEditor.putString("division3", json.getString("division3"));
                	}
					
					if(json.getString("division4") == null || json.getString("division4").equals("null")){
                    	Log.d("null test division4: ", "null");
    					skillsOfMDASEditor.putString("division4", null);
                	}else{
                    	Log.d("null test division4: ", "not null");
    					skillsOfMDASEditor.putString("division4", json.getString("division4"));
                	}
					
					skillsOfMDASEditor.commit();
					if(skillsOfMDASSharedPreferences.getString("addition1", null) == null){
                    	Log.d("null test addition == null: ", "null");
					}else{
                    	Log.d("null test addition: == null", "!null");
					}

                	Log.d("loginsuccess", "lets go!");
                	Log.d("mainactivityselectedbuttonlogin", skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0") + ":" + username + ":" + password);
                	if(skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0").equals("Tests")){
						Intent i = new Intent(LoginxActivity.this, optionsActivity.class);
	                	finish();
	    				startActivity(i);
					}else if(skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0").equals("Score")){
						Intent i = new Intent(LoginxActivity.this, ScoreActivity.class);
	                	finish();
	    				startActivity(i);
					}else if(skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0").equals("Teacher") && user.getText().toString().equals("teacher") && pass.getText().toString().equals("teacher")){
						Intent i = new Intent(LoginxActivity.this, teacherActivity.class);
	                	finish();
	    				startActivity(i);
					}else{
						Intent i = new Intent(LoginxActivity.this, mainActivity.class);
	                	finish();
	    				startActivity(i);
					}
					
                	
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

		}
		/**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(LoginxActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

	}
	@Override
	public void onBackPressed() {
		// your code.
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
    			Context.MODE_PRIVATE);
        SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
        skillsOfMDASEditor.putString("userID", null);
		skillsOfMDASEditor.putString("userName", null);
		skillsOfMDASEditor.putString("firstName", null);
		skillsOfMDASEditor.putString("middleName", null);
		skillsOfMDASEditor.putString("lastName", null);
		skillsOfMDASEditor.putString("userLevel", null);
		skillsOfMDASEditor.putString("counting1", null);
		skillsOfMDASEditor.putString("counting2", null);
		skillsOfMDASEditor.putString("counting3", null);
		skillsOfMDASEditor.putString("counting4", null);
		skillsOfMDASEditor.putString("addition1", null);
		skillsOfMDASEditor.putString("addition2", null);
		skillsOfMDASEditor.putString("addition3", null);
		skillsOfMDASEditor.putString("addition4", null);
		skillsOfMDASEditor.putString("subtraction1", null);
		skillsOfMDASEditor.putString("subtraction2", null);
		skillsOfMDASEditor.putString("subtraction3", null);
		skillsOfMDASEditor.putString("subtraction4", null);
		skillsOfMDASEditor.putString("multiplication1", null);
		skillsOfMDASEditor.putString("multiplication2", null);
		skillsOfMDASEditor.putString("multiplication3", null);
		skillsOfMDASEditor.putString("multiplication4", null);
		skillsOfMDASEditor.putString("division1", null);
		skillsOfMDASEditor.putString("division2", null);
		skillsOfMDASEditor.putString("division3", null);
		skillsOfMDASEditor.putString("division4", null);
		skillsOfMDASEditor.commit();
		Intent mInHome = new Intent(LoginxActivity.this, mainActivity.class);
		this.startActivity(mInHome);
		this.finish();
	}
}