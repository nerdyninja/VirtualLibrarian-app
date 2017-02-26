package com.example.virtuallibrarian.utils;

/**
 * Created by rohitramaswamy on 26/02/17.
 */

public class IssueCard {
    String title;
    String created_at;
    String status;

    IssueCard(String title,String created_at,String status)
    {
        this.title = title;
        this.created_at = created_at;
        this.status = status;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }
}
