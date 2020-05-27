package com.example.harden;

public class Book {
    private String date;
    private String activity;
    private String content;
    private String category_name;

    public Book() {
    }

    public Book(String date, String activity, String content, String category_name) {
        this.date = date;
        this.activity= activity;
        this.content = content;
        this.category_name = category_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}