package com.example.virtuallibrarian.utils;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class Book {
    Book(){}

    String title;
    String description;
    String type;

    public Book(String title, String description, String type)
    {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    /*public void setTime(String time)
    {
        this.time = time;
    }*/
    public void setType(String type)
    {
        this.type = type;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getDescription()
    {
        return this.description;
    }
    public String getType()
    {
        return this.type;
    }
    /*public String getTime()
    {
        return this.time;
    }*/

}
