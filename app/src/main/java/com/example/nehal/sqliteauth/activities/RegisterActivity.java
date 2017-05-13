package com.example.nehal.sqliteauth.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.nehal.sqliteauth.InputValidation;
import com.example.nehal.sqliteauth.R;
import com.example.nehal.sqliteauth.modal.User;
import com.example.nehal.sqliteauth.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private NestedScrollView nestedScrollView;

    private TextInputLayout layoutName;
    private TextInputLayout layoutEmail;
    private TextInputLayout layoutPassword;
    private TextInputLayout layoutConfirmPassword;

    private TextInputEditText editTextName;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextConfirmPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        layoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        layoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        layoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        layoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        editTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        editTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        editTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        editTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        AppCompatButton buttonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        AppCompatTextView textViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);
        user = new User();

        buttonRegister.setOnClickListener(this);
        textViewLoginLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
               Intent  i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                break;
        }

    }

    private void postDataToSQLite(){
        if (!inputValidation.isEditTextsFilled(editTextName, layoutName, getString(R.string.empty_feild))){
            return;
        }
        if (!inputValidation.isEditTextsFilled(editTextEmail, layoutEmail, getString(R.string.empty_feild))){
          return;
        }
        if (!inputValidation.isEditTextsFilled(editTextPassword, layoutPassword, getString(R.string.empty_feild))){
            return;
        }
        if (!inputValidation.isEditTextsFilled(editTextConfirmPassword, layoutConfirmPassword, getString(R.string.empty_feild))){
            return;
        }
        if (!inputValidation.isInputEditTextMatches(editTextPassword, editTextConfirmPassword , layoutConfirmPassword ,getString(R.string.password_not_match))){
            return;
        }

        if (!databaseHelper.checkUser(editTextEmail.getText().toString().trim())){
            user.setName(editTextName.getText().toString().trim());
            user.setEmail(editTextEmail.getText().toString().trim());
            user.setPassword(editTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView, R.string.registration_successful,Snackbar.LENGTH_SHORT).show();
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            editTextEmail.setText(null);
            editTextName.setText(null);
            editTextPassword.setText(null);
            editTextConfirmPassword.setText(null);
            startActivity(i);
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.email_exist), Snackbar.LENGTH_LONG ).show();
        }
    }
}
