package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class UserSelector extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selector);
        sp=getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
    }

    public void Studentfunc(View view) {

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("selectdb","student_details");
        editor.commit();

        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void Teacherfunc(View view) {
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("selectdb","teacherdb");
        editor.commit();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void Parentfunc(View view) {
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("selectdb","parentdb");
        editor.commit();
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}