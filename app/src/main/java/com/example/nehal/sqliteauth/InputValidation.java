package com.example.nehal.sqliteauth;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;


public class InputValidation {

   private Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    public boolean isEditTextsFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();

        if (value.isEmpty()){
            textInputLayout.setError(message);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
}
