package com.example.nehal.sqliteauth.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nehal.sqliteauth.R;
import com.example.nehal.sqliteauth.adapter.SearchAdapter;
import com.example.nehal.sqliteauth.modal.User;
import com.example.nehal.sqliteauth.sql.DatabaseHelper;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseHelper = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listViewUsers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        handleIntent(getIntent());
    }
    @Override protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    public void doMySearch(String query) {
        List <User> userslist = databaseHelper.SearchUser(query);
        SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, userslist);
        listView.setAdapter(searchAdapter);

    }
}
