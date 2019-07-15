package com.example.firebasetodoapp;

import java.io.Serializable;

public class ToDoList implements Serializable {

    private String title;
    private String date;

    @Override
    public String toString() {
        return "ToDoList{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public ToDoList() {
// Default constructor required for calls to snapshot.toObject(Message.class)
    }

    public ToDoList(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}


