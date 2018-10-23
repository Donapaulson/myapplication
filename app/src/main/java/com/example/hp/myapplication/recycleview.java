package com.example.hp.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class recycleview extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    DBHelper dbHelper;
    String selectquery;
    Cursor cursor;

    ArrayList<String> al = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        //DB VALUE
        dbHelper = new DBHelper(this);
        dbHelper.openConnection();
        //selection query
        selectquery = "select * from users";
        cursor = dbHelper.selectData(selectquery);
        if (cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex("name"));
                al.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.closeConnection();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerAdapter(al);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
