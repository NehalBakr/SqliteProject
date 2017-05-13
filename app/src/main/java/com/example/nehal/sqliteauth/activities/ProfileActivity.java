package com.example.nehal.sqliteauth.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nehal.sqliteauth.R;


public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatTextView textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        String email = getIntent().getStringExtra("EMAIL");
        textViewName.setText(email);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
         SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
       MenuItem searchMenuItem = menu.findItem(R.id.action_search);
       SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        return true;

    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return onSearchRequested();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }
