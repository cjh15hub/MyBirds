package com.cjh15hub.mybirds;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cjh15 on 2/21/2017.
 */

public class DBAdapter extends SQLiteOpenHelper {


    private static int version =1;
    private static String DATABASE_NAME = "BIRD_DB";
    private static final String TABLE_BIRD = "bird_table";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "desc";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE BIRD (id integer primary key, name text, desc text)");
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BIRD + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_DESC + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIRD);
        onCreate(db);
    }

    //CRUD
    public void addContact(Bird bird) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bird.getName()); // Bird Name
        values.put(KEY_DESC, bird.getDescription()); // Bird Description

        // Inserting Row
        db.insert(TABLE_BIRD, null, values);
        db.close(); // Closing database connection
    }


    public void addBird(Bird newbird){

    }

    public Bird getBird(int id){
        return null;
    }


}
