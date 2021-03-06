package com.example.school_app;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button lb;
    private EditText t1,t2;
    private MyDbHandler db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        db= new MyDbHandler(MainActivity.this);
        t1= findViewById(R.id.username);
        t2= findViewById(R.id.password);
        lb= findViewById(R.id.loginbtn);
        sp=getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);


       lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=t1.getText().toString();
                String s2=t2.getText().toString();
                Boolean chkmailpass=db.emailpassword(s1,s2);
                if((s1.equals("")) || (s2.equals(""))){
                    Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(chkmailpass){
                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor=sp.edit();

                        editor.putString("Username",s1);
                        editor.commit();
                        Intent intent= new Intent(MainActivity.this,Dashboard.class);
                        //intent.putExtra("key_username",s1);
                        startActivity(intent);
                    }
                    else Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    public void signupfunc(View view){
        Intent intent=new Intent(this,Sign_up.class);
        startActivity(intent);


    }
}