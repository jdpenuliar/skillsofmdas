package com.example.skillsofmdas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class optionsActivity extends Activity {

	Button optionsActivity_btnZero, optionsActivity_btnOne, optionsActivity_btnTwo, optionsActivity_btnThree, optionsActivity_btnFour;
	public MediaPlayer mp;
	MediaPlayer mpButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);


		mpButton = MediaPlayer.create(optionsActivity.this, R.raw.click);
		
		optionsActivity_btnZero = (Button) findViewById(R.id.optionsActivity_btnZero);
		optionsActivity_btnOne = (Button) findViewById(R.id.optionsActivity_btnOne);
		optionsActivity_btnTwo = (Button) findViewById(R.id.optionsActivity_btnTwo);
		optionsActivity_btnThree = (Button) findViewById(R.id.optionsActivity_btnThree);
		optionsActivity_btnFour = (Button) findViewById(R.id.optionsActivity_btnFour);

		optionsActivity_btnZero.setText("Counting");
		optionsActivity_btnOne.setText("Addition");
		optionsActivity_btnTwo.setText("Subtraction");
		optionsActivity_btnThree.setText("Multiplication");
		optionsActivity_btnFour.setText("Division");

		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);

		Log.d("sptester: ", skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", null));
		String xTest = skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", null);
		if (skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", null).equals("Lessons")) {
			Log.d("testexr: ", "successx");
			if (skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", null).equals("Lessons")) {
				Log.d("testexrx: ", "successxx");
			}

			optionsActivity_btnZero.setOnClickListener(new View.OnClickListener() {
					
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
					skillsOfMDASEditor.putString("optionsActivitySelectedButton", "lessonsCounting");
					skillsOfMDASEditor.commit();

					Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
					optionsActivity.this.startActivity(mInHome);
					optionsActivity.this.finish();
				}
			});
			
			optionsActivity_btnOne.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					mpButton.start();
					SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
					skillsOfMDASEditor.putString("optionsActivitySelectedButton", "lessonsAddition");
					skillsOfMDASEditor.commit();

					Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
					optionsActivity.this.startActivity(mInHome);
					optionsActivity.this.finish();
				}
			});

			optionsActivity_btnTwo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
					skillsOfMDASEditor.putString("optionsActivitySelectedButton", "lessonsSubtraction");
					skillsOfMDASEditor.commit();

					Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
					optionsActivity.this.startActivity(mInHome);
					optionsActivity.this.finish();
				}
			});

			optionsActivity_btnThree.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					mpButton.start();
					SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
					skillsOfMDASEditor.putString("optionsActivitySelectedButton", "lessonsMultiplication");
					skillsOfMDASEditor.commit();

					Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
					optionsActivity.this.startActivity(mInHome);
					optionsActivity.this.finish();
				}
			});

			optionsActivity_btnFour.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					
					SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
					skillsOfMDASEditor.putString("optionsActivitySelectedButton", "lessonsDivision");
					skillsOfMDASEditor.commit();

					Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
					optionsActivity.this.startActivity(mInHome);
					optionsActivity.this.finish();
				}
			});
		} else if (skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", null).equals("Tests")) {
			Log.d("testexr: ", "successxtest");
			if (skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", null).equals("Tests")) {
				Log.d("testexrx: ", "successxxtest");
			}

			
			optionsActivity_btnZero.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					mpButton.start();
					if (optionsActivity_btnZero.getText().equals("Counting")) {
						optionsActivity_btnZero.setVisibility(View.INVISIBLE);
						optionsActivity_btnOne.setText("Test 1");
						optionsActivity_btnTwo.setText("Test 2");
						optionsActivity_btnThree.setText("Test 3");
						optionsActivity_btnFour.setText("Test 4");
						if(skillsOfMDASSharedPreferences.getString("counting1", null) == null){
							optionsActivity_btnOne.setEnabled(true);
						}else{
							optionsActivity_btnOne.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("counting2", null) == null){
							optionsActivity_btnTwo.setEnabled(true);
						}else{
							optionsActivity_btnTwo.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("counting3", null) == null){
							optionsActivity_btnThree.setEnabled(true);
						}else{
							optionsActivity_btnThree.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("counting4", null) == null){
							optionsActivity_btnFour.setEnabled(true);
						}else{
							optionsActivity_btnFour.setEnabled(false);
						}
						Log.d("change", "buttonchange");
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("optionsActivitySelectedButton", "testsCounting");
						skillsOfMDASEditor.commit();
					} else if (optionsActivity_btnOne.getText().equals("Test 1")) {
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("testNumber", "1");
						skillsOfMDASEditor.commit();

						Log.d("testNumber", skillsOfMDASSharedPreferences.getString("testNumber", null));
						Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
						optionsActivity.this.startActivity(mInHome);
						optionsActivity.this.finish();
					}
				}
			});
			
			optionsActivity_btnOne.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					if (optionsActivity_btnOne.getText().equals("Addition")) {
						optionsActivity_btnZero.setVisibility(View.INVISIBLE);
						optionsActivity_btnOne.setText("Test 1");
						optionsActivity_btnTwo.setText("Test 2");
						optionsActivity_btnThree.setText("Test 3");
						optionsActivity_btnFour.setText("Test 4");
						if(skillsOfMDASSharedPreferences.getString("addition1", null) == null){
							optionsActivity_btnOne.setEnabled(true);
						}else{
							optionsActivity_btnOne.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("addition2", null) == null){
							optionsActivity_btnTwo.setEnabled(true);
						}else{
							optionsActivity_btnTwo.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("addition3", null) == null){
							optionsActivity_btnThree.setEnabled(true);
						}else{
							optionsActivity_btnThree.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("addition4", null) == null){
							optionsActivity_btnFour.setEnabled(true);
						}else{
							optionsActivity_btnFour.setEnabled(false);
						}
						Log.d("change", "buttonchange");
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("optionsActivitySelectedButton", "testsAddition");
						skillsOfMDASEditor.commit();
					} else if (optionsActivity_btnOne.getText().equals("Test 1")) {
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("testNumber", "1");
						skillsOfMDASEditor.commit();

						Log.d("testNumber", skillsOfMDASSharedPreferences.getString("testNumber", null));
						Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
						optionsActivity.this.startActivity(mInHome);
						optionsActivity.this.finish();
					}
				}
			});

			optionsActivity_btnTwo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					if (optionsActivity_btnTwo.getText().equals("Subtraction")) {
						optionsActivity_btnZero.setVisibility(View.INVISIBLE);
						optionsActivity_btnOne.setText("Test 1");
						optionsActivity_btnTwo.setText("Test 2");
						optionsActivity_btnThree.setText("Test 3");
						optionsActivity_btnFour.setText("Test 4");

						if(skillsOfMDASSharedPreferences.getString("subtraction1", null) == null){
							optionsActivity_btnOne.setEnabled(true);
						}else{
							optionsActivity_btnOne.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("subtraction2", null) == null){
							optionsActivity_btnTwo.setEnabled(true);
						}else{
							optionsActivity_btnTwo.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("subtraction3", null) == null){
							optionsActivity_btnThree.setEnabled(true);
						}else{
							optionsActivity_btnThree.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("subtraction4", null) == null){
							optionsActivity_btnFour.setEnabled(true);
						}else{
							optionsActivity_btnFour.setEnabled(false);
						}
						Log.d("change", "buttonchange");
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("optionsActivitySelectedButton", "testsSubtraction");
						skillsOfMDASEditor.commit();
					} else if (optionsActivity_btnTwo.getText().equals("Test 2")) {
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("testNumber", "2");
						skillsOfMDASEditor.commit();

						Log.d("testNumber", skillsOfMDASSharedPreferences.getString("testNumber", null));
						Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
						optionsActivity.this.startActivity(mInHome);
						optionsActivity.this.finish();
					}
				}
			});

			optionsActivity_btnThree.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					if (optionsActivity_btnThree.getText().equals("Multiplication")) {
						optionsActivity_btnZero.setVisibility(View.INVISIBLE);
						optionsActivity_btnOne.setText("Test 1");
						optionsActivity_btnTwo.setText("Test 2");
						optionsActivity_btnThree.setText("Test 3");
						optionsActivity_btnFour.setText("Test 4");
						if(skillsOfMDASSharedPreferences.getString("multiplication1", null) == null){
							optionsActivity_btnOne.setEnabled(true);
						}else{
							optionsActivity_btnOne.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("multiplication2", null) == null){
							optionsActivity_btnTwo.setEnabled(true);
						}else{
							optionsActivity_btnTwo.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("multiplication3", null) == null){
							optionsActivity_btnThree.setEnabled(true);
						}else{
							optionsActivity_btnThree.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("multiplication4", null) == null){
							optionsActivity_btnFour.setEnabled(true);
						}else{
							optionsActivity_btnFour.setEnabled(false);
						}
						Log.d("change", "buttonchange");
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("optionsActivitySelectedButton", "testsMultiplication");
						skillsOfMDASEditor.commit();
					} else if (optionsActivity_btnThree.getText().equals("Test 3")) {
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("testNumber", "3");
						skillsOfMDASEditor.commit();

						Log.d("testNumber", skillsOfMDASSharedPreferences.getString("testNumber", null));
						Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
						optionsActivity.this.startActivity(mInHome);
						optionsActivity.this.finish();
					}
				}
			});

			optionsActivity_btnFour.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mpButton.start();
					if (optionsActivity_btnOne.getText().equals("Addition")) {
						optionsActivity_btnZero.setVisibility(View.INVISIBLE);
						optionsActivity_btnOne.setText("Test 1");
						optionsActivity_btnTwo.setText("Test 2");
						optionsActivity_btnThree.setText("Test 3");
						optionsActivity_btnFour.setText("Test 4");
						if(skillsOfMDASSharedPreferences.getString("division1", null) == null){
							optionsActivity_btnOne.setEnabled(true);
						}else{
							optionsActivity_btnOne.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("division2", null) == null){
							optionsActivity_btnTwo.setEnabled(true);
						}else{
							optionsActivity_btnTwo.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("division3", null) == null){
							optionsActivity_btnThree.setEnabled(true);
						}else{
							optionsActivity_btnThree.setEnabled(false);
						}
						if(skillsOfMDASSharedPreferences.getString("division4", null) == null){
							optionsActivity_btnFour.setEnabled(true);
						}else{
							optionsActivity_btnFour.setEnabled(false);
						}
						Log.d("change", "buttonchange");
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("optionsActivitySelectedButton", "testsDivision");
						skillsOfMDASEditor.commit();
					} else if (optionsActivity_btnFour.getText().equals("Test 4")) {
						SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
						skillsOfMDASEditor.putString("testNumber", "4");
						skillsOfMDASEditor.commit();

						Log.d("testNumber", skillsOfMDASSharedPreferences.getString("testNumber", null));
						Intent mInHome = new Intent(optionsActivity.this, lessonsTestsActivity.class);
						optionsActivity.this.startActivity(mInHome);
						optionsActivity.this.finish();
					}
				}
			});
		}

	}

	@Override
	public void onBackPressed() {
		// your code.
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
		skillsOfMDASEditor.commit();
		Intent mInHome = new Intent(optionsActivity.this, mainActivity.class);
		this.startActivity(mInHome);
		this.finish();
	}

}
