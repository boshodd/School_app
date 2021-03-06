package com.example.school_app;

import android.graphics.Bitmap;

public class Users {
    private int rollno;
    private String username,password,name;
    Bitmap profile_image;
    private String mother,father,clas,div,dob,address;
    private long phoneno;

    public Users(int rollno, String username, String password, String name, Bitmap profile_image, String mother, String father, String clas, String div, String dob, String address, long phoneno) {
        this.rollno = rollno;
        this.username = username;
        this.password = password;
        this.name = name;
        this.profile_image = profile_image;
        this.mother = mother;
        this.father = father;
        this.clas = clas;
        this.div = div;
        this.dob = dob;
        this.address = address;
        this.phoneno = phoneno;
    }



    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Bitmap profile_image) {
        this.profile_image = profile_image;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(long phoneno) {
        this.phoneno = phoneno;
    }
}
