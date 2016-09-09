package com.example.skillsofmdas;

import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

	private EditText editTextFirstName;
	private EditText editTextMiddleName;
	private EditText editTextLastName;
	private EditText editTextUsername;
	private EditText editTextPassword;
	private EditText editTextEmail;

	private Button buttonRegister;
	//if using local host, please start apache and xampp server then change the ip address to your current ip address. Else use the online hosting address
	private static final String REGISTER_URL = "http://10.0.2.2/Projects/skillsofMDAS/register.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeruser);

		editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
		editTextMiddleName = (EditText) findViewById(R.id.editTextMiddleName);
		editTextLastName = (EditText) findViewById(R.id.editTextLastName);
		editTextUsername = (EditText) findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);

		buttonRegister = (Button) findViewById(R.id.buttonRegister);

		buttonRegister.setOnClickListener(this);
	}
	



	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		super.onBackPressed();
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
    			Context.MODE_PRIVATE);
        SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
		skillsOfMDASEditor.putString("userID", null);
		skillsOfMDASEditor.putString("firstName", null);
		skillsOfMDASEditor.putString("lastName", null);
		skillsOfMDASEditor.putString("userName", null);
		skillsOfMDASEditor.commit();
		
		Intent mInHome = new Intent(RegisterUserActivity.this, mainActivity.class);
		RegisterUserActivity.this.startActivity(mInHome);
		RegisterUserActivity.this.finish();
	}


	@Override
	public void onClick(View v) {
		if (v == buttonRegister) {
			registerUser();
			Intent mInHome = new Intent(RegisterUserActivity.this, mainActivity.class);
			RegisterUserActivity.this.startActivity(mInHome);
			RegisterUserActivity.this.finish();
		}
	}

	private void registerUser() {
		String firstName = editTextFirstName.getText().toString().trim();
		String middleName = editTextMiddleName.getText().toString().trim();
		String lastName = editTextLastName.getText().toString().trim();
		String username = editTextUsername.getText().toString().trim().toLowerCase();
		String password = editTextPassword.getText().toString().trim().toLowerCase();
		String email = editTextEmail.getText().toString().trim().toLowerCase();

		register(firstName, middleName, lastName, username, password, email);
	}

	private void register(String firstName, String middleName, String lastName, String username, String password, String email) {
		class RegisterUser extends AsyncTask<String, Void, String> {

			ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();
            
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(RegisterUserActivity.this, "Please Wait", null, true, true);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(getApplicationContext(), s + "Registratoin successfull", Toast.LENGTH_LONG).show();
			}

			@Override
			protected String doInBackground(String... params) {
				HashMap<String, String> data = new HashMap<String,String>();
                data.put("firstName",params[0]);
                data.put("middleName",params[1]);
                data.put("lastName",params[2]);
                data.put("username",params[3]);
                data.put("password",params[4]);
                data.put("email",params[5]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);
                String resultx = data.toString();
                Log.w("resultx", resultx);
                return  result;
			}
		}

		RegisterUser ru = new RegisterUser();
        ru.execute(firstName, middleName, lastName,username,password,email);
	}

}
