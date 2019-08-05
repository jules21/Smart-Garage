package com.example.smartgarage.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgarage.R;
import com.example.smartgarage.helpers.InputValidation;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterMechanician extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterMechanician.this;

    private EditText fullNameEditText, emailEditText, passwordEditText,phoneEditText, addressEditText, confirmPasswordEditText;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputTest,inputLayoutPhone,inputLayoutAddress, inputLayoutConfirmPassword;
    private Button signupBtn;
    private InputValidation inputValidation;
    private RelativeLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanician_register);

        initializeWidgets();
        initializeListeners();
        initObjects();
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

//    private void signUp() {
//
//        boolean isValid = true;
//
//        if (fullNameEditText.getText().toString().isEmpty()) {
//            inputLayoutName.setError("Your name is mandatory");
//            isValid = false;
//        } else {
//            inputLayoutName.setErrorEnabled(false);
//        }
//
//        if (emailEditText.getText().toString().isEmpty()) {
//            inputLayoutEmail.setError("Email is mandatory");
//            isValid = false;
//        } else {
//            inputLayoutEmail.setErrorEnabled(false);
//        }
//
//        if (passwordEditText.getText().toString().trim().length() < 8 ) {
//            inputLayoutPassword.setError(getString(R.string.pwd_validation_msg));
//            isValid = false;
//        } else {
//            inputLayoutPassword.setErrorEnabled(false);
//        }
//
//        if (isValid) {
//            Toast.makeText(RegisterMechanician.this, R.string.signup_success, Toast.LENGTH_SHORT).show();
//        }
//    }

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

//        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
//
//            user.setName(textInputEditTextName.getText().toString().trim());
//            user.setEmail(textInputEditTextEmail.getText().toString().trim());
//            user.setPassword(textInputEditTextPassword.getText().toString().trim());
//
//            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
//            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            Snackbar.make(rootLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


//        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(rootLayout, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
//            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
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
