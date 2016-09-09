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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registerx extends Activity implements OnClickListener{

	private EditText user, pass, firstnamex, middlenamex, lastnamex, emailaddressx;
	private Button  mRegister;

	 // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //php login script

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/register.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://10.0.2.2/Projects/skillsofmdas/registerx.php";

  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/register.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerx);

		user = (EditText)findViewById(R.id.username);
		pass = (EditText)findViewById(R.id.password);
		firstnamex = (EditText)findViewById(R.id.registerxETFirstName);
		middlenamex = (EditText)findViewById(R.id.registerxETMiddleName);
		lastnamex = (EditText)findViewById(R.id.registerxETLastName);
		emailaddressx = (EditText)findViewById(R.id.registerxETEmailAddress);

		mRegister = (Button)findViewById(R.id.register);
		mRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

				new CreateUser().execute();

	}

	class CreateUser extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Registerx.this);
            pDialog.setMessage("Creating User...");
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
            String firstname = firstnamex.getText().toString();
            String middlename = middlenamex.getText().toString();
            String lastname = lastnamex.getText().toString();
            String emailaddress = emailaddressx.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("firstname", firstname));
                params.add(new BasicNameValuePair("middlename", middlename));
                params.add(new BasicNameValuePair("lastname", lastname));
                params.add(new BasicNameValuePair("emailaddress", emailaddress));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                       LOGIN_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("User Created!", json.toString());
                	finish();
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
            	Toast.makeText(Registerx.this, file_url, Toast.LENGTH_LONG).show();
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
		Intent mInHome = new Intent(Registerx.this, mainActivity.class);
		this.startActivity(mInHome);
		this.finish();
	}
}