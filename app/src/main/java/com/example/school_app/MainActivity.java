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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button lb;
    private EditText t1,t2;
    private MyDbHandler db;
    SharedPreferences sp;

    private String username1;
    private String password1;
    //private String URL="http://10.0.2.2/loginregister/login.php";
    private String URL="http://schoolplusplus.000webhostapp.com/php_files/newlogin.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        db= new MyDbHandler(MainActivity.this);
        /*t1= findViewById(R.id.username);
        t2= findViewById(R.id.password);*/
        lb= findViewById(R.id.loginbtn);
        sp=getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        final String sp_db = sp.getString("selectdb", "");

        username1 = password1 = "";
        t1= findViewById(R.id.username);
        t2= findViewById(R.id.password);

       lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                username1 =t1.getText().toString().trim();
                password1 =t2.getText().toString().trim();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Username",username1);
                editor.commit();
                final String sp_db = sp.getString("selectdb", "");

                if(!username1.equals("") && !password1.equals("")){
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            if (response.equals("Success")) {
                                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                                startActivity(intent);
                                finish();
                            } else if (response.equals("Failure")) {
                                Toast.makeText(MainActivity.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Bosco error is "+error.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            public Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> data=new HashMap<>();
                                data.put("t1", username1);
                                data.put("t2", password1);
                                data.put("tablename",sp_db);
                                return data;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                else{
                    Toast.makeText(MainActivity.this, "All Fields are mandatory", Toast.LENGTH_SHORT).show();
                }

                }
            });

    }

    public void signupfunc(View view){
        Intent intent=new Intent(this,Sign_up.class);
        startActivity(intent);
    }

    public void toForgotPass(View view) {
        Intent intent=new Intent(this,ForgotPass.class);
        startActivity(intent);
    }
}

 /*String s1=t1.getText().toString();
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

                }*/
