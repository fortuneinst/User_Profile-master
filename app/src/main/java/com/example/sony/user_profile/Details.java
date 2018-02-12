package com.example.sony.user_profile;

/**
 * Created by SONY on 31-01-2018.
 */

public class Details {

    private String userName;
    private String id;
    private String phoneNumber;
    private String emailId;

    public Details(){

    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public Details(String userName, String id, String phoneNumber, String emailId) {
        this.userName = userName;

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }
    /*public Details(String username, String email, String mobilenumber) {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String  getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

*/
}

