package com.example.xubii.finalprojectfinal;

/**
 * Created by Xubii on 18-Apr-18.
 */

public class ground
{
    String groundName, ownerName, location, phone, email, image, details;
    Integer ratting, votes;

    public ground()
    {
         groundName= ownerName= location= phone= email= image=details="";
         ratting= votes=0;

    }

    public ground( String groundName, String ownerName, String location, String phone, String email, String image, String details) {
       this.groundName=groundName;
        this.ownerName = ownerName;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.details = details;
        ratting=0;
        votes=0;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRatting() {
        return ratting;
    }

    public void setRatting(Integer ratting) {
        this.ratting = ratting;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}

