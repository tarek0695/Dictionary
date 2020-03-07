package com.pixelhubllc.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the databases connection.
     */
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the databases connection.
     */
    public void close(){
        if (database != null){
            this.database.close();
        }
    }


    /**
     * Read all quotes from the databases.
     *
     * @return a List of quotes
     */
    public List<String> getWords() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT en_word FROM words", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex("en_word")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public Cursor getMeaning(String text)
    {
        Cursor c= database.rawQuery("SELECT en_definition,example,synonyms,antonyms FROM words WHERE en_word==UPPER('"+text+"')",null);
        return c;
    }

    public Cursor getSuggestions(String text)
    {
        Cursor c= database.rawQuery("SELECT _id, en_word FROM words WHERE en_word LIKE '"+text+"%' LIMIT 40",null);
        return c;
    }

    public void  insertHistory(String text)
    {
        database.execSQL("INSERT INTO history(word) VALUES(UPPER('"+text+"'))");

    }

    public Cursor getHistory()
    {
        Cursor c= database.rawQuery("select distinct  word, en_definition from history h join words w on h.word==w.en_word order by h._id desc",null);
        return c;
    }


    public void  deleteHistory()
    {
        database.execSQL("DELETE  FROM history");
    }
}
