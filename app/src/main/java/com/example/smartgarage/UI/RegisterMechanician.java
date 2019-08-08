package com.example.smartgarage.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgarage.MainActivity;
import com.example.smartgarage.Model.Mechanician;
import com.example.smartgarage.R;
import com.example.smartgarage.SmartGarageApi;
import com.example.smartgarage.api.APIClient;
import com.example.smartgarage.helpers.InputValidation;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMechanician extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterMechanician.this;

    private EditText fullNameEditText, emailEditText, passwordEditText,phoneEditText, addressEditText, confirmPasswordEditText;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputTest,inputLayoutPhone,inputLayoutAddress, inputLayoutConfirmPassword;
    private Button signupBtn;
    private InputValidation inputValidation;
    private RelativeLayout rootLayout;
    SmartGarageApi smartGarageApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanician_register);

        initializeWidgets();
        initializeListeners();
        initObjects();

        smartGarageApi = APIClient.getClient().create(SmartGarageApi.class);
    }
    private void initializeWidgets() {



        inputLayoutName = (TextInputLayout) findViewById(R.id.inputLayoutFullName);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.inputLayoutEmail);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);
        inputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.inputLayoutConfirmPassword);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.inputLayoutPhone);
        inputLayoutAddress = (TextInputLayout) findViewById(R.id.inputLayoutAddress);

        fullNameEditText = (EditText) findViewById(R.id.fullnameField);
        emailEditText = (EditText) findViewById(R.id.emailField);
        passwordEditText = (EditText) findViewById(R.id.passwordField);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordField);
        phoneEditText = (EditText) findViewById(R.id.phoneField);
        addressEditText = (EditText) findViewById(R.id.addressField);

        rootLayout =(RelativeLayout)findViewById(R.id.mlayout);

        signupBtn = (Button) findViewById(R.id.signUpBtn);
    }

    private void initializeListeners() {

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                signUp();
                RegisterMEchanician();
            }
        });
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);

    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void RegisterMEchanician() {
        if (!inputValidation.isInputEditTextFilled(fullNameEditText, inputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(emailEditText, inputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(addressEditText, inputLayoutAddress, getString(R.string.error_message_email))) {
             return;
        }
        if (!inputValidation.isInputEditTextFilled(phoneEditText, inputLayoutPhone, getString(R.string.error_message_email))) {
             return;
        }
        if (!inputValidation.isInputEditTextEmail(emailEditText, inputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(passwordEditText, inputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(passwordEditText, confirmPasswordEditText,
                inputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }
        createMechanician();
        }

    private void createMechanician() {

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(RegisterMechanician.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("It may take too long please wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();

        Map<String, String> fields = new HashMap<>();
        fields.put("names",fullNameEditText.getText().toString().trim());
        fields.put("email",emailEditText.getText().toString().trim());
        fields.put("phone",phoneEditText.getText().toString().trim());
        fields.put("password",passwordEditText.getText().toString().trim());
        fields.put("address",addressEditText.getText().toString().trim());
        fields.put("garage_id","1");
        fields.put("location","rwanda");


        Call<Mechanician> call = smartGarageApi.createMechanician(fields);
        call.enqueue(new Callback<Mechanician>() {
            @Override
            public void onResponse(Call<Mechanician> call, Response<Mechanician> response) {
                // close it after response
                progressDoalog.dismiss();
                //Toast.makeText()
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Request Sent but Something went wrong", Toast.LENGTH_LONG).show();
                    Log.i("Responsestring", response.body().toString());
                }

                Toast.makeText(getApplicationContext(), "Register done successful", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterMechanician.this, MechanicianList.class);
                startActivity(i);
                Log.i("Responsestring", response.body().toString());
            }

            @Override
            public void onFailure(Call<Mechanician> call, Throwable t) {
                progressDoalog.dismiss();
                Log.v("this", "No Response!");
                Toast.makeText(getApplicationContext(), "The email has already been taken.", Toast.LENGTH_LONG).show();
            }
        });

    }


    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        fullNameEditText.setText(null);
        emailEditText.setText(null);
        phoneEditText.setText(null);
        addressEditText.setText(null);
        confirmPasswordEditText.setText(null);
        passwordEditText.setText(null);
    }
}
