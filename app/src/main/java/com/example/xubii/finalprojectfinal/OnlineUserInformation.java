package com.example.xubii.finalprojectfinal;

/**
 * Created by Xubii on 27-Apr-18.
 */

public class OnlineUserInformation
{
    String uid,name, password, contact,type;

    public OnlineUserInformation(String uid, String name, String password, String contact, String type) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.type = type;
    }

    public  OnlineUserInformation()
    {
        uid=name=password=contact=type="";
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

