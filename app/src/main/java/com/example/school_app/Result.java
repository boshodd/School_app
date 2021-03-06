package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {
    MyDbHandler dbb;
    TextView Rname, Rrollno, Rclass, Rdiv;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        dbb=new MyDbHandler(this);
        Rname = findViewById(R.id.result_name);
        Rrollno = findViewById(R.id.result_rollno);
        Rclass = findViewById(R.id.result_class);
        Rdiv = findViewById(R.id.result_div);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String sp_username = sp.getString("Username", "");


        cursor = dbb.getAllData(sp_username);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data inserted yet", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Rname.setText(cursor.getString(3));
                Rclass.setText(cursor.getString(7));
                Rrollno.setText(String.valueOf(cursor.getInt(0)));
                Rdiv.setText(cursor.getString(8));


            }

        }
    }
}
