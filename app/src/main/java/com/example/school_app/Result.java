package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Result extends AppCompatActivity {
    MyDbHandler dbb;
    TextView Rname, Rrollno, Rclass, Rdiv;
    private String url="http://schoolplusplus.000webhostapp.com/php_files/datafetching.php";
    private String sp_username;
    //Cursor cursor;

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
         sp_username = sp.getString("Username", "");
        final String sp_db=sp.getString("selectdb","");

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String name = object.getString("name");
                            String clas = object.getString("clas");
                            String divi = object.getString("divi");
                            String rollno = object.getString("rollno");

                            Rname.setText(name);
                            Rrollno.setText(rollno);
                            Rclass.setText(clas);
                            Rdiv.setText(divi);
                        }//end of for
                    }//end if
                }//end try
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json login error", Toast.LENGTH_SHORT).show();
                }// end of catch
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data=new HashMap<>();
                data.put("username", sp_username);
                data.put("tablename",sp_db);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}
/*
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

        }*/
