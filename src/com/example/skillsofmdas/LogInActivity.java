package com.example.skillsofmdas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONArray;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends Activity{
	private String myJSONString;
	public static final String MY_JSON ="MY_JSON";
	 
    private static final String JSON_URL = "http://10.0.2.2/Projects/skillsofMDAS/login.php";
    
    private static final String JSON_ARRAY ="result";
    private static final String ID = "id";
    private static final String USERNAME= "username";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME= "firstName";
    private static final String LASTNAME = "lastName";
 
    private JSONArray users = null;
 
    private int TRACK = 0;
 
    private EditText editTextId;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private TextView tvLinkSignup;
 
    Button btnLogin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(this.MY_JSON);
        
        final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
    			Context.MODE_PRIVATE);
        SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
 
        //editTextId = (EditText) findViewById(R.id.editTextID);
        editTextUserName = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        
        tvLinkSignup = (TextView)findViewById(R.id.linkSignup);
        tvLinkSignup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mInHome = new Intent(LogInActivity.this, RegisterUserActivity.class);
				LogInActivity.this.startActivity(mInHome);
				LogInActivity.this.finish();
			}
		});
        
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginuser();
				if(skillsOfMDASSharedPreferences.getString("userID", null) != null){
					Toast a = Toast.makeText(LogInActivity.this, "Good luck Mr. " + skillsOfMDASSharedPreferences.getString("lastName", null) + "!", Toast.LENGTH_LONG);
					a.show();
					Intent mInHome = new Intent(LogInActivity.this, optionsActivity.class);
					LogInActivity.this.startActivity(mInHome);
					LogInActivity.this.finish();
				}else{
					Toast a = Toast.makeText(LogInActivity.this, "Invalid username or password!", Toast.LENGTH_LONG);
					a.show();
				}
			}
		});

    }
    private void loginuser() {
    	String url = JSON_URL;
		String username = editTextUserName.getText().toString().trim().toLowerCase();
		String password = editTextPassword.getText().toString().trim().toLowerCase();
		Log.w("loginuserclass", "going to login class with: " + JSON_URL + ", " + username + ", " + password);
		login(url, username, password);
	}
    
    private void login(String url, String username, String password) {

		Log.w("loginclass", "going to getjasonextendsasynctask with: " + url + ", " + username + ", " + password);
        class GetJSON extends AsyncTask<String, Void, String>{
            LogInUserClass luc = new LogInUserClass();
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try{
                    loading = ProgressDialog.show(LogInActivity.this, "Please Wait...",null,true,true);
                }catch(Exception e){
                	Log.w("e", e);
                }
            	Log.w("onpreexc", "adfs");
            }
 
            @Override
            protected String doInBackground(String... params) {
            	Log.w("doinbackground", "processing"+params[0]+params[1]+params[2]);
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HashMap<String, String> data = new HashMap<String,String>();
                	Log.w("Try", "try");
                	Log.w("username: params[0]", params[0]+params[1]+params[2]);
                    data.put("username",params[1]);
                    data.put("password",params[2]);

                    String result = luc.sendPostRequest(JSON_URL,data);
                    String resultx = data.toString();
                    
                    Log.w("resultlogin", resultx);
                	Log.w("luc.sendPostRequest(JSON_URL,data)", result);
                	
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
 
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    
                    String json;

                    while((json = bufferedReader.readLine())!= null){
                            sb.append(json+"\n");

                        	Log.w("while loop", "looping");
                        	if(json != null){
                        		Log.w("null", "not null" + sb.toString().trim());
                        	}else{
                        		Log.w("jason", sb.toString());	
                        	}
                    }
                    String success;
                    final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
                			Context.MODE_PRIVATE);
                    SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
                    if(result != null){
                    	JSONObject jsonObject = new JSONObject(result);
                        users = jsonObject.getJSONArray(JSON_ARRAY);
                        //users.getJSONObject(0).getString(ID)

                        
    					skillsOfMDASEditor.putString("userID", users.getJSONObject(0).getString(ID));
    					skillsOfMDASEditor.putString("firstName", users.getJSONObject(0).getString(FIRSTNAME));
    					skillsOfMDASEditor.putString("lastName", users.getJSONObject(0).getString(LASTNAME));
    					skillsOfMDASEditor.putString("userName", users.getJSONObject(0).getString(USERNAME));
    					skillsOfMDASEditor.commit();
                        
                		Log.w("jason", users.getJSONObject(0).getString(ID));
                    	
                    	
                    	return success = "true";
                    }else{
                    	skillsOfMDASEditor.putString("userID", null);
    					skillsOfMDASEditor.commit();
                    	return success = "false";
                    }
                    
                    
                    
 
                }catch(Exception e){
                	Log.w("e", e);
                    return null;
                }
 
            }
 
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try{
                    loading.dismiss();
                }catch(Exception e){
                	Log.w("e", e);
                }
            	//Log.w("tvJSON", s);
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url,username,password);
    }
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
    			Context.MODE_PRIVATE);
        SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
		skillsOfMDASEditor.putString("userID", null);
		skillsOfMDASEditor.putString("firstName", null);
		skillsOfMDASEditor.putString("lastName", null);
		skillsOfMDASEditor.putString("userName", null);
		skillsOfMDASEditor.commit();
		
		
		Intent mInHome = new Intent(LogInActivity.this, mainActivity.class);
		LogInActivity.this.startActivity(mInHome);
		LogInActivity.this.finish();
	}

}
