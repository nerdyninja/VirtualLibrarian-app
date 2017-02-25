package com.example.virtuallibrarian.utils;

/**
 * Created by rohitramaswamy on 26/02/17.
 */

public class BookCard {
    BookCard(){}

    String title;
    String description;
    String publisher;
    String author;
    String categories;

    public BookCard(String title,String description,String publisher, String author)
    {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.author = author;
    }
    public BookCard(String title,String description)
    {
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setCategories(String categories)
    {
        this.categories = categories;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getCategories() {
        return this.categories;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPublisher() {
        return this.publisher;
    }
}
