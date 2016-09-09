package com.example.skillsofmdas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class teacherActivity extends Activity {

	private TextView scores;
 
    public static final String MY_JSON ="MY_JSON";
 
    private static final String JSON_URL = "http://10.0.2.2/Projects/skillsofmdas/showscoreall.php";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);
 
        scores = (TextView) findViewById(R.id.scores);
        getJSON(JSON_URL);
        //textViewJSON.setMovementMethod(new ScrollingMovementMethod());
    }

    
    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(teacherActivity.this, "Please Wait...",null,true,true);
            }
 
            @Override
            protected String doInBackground(String... params) {
 
                String uri = params[0];
 
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
 
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
 
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                            sb.append(json+"\n");
                            Log.d("jsonn", String.valueOf(sb.append(json+"\n")));
                    }
 
                    String allScore = sb.toString().trim();
                    allScore = allScore.replaceAll(",", "\n");
                    allScore = allScore.replaceAll("\"", "");
                    //return sb.toString().trim();
                    return allScore;
 
                }catch(Exception e){
                    return null;
                }
 
            }
 
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                scores.setText(s);
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }
    

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

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
		skillsOfMDASEditor.putString("score", null);
		Intent mInHome = new Intent(teacherActivity.this, mainActivity.class);
		teacherActivity.this.startActivity(mInHome);
		teacherActivity.this.finish();
	}

}
