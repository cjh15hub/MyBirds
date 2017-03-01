package com.cjh15hub.mybirds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
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
        values.put(KEY_NAME, bird.getName());
        values.put(KEY_DESC, bird.getDescription());

        db.insert(TABLE_BIRD, null, values);
        db.close();
    }


    public void addBird(Bird newbird){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newbird.getName());
        values.put(KEY_DESC, newbird.getDescription());

        db.insert(TABLE_BIRD, null, values);
        db.close();
    }

    public Bird getBird(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BIRD, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Bird bird = new Bird(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        db.close();
        return bird;
    }

    public List<Bird> getAllBirds(){
        List<Bird> birdList = new ArrayList<Bird>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BIRD,new String[]{"*"},null,null,null,null,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bird newBird = new Bird(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                birdList.add(newBird);
            } while (cursor.moveToNext());
        }
        db.close();
        return birdList;
    }

    public int getBirdCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(false,TABLE_BIRD,new String[]{"Count(ID)"},null,null,null,null,null,null);
        cursor.moveToFirst();
        int c = cursor.getInt(0);
        db.close();
        return c;
    }

    public int updateBird(Bird bird){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bird.getName());
        values.put(KEY_DESC, bird.getDescription());
        // updating row
        int s = db.update(TABLE_BIRD, values, KEY_ID + " = ?",
                new String[] { String.valueOf(bird.getID()) });
        db.close();
        return s;
    }

    public void deleteBird(Bird bird){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIRD, KEY_ID + " = ?",
                new String[] { String.valueOf(bird.getID()) });
        db.close();
    }


    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIRD,null,null);
        db.close();
    }

}
