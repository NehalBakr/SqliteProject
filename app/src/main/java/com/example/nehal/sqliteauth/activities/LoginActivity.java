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
import com.example.nehal.sqliteauth.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private NestedScrollView nestedScrollView;

    private TextInputLayout layoutEmail;
    private TextInputLayout layoutPassword;

    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        layoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        layoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        editTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        editTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        AppCompatButton buttonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        AppCompatTextView textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

        buttonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);

        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent i = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(i);
                break;
        }


    }
    private void verifyFromSQLite() {
        if (!inputValidation.isEditTextsFilled(editTextEmail, layoutEmail,getString(R.string.empty_feild))){
            return;
        }
        if (!inputValidation.isEditTextsFilled(editTextPassword, layoutPassword, getString(R.string.empty_feild))){
            return;
        }
        if (databaseHelper.checkUser(editTextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim())){
            Intent i = new Intent(LoginActivity.this , ProfileActivity.class);
            i.putExtra("EMAIL",editTextEmail.getText().toString().trim());
            editTextEmail.setText(null);
            editTextPassword.setText(null);
            startActivity(i);
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.invalid_feilds),Snackbar.LENGTH_LONG).show();
        }
    }
}
