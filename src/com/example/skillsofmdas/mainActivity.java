package com.example.skillsofmdas;

import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class mainActivity extends Activity {
	Button mainActivity_btnLessons, mainActivity_btnTests, mainActivity_btnScore, mainActivity_btnAbout,
			mainActivity_btnExit,mainActivity_btnTeacher;

	public MediaPlayer mp;
	MediaPlayer mpButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		mpButton = MediaPlayer.create(mainActivity.this, R.raw.click);
		mp = MediaPlayer.create(mainActivity.this, R.raw.positivefeeling);
		mp.start();
		mp.setLooping(true);
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);
		String mainActivitySelectedButton = skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0");

		mainActivity_btnLessons = (Button) findViewById(R.id.mainActivity_btnLessons);
		mainActivity_btnLessons.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mpButton.start();
				SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
				skillsOfMDASEditor.putString("mainActivitySelectedButton", "Lessons");
				skillsOfMDASEditor.commit();
				Log.w("mainActivityButtonClick", "btnLessons. mainActivitySelectedbutton: "
						+ skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0"));

				if (skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0") == "Lessons") {
					Log.w("tester: ", "success");
				} else {

					Log.w("tester: ", "fail");
				}
				Intent mInHome = new Intent(mainActivity.this, optionsActivity.class);
				mainActivity.this.startActivity(mInHome);
				mainActivity.this.finish();
			}
		});

		mainActivity_btnTests = (Button) findViewById(R.id.mainActivity_btnTests);
		mainActivity_btnTests.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// optionsGlobalVariables optionsGlobalVariablesValue =
				// (optionsGlobalVariables)getApplication();
				// optionsGlobalVariablesValue.setSomeVariable("optionTests");

				mpButton.start();
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo netInfo = cm.getActiveNetworkInfo();
				Log.w("Connectivity", netInfo.toString());
				Log.w("Connectivity", netInfo.getState().toString());

				SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
				skillsOfMDASEditor.putString("mainActivitySelectedButton", "Tests");
				skillsOfMDASEditor.commit();
				Log.w("mainActivityButtonClick", "btnTests. mainActivitySelectedbutton: "
						+ skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0"));

				if (netInfo.getState().toString().equals("CONNECTED")) {
					Intent mInHome = new Intent(mainActivity.this, LoginxActivity.class);
					mainActivity.this.startActivity(mInHome);
					mainActivity.this.finish();
				} else {
					Toast a = Toast.makeText(mainActivity.this, "Connection Error: No connectivity to the server.",
							Toast.LENGTH_LONG);
				}

				/*
				 * Intent mInHome = new Intent(mainActivity.this,
				 * optionsActivity.class);
				 * mainActivity.this.startActivity(mInHome);
				 * mainActivity.this.finish();
				 */
			}
		});

		mainActivity_btnTeacher = (Button) findViewById(R.id.mainActivity_btnTeacher);
		mainActivity_btnTeacher.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// optionsGlobalVariables optionsGlobalVariablesValue =
				// (optionsGlobalVariables)getApplication();
				// optionsGlobalVariablesValue.setSomeVariable("optionTests");

				mpButton.start();
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo netInfo = cm.getActiveNetworkInfo();
				Log.w("Connectivity", netInfo.toString());
				Log.w("Connectivity", netInfo.getState().toString());

				SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
				skillsOfMDASEditor.putString("mainActivitySelectedButton", "Teacher");
				skillsOfMDASEditor.commit();
				Log.w("mainActivityButtonClick", "btnTests. mainActivitySelectedbutton: "
						+ skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0"));

				if (netInfo.getState().toString().equals("CONNECTED")) {
					//Intent mInHome = new Intent(mainActivity.this, LoginxActivity.class);
					Intent mInHome = new Intent(mainActivity.this, LoginxActivity.class);
					mainActivity.this.startActivity(mInHome);
					mainActivity.this.finish();
				} else {
					Toast a = Toast.makeText(mainActivity.this, "Connection Error: No connectivity to the server.",
							Toast.LENGTH_LONG);
				}

				/*
				 * Intent mInHome = new Intent(mainActivity.this,
				 * optionsActivity.class);
				 * mainActivity.this.startActivity(mInHome);
				 * mainActivity.this.finish();
				 */
			}
		});
		
		mainActivity_btnScore = (Button) findViewById(R.id.mainActivity_btnScore);
		mainActivity_btnScore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mpButton.start();
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo netInfo = cm.getActiveNetworkInfo();
				Log.w("Connectivity", netInfo.toString());
				Log.w("Connectivity", netInfo.getState().toString());

				SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
				skillsOfMDASEditor.putString("mainActivitySelectedButton", "Score");
				skillsOfMDASEditor.commit();
				Log.w("mainActivityButtonClick", "btnScore. mainActivitySelectedbutton: "
						+ skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0"));

				if (netInfo.getState().toString().equals("CONNECTED")) {
					Intent mInHome = new Intent(mainActivity.this, LoginxActivity.class);
					mainActivity.this.startActivity(mInHome);
					mainActivity.this.finish();
				} else {
					Toast a = Toast.makeText(mainActivity.this, "Connection Error: No connectivity to the server.",
							Toast.LENGTH_LONG);
				}
			}
		});
		
		mainActivity_btnAbout = (Button) findViewById(R.id.mainActivity_btnAbout);
		mainActivity_btnAbout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mpButton.start();
				Log.w("mainActivityButtonClick", "btnAbout");
				Intent mInHome = new Intent(mainActivity.this, AboutActivity.class);
				mainActivity.this.startActivity(mInHome);
				mainActivity.this.finish();
			}
		});

		mainActivity_btnExit = (Button) findViewById(R.id.mainActivity_btnExit);
		mainActivity_btnExit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mpButton.start();
				Log.w("mainActivityButtonClick", "btnExit");
				mainActivity.this.finish();
			}
		});
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(mp!=null){
			mp.stop();
		}
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		super.onDestroy();
		
		if(mp!=null){
			mp.pause();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mp!=null){
			if(mp.isPlaying()==false){
				mp.start();
			}
		}
	}
	public boolean isConnectedToServer(String url, int timeout) {
		try {
			URL myUrl = new URL(url);
			URLConnection connection = myUrl.openConnection();
			connection.setConnectTimeout(timeout);
			connection.connect();
			return true;
		} catch (Exception e) {
			// Handle your exceptions
			return false;
		}
	}
}
