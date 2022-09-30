package com.example.otpscreen.models;

public class signupdetailsmodelsastrologers {
    String email;
    String password;
    String username;
   public String proImg;
    String profession;
    String languages;
    String experience;
    String rate;
    String chatPrice;
    String callPrice;
    String videoPrice;
    String About;
    String fcm;
    String authid;
    public signupdetailsmodelsastrologers(){

    }

    public signupdetailsmodelsastrologers(String email, String password, String username, String proImg, String profession, String languages, String experience, String rate, String chatPrice, String callPrice, String videoPrice, String about, String fcm, String authid) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.proImg = proImg;
        this.profession = profession;
        this.languages = languages;
        this.experience = experience;
        this.rate = rate;
        this.chatPrice = chatPrice;
        this.callPrice = callPrice;
        this.videoPrice = videoPrice;
        About = about;
        this.fcm = fcm;
        this.authid = authid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getChatPrice() {
        return chatPrice;
    }

    public void setChatPrice(String chatPrice) {
        this.chatPrice = chatPrice;
    }

    public String getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(String callPrice) {
        this.callPrice = callPrice;
    }

    public String getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(String videoPrice) {
        this.videoPrice = videoPrice;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getFcm() {
        return fcm;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }
}
