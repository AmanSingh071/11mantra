package com.example.otpscreen.models;

public class horoscopemainmodels {



 String nametype;



    public String image;


    public horoscopemainmodels(){

    }


    public String getNametype() {
        return nametype;
    }

    public void setNametype(String dailyHoroscope) {
        nametype = dailyHoroscope;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public horoscopemainmodels(String nametype, String image) {
        this.nametype = nametype;
        this.image = image;
    }
}

