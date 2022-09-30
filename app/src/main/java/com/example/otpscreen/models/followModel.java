package com.example.otpscreen.models;

public class followModel {
    String currentuserid;
    String astrouserid;

    public followModel(String currentuserid, String astrouserid) {
        this.currentuserid = currentuserid;
        this.astrouserid = astrouserid;
    }

    public followModel(){

    }

    public String getAstrouserid() {
        return astrouserid;
    }

    public void setAstrouserid(String astrouserid) {
        this.astrouserid = astrouserid;
    }

    public String getCurrentuserid() {
        return currentuserid;
    }

    public void setCurrentuserid(String currentuserid) {
        this.currentuserid = currentuserid;
    }
}
