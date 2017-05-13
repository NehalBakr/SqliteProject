package com.example.nehal.sqliteauth.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nehal.sqliteauth.modal.User;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_NAME = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser (User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public List<User> SearchUser(String searchName) {

        List<User> userList= new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " where " + COLUMN_USER_NAME +" like " + "'%" + searchName + "%'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query , null);
        if (cursor.moveToFirst()){
            do {
                int id= cursor.getInt(0);
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME));
                userList.add(new User(id, name));

            }while (cursor.moveToNext());
        }
            cursor.close();
            db.close();
        return userList;
    }


    public boolean checkUser(String email){
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL +" = ?";
        String[] selectionArgs ={email};

        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL +" = ?" + " AND "+ COLUMN_USER_PASSWORD + " = ?" ;
        String[] selectionArgs ={email, password};

        Cursor cursor= db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int cursorCount= cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;

    }
}
