package com.example.virtuallibrarian.utils;

/**
 * Created by rohitramaswamy on 26/02/17.
 */

public class IssueCard {
    String title;
    String description;
    String status;
    String dateToDisplay;

    IssueCard(String title,String description,String status,String dateToDisplay)
    {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateToDisplay = dateToDisplay;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDateToDisplay(String dateToDisplay) {
        this.dateToDisplay = dateToDisplay;
    }

    public String getDateToDisplay() {
        return this.dateToDisplay;
    }
}
