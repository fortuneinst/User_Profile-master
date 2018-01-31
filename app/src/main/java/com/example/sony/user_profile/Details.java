package com.example.sony.user_profile;

/**
 * Created by SONY on 31-01-2018.
 */

public class Details {

    private String username;
    private int id;
    private int mobilenumber;
    private String email;

    public Details(){

    }

    public void setusername(String username) {
        this.username = username;
    }
    public void setId (int id) {
        this.id = id;
    }
    public void setMobilenumber(int mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
    public void setEmail (String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public int getId() {
        return id;
    }
    public int getMobilenumber() {
        return mobilenumber;
    }
    public String getemail() {
        return email;
    }




}

