package com.example.otpscreen.models;

public class rashiModel {
    String rashiname;
   public String rashiimage;

    public rashiModel(){

    }

    public rashiModel(String rashiname, String rashiimage) {
        this.rashiname = rashiname;
        this.rashiimage = rashiimage;
    }

    public String getRashiname() {
        return rashiname;
    }

    public void setRashiname(String rashiname) {
        this.rashiname = rashiname;
    }

    public String getRashiimage() {
        return rashiimage;
    }

    public void setRashiimage(String rashiimage) {
        this.rashiimage = rashiimage;
    }
}
