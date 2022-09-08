package com.example.otpscreen.models;

public class reviewmodel {
    String name;
    String add;
    String revtxt;
    public String perimg;
    public reviewmodel(){

    }

    public reviewmodel(String name, String add, String revtxt, String perimg) {
        this.name = name;
        this.add = add;
        this.revtxt = revtxt;
        this.perimg = perimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getRevtxt() {
        return revtxt;
    }

    public void setRevtxt(String revtxt) {
        this.revtxt = revtxt;
    }

    public String getPerimg() {
        return perimg;
    }

    public void setPerimg(String perimg) {
        this.perimg = perimg;
    }
}
