package com.example.quran_log;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HafizLogger";

    private static final String TABLE_NAME1 = "hafiz";
    private static final String TABLE_NAME2 = "Log";

    private static final String COLUMN_ID = "hafiz_id";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    //    private static final String COLUMN_LOG_ID = "std_id";
    private static final String COLUMN_name = "std_name";
    private static final String COLUMN_sabaq = "sabaq";
    private static final String COLUMN_sabqi = "sabqi";
    private static final String COLUMN_manzil = "manzil";
    private static final String COLUMN_date = "date";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + "("
                + COLUMN_ID + " TEXT PRIMARY KEY ,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT"
                + ")";





        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 +
                " (" +
                COLUMN_name + " TEXT,"+
                COLUMN_sabaq + " TEXT,"+
                COLUMN_sabqi + " TEXT,"+
                COLUMN_manzil + " TEXT,"+
                COLUMN_date + " TEXT,"+
                COLUMN_ID + " TEXT," +
                " PRIMARY KEY(" + COLUMN_ID + "," + COLUMN_date + "))"
                ;
        db.execSQL(sql);
        db.execSQL(sql2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        db.execSQL(sql2);
        onCreate(db);
    }
    public void insertHafiz(Hafiz hf) throws Exception  {
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, hf.getId());
            values.put(COLUMN_NAME, hf.getName());
            values.put(COLUMN_PASSWORD, hf.getPassword());


            db.insert(TABLE_NAME1, null, values);
            db.close();
        }
        catch (Exception e)
        {
            throw new Exception("Error in Hafiz Enterance", e);
        }




    }
    public Integer insertLOG(Log lg) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, lg.getStd_id().toString());
            values.put(COLUMN_name, lg.getName());
            values.put(COLUMN_sabaq, lg.getSabaq());
            values.put(COLUMN_sabqi, lg.getSabaq());
            values.put(COLUMN_manzil, lg.getManzil());
            values.put(COLUMN_date, lg.getDate());


            long result = db.insert(TABLE_NAME2, null, values);
            return Math.toIntExact(result);
        } catch (Exception e) {
            android.util.Log.d("inset", "Log couldn't Inserted: ");
        }
        return 0;

    }
    public void delete(String id,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
//        android.util.Log.d("delete", "Log couldn't Inserted: "+id+"  "+date);
        int count=db.delete(TABLE_NAME2, COLUMN_ID + " = ? AND "+ COLUMN_date+" = ?", new String[] {id,date});
        android.util.Log.d("delete", "Log couldn't Inserted: "+count);
        db.close();
    }
    public void update(Log lg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        android.util.Log.d("delete", "Log couldn't Inserted: "+lg.getSabaq()+lg.getManzil()+lg.getSabaq()+" "+lg.getDate());

        values.put(COLUMN_sabaq, lg.getSabaq());
        values.put(COLUMN_sabqi, lg.getSabqi());
        values.put(COLUMN_manzil,lg.getManzil());

        db.update(TABLE_NAME2, values, COLUMN_ID + " = ? AND "+ COLUMN_date+" = ?", new String[] {lg.getStd_id().toString(),lg.getDate()});
        db.close();
    }
    public List<Hafiz> getHafizData()
    {
        List<Hafiz> hf=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+TABLE_NAME1;
        Cursor cursor= db.rawQuery(select,null);
        if(cursor.moveToFirst())
            do {
                Hafiz hafiz=new Hafiz();
                hafiz.setId(Integer.parseInt(cursor.getString(0)));
                hafiz.setName(cursor.getString(1));
                hafiz.setPassword(cursor.getString(2));
                hf.add(hafiz);
            }while(cursor.moveToNext());
        return hf;
    }
    public Cursor getAllData()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        String query= "SELECT * FROM "  + TABLE_NAME2+ " order  by " +COLUMN_ID+" desc";
        Cursor cursor = db.rawQuery(query,null);
        android.util.Log.d("inset", "Log couldn't Inserted: "+cursor.getCount());
        return cursor;

//        if(cursor.moveToFirst())
//        {
//            do {
//                Log lg=new Log();
//                lg.setName(cursor.getString(0));
//                lg.setSabaq(cursor.getString(1));
//                lg.setSabqi(cursor.getString(2));
//                lg.setManzil(cursor.getString(3));
//                lg.setDate(cursor.getString(4));
//                lg.setStd_id(5);
//
//            }while (cursor.moveToNext());
//        }
//
//        return model;


    }}
