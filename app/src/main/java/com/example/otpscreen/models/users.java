package com.example.otpscreen.models;


public class users {
    private String uId, name, number;
    private long coins;

    public users(String uid, String name, String number, int i) {

    }

    public users(String uId, String name, String number, long coins) {
        this.uId = uId;
        this.name = name;
        this.number=number;

        this.coins = coins;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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

    public void setNumber(String profile) {
        this.number = number;
    }



    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }
}