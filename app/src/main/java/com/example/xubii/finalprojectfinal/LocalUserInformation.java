package com.example.xubii.finalprojectfinal;

/**
 * Created by Xubii on 27-Apr-18.
 */

public class LocalUserInformation
{
    String name, number, password, id,pic;
    String[]bookings;
    String[]saved;

    public LocalUserInformation(String name, String number, String password, String id, String pic, String[] bookings, String[] saved)
    {
        this.name = name;
        this.number = number;
        this.password = password;
        this.id = id;
        this.pic = pic;
        this.bookings = bookings;
        this.saved = saved;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String[] getBookings() {
        return bookings;
    }

    public void setBookings(String[] bookings) {
        this.bookings = bookings;
    }

    public String[] getSaved() {
        return saved;
    }

    public void setSaved(String[] saved) {
        this.saved = saved;
    }
}
