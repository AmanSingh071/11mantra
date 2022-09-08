package com.example.otpscreen.models;

public class blogmodel {
    String blogTitle;
    String blogAuthor;
    String blogTxt;
    String blogDate;
   public String blogimg;
    public blogmodel(){

    }

    public blogmodel(String blogTitle, String blogAuthor, String blogTxt, String blogDate, String blogimg) {
        this.blogTitle = blogTitle;
        this.blogAuthor = blogAuthor;
        this.blogTxt = blogTxt;
        this.blogDate = blogDate;
        this.blogimg = blogimg;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogAuthor() {
        return blogAuthor;
    }

    public void setBlogAuthor(String blogAuthor) {
        this.blogAuthor = blogAuthor;
    }

    public String getBlogTxt() {
        return blogTxt;
    }

    public void setBlogTxt(String blogTxt) {
        this.blogTxt = blogTxt;
    }

    public String getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(String blogDate) {
        this.blogDate = blogDate;
    }

    public String getBlogimg() {
        return blogimg;
    }

    public void setBlogimg(String blogimg) {
        this.blogimg = blogimg;
    }
}
