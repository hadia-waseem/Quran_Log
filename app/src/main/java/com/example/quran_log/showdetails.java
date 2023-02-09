package com.example.quran_log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class showdetails extends AppCompatActivity {

    RecyclerView recycle ;
    ArrayList<Log> dataHolder;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_showdetails);
        recycle=(RecyclerView)findViewById(R.id.recyclerView);
        dataHolder=new ArrayList<>();
        layoutManager = new LinearLayoutManager(showdetails.this);
        recycle.setLayoutManager(layoutManager);
        Cursor cursor=new DBHandler(this).getAllData();
        while (cursor.moveToNext())
        {
            Log lg=new Log();
            lg.setName(cursor.getString(0));
            lg.setSabaq(cursor.getString(1));
            lg.setSabqi(cursor.getString(2));
            lg.setManzil(cursor.getString(3));
            lg.setDate(cursor.getString(4));
            lg.setStd_id(Integer.valueOf(cursor.getString(5)));
            dataHolder.add(lg);
        }
        android.util.Log.d("inset", "List Size: "+dataHolder.size());


        adapter = new myRecyclerViewAdaptor(dataHolder) ;
        recycle.setAdapter(adapter);

    }
}