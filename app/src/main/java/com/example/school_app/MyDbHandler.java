package com.example.school_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;



import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import java.util.List;

import com.example.school_app.Params;

public class MyDbHandler extends SQLiteOpenHelper {

    public static final String mytable="students_data";
    public static final String my_db="Users";


/*    public static final String KEY_ID="Id";
    public static final String KEY_USER="Username";
    public static final String KEY_PASS="Password";
    public static final String KEY_NAME="Name";
    public static final String KEY_FATHER="Father";
    public static final String KEY_MOTHER="Mother";

    public static final String KEY_DIV="Div";
    public static final String KEY_ROLLNO="Roll_no";
    public static final String KEY_CLASS="Class";
    public static final String KEY_DOB="Dob";
    public static final String KEY_ADDR="Adress";
    public static final String KEY_PHONE="Phone_NO";*/
    ByteArrayOutputStream baos;
    byte[] imageInByte;


    public MyDbHandler(Context context) {
        super(context,my_db,null,1);
    }


@Override
public void onCreate(SQLiteDatabase db) {
    //srollno, susrn, spass, sname, image2store, smother, sfather, sclass, sdiv, sdob, saddress, sphone

    String query="create table "+mytable+"(Rollno INTEGER , Username TEXT ,Password TEXT , Name TEXT , Image BLOB , Mother TEXT , Father TEXT , Class TEXT , Div TEXT , Dob TEXT , Address TEXT , Phone LONG)";
    Log.d("rich", "onCreate: "+ query);
    db.execSQL(query);
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addOne(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imgBitmap=user.getProfile_image();
        baos=new ByteArrayOutputStream();
        imgBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        imageInByte = baos.toByteArray();

        //srollno, susrn, spass, sname, image2store, smother, sfather, sclass, sdiv, sdob, saddress, sphone
        ContentValues cv = new ContentValues();
        cv.put("Rollno", user.getRollno());
        cv.put("Username", user.getUsername());
        cv.put("Password", user.getPassword());
        cv.put("Name", user.getName());
        cv.put("Image ",imageInByte);
        cv.put("Mother", user.getMother());
        cv.put("Father", user.getFather());
        cv.put("Class", user.getClas());
        cv.put("Div", user.getDiv());
        cv.put("Dob", user.getDob());
        cv.put("Address", user.getAddress());
        cv.put("Phone", user.getPhoneno());

        long conf=db.insert(mytable, null, cv);
        if(conf==-1) {return false;}
        else {return true;}


    }

    public Boolean chkmail(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT * FROM "+mytable+" where Username=?";
        Cursor cursor= db.rawQuery(query,new String[]{email});

        if(cursor.getCount()>0) {return false;}
            else{return true;}
    }

    //to check login credentials exist (LOGIN)

    public Boolean emailpassword(String semail,String spassword){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from "+mytable+" where Username = ? and Password = ?",new String[]{semail,spassword});
        if (cursor.getCount()>0){return true;}
        else{return false;}
    }


    public Cursor getAllData(String email){

        SQLiteDatabase db=this.getReadableDatabase();
        String result="select * from "+mytable+" where Username = ?";

        Cursor cursor=db.rawQuery(result,new String[]{email});
        Log.d("rick", " Id obtained is:  "+email);


        return cursor;
    }

    /*public List<Users> displayContacts(){
        List<Users> contactList = new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+Params.ltstudents_tb;

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                Users contact =new Users();
                contact.setId(cursor.getInt(0));
                contact.setUsername(cursor.getString(1));
                contact.setPassword(cursor.getString(2));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }*/

}
















































