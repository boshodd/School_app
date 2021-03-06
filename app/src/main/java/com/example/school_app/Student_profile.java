package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Student_profile extends AppCompatActivity {
    MyDbHandler db;
    ImageView dp;
    TextView dname2,dparent2,drollno2,dclass2,ddiv2,dphone2,daddress2,ddob2;
    Bitmap dpBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        db=new MyDbHandler(this);
        dp=findViewById(R.id.deatils_dp);


        dname2=findViewById(R.id.details_name2);
        dparent2=findViewById(R.id.details_Pname2);
        drollno2=findViewById(R.id.details_Rollno2);
        dclass2=findViewById(R.id.details_Class2);
        ddiv2=findViewById(R.id.details_Div2);
        daddress2=findViewById(R.id.details_Address2);
        dphone2=findViewById(R.id.details_Phone2);
        ddob2=findViewById(R.id.details_Dob2);



        SharedPreferences sp=getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String sp_username=sp.getString("Username","");

        Cursor cursor=db.getAllData(sp_username);
        // Log.d("rick", "email"+email);
        if (cursor.getCount()==0){
            Toast.makeText(this, "No data inserted yet", Toast.LENGTH_SHORT).show();
            //cursor.close();
        }
        else {
            while (cursor.moveToNext()) {

                byte[] image2byte=cursor.getBlob(4);
                dpBitmap= BitmapFactory.decodeByteArray(image2byte,0,image2byte.length);
                dp.setImageBitmap(dpBitmap);

                dname2.setText(cursor.getString(3));
                String ax=(cursor.getString(6)+", "+cursor.getString(5));
                dparent2.setText(ax);
                drollno2.setText(String.valueOf(cursor.getInt(0)));
                dclass2.setText(cursor.getString(7));
                ddiv2.setText(cursor.getString(8));
                ddob2.setText(cursor.getString(9));
                daddress2.setText(cursor.getString(10));

                dphone2.setText(String.valueOf(cursor.getLong(11)));
                cursor.close();
                db.close();


            }

        }


    }
}