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

public class FeeFunction extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    private String url="http://schoolplusplus.000webhostapp.com/php_files/feefetching.php";
    private String sp_username,sp_name,sp_clas,sp_divi,sp_rollno;
    public String tablename="fee_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_function);
        t1= findViewById(R.id.fee_name);
        t2= findViewById(R.id.fee_class);
        t3= findViewById(R.id.fee_rollno);
        t4= findViewById(R.id.fee_total);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        sp_username = sp.getString("Username", "");
        sp_name = sp.getString("Name", "");
        sp_clas = sp.getString("Clas", "");
        sp_divi = sp.getString("Divi", "");
        sp_rollno = sp.getString("Rollno", "");

        Toast.makeText(FeeFunction.this, sp_name, Toast.LENGTH_LONG).show();
        t1.setText("Name: "+sp_name);
        t2.setText("Class: "+sp_clas);
        t3.setText("Rollno: "+sp_rollno);

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String totalFee = response;
                    t4.setText("Total fess is : "+totalFee);
                    /*JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");*/

                    //String totalFee=response;

                    /*if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String totalFee = object.getString("name");*/



                            //t4.setText("Total fees: "+totalFee);
                       /* }//end of for
                    }//end if*/
                }//end try
                catch (Exception e) {
                    //e.printStackTrace();
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
                data.put("table", tablename);
                data.put("clas", sp_clas);


                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}