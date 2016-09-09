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
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends Activity {

	TextView scores,tvCounting1,tvCounting2,tvCounting3,tvCounting4,tvAddition1, tvAddition2, tvAddition3, tvAddition4, tvSubtraction1, tvSubtraction2, tvSubtraction3,
			tvSubtraction4, tvMultiplication1, tvMultiplication2, tvMultiplication3, tvMultiplication4, tvDivision1,
			tvDivision2, tvDivision3, tvDivision4;
	
	public MediaPlayer mp;
	MediaPlayer mpButton;
	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// php login script

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL =
	// "http://xxx.xxx.x.x:1234/webservice/register.php";

	// testing on Emulator:
	private static final String LOGIN_URL = "http://10.0.2.2/Projects/skillsofmdas/showscore.php";

	// testing from a real server:
	// private static final String LOGIN_URL =
	// "http://www.yourdomain.com/webservice/register.php";

	// ids
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);

		mpButton = MediaPlayer.create(ScoreActivity.this, R.raw.click);
		scores = (TextView) findViewById(R.id.scores);
		/*
		tvAddition1 = (TextView) findViewById(R.id.tvAddition1);
		tvAddition2 = (TextView) findViewById(R.id.tvAddition2);
		tvAddition3 = (TextView) findViewById(R.id.tvAddition3);
		tvAddition4 = (TextView) findViewById(R.id.tvAddition4);
		tvSubtraction1 = (TextView) findViewById(R.id.tvSubtraction1);
		tvSubtraction2 = (TextView) findViewById(R.id.tvSubtraction2);
		tvSubtraction3 = (TextView) findViewById(R.id.tvSubtraction3);
		tvSubtraction4 = (TextView) findViewById(R.id.tvSubtraction4);
		tvMultiplication1 = (TextView) findViewById(R.id.tvMultiplication1);
		tvMultiplication2 = (TextView) findViewById(R.id.tvMultiplication2);
		tvMultiplication3 = (TextView) findViewById(R.id.tvMultiplication3);
		tvMultiplication4 = (TextView) findViewById(R.id.tvMultiplication4);
		tvDivision1 = (TextView) findViewById(R.id.tvDivision1);
		tvDivision2 = (TextView) findViewById(R.id.tvDivision2);
		tvDivision3 = (TextView) findViewById(R.id.tvDivision3);
		tvDivision4 = (TextView) findViewById(R.id.tvDivision4);
		*/
		
		
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);
		skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0");
		/*tvAddition1.setText("Addition 1: " + skillsOfMDASSharedPreferences.getString("addition1", null));
		tvAddition2.setText("Addition 2: " + skillsOfMDASSharedPreferences.getString("addition2", null));
		tvAddition3.setText("Addition 3: " + skillsOfMDASSharedPreferences.getString("addition3", null));
		tvAddition4.setText("Addition 4: " + skillsOfMDASSharedPreferences.getString("addition4", null));
		tvSubtraction1.setText("Subtraction 1: " + skillsOfMDASSharedPreferences.getString("subtraction1", null));
		tvSubtraction2.setText("Subtraction 2: " + skillsOfMDASSharedPreferences.getString("subtraction2", null));
		tvSubtraction3.setText("Subtraction 3: " + skillsOfMDASSharedPreferences.getString("subtraction3", null));
		tvSubtraction4.setText("Subtraction 4: " + skillsOfMDASSharedPreferences.getString("subtraction4", null));
		tvMultiplication1
				.setText("Multiplication 1: " + skillsOfMDASSharedPreferences.getString("multiplication1", null));
		tvMultiplication2
				.setText("Multiplication 2: " + skillsOfMDASSharedPreferences.getString("multiplication2", null));
		tvMultiplication3
				.setText("Multiplication 3: " + skillsOfMDASSharedPreferences.getString("multiplication3", null));
		tvMultiplication4
				.setText("Multiplication 4: " + skillsOfMDASSharedPreferences.getString("multiplication4", null));
		tvDivision1.setText("Division 1: " + skillsOfMDASSharedPreferences.getString("division1", null));
		tvDivision2.setText("Division 2: " + skillsOfMDASSharedPreferences.getString("division2", null));
		tvDivision3.setText("Division 3: " + skillsOfMDASSharedPreferences.getString("division3", null));
		tvDivision4.setText("Division 4: " + skillsOfMDASSharedPreferences.getString("division4", null));*/
		scores.setText("Counting\n" + 
		skillsOfMDASSharedPreferences.getString("counting1", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("counting2", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("counting3", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("counting4", null) + "\n" +
		"Addition" + "\n" +
		skillsOfMDASSharedPreferences.getString("addition1", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("addition2", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("addition3", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("addition4", null) + "\n" +
		"Subtraction" + "\n" +
		skillsOfMDASSharedPreferences.getString("subtraction1", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("subtraction2", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("subtraction3", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("subtraction4", null) + "\n" + 
		"Multiplication" + "\n" +
		skillsOfMDASSharedPreferences.getString("multiplication1", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("multiplication2", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("multiplication3", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("multiplication4", null) + "\n" + 
		"Division" + "\n" +
		skillsOfMDASSharedPreferences.getString("division1", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("division2", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("division3", null) + "\n" +
		skillsOfMDASSharedPreferences.getString("division4", null) + "\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		mpButton.start();
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
		Intent mInHome = new Intent(ScoreActivity.this, mainActivity.class);
		ScoreActivity.this.startActivity(mInHome);
		ScoreActivity.this.finish();
	}

}
