package com.example.virtuallibrarian.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.virtuallibrarian.activities.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE=0;
    // shared pref file name
    private static final String PREF_NAME= "Pref_file";
    // all shared preferences keys
    private static final String IS_LOGIN = "false";

    private static List<Book> updateList = new ArrayList<>();

    private static List<BookCard> bookList = new ArrayList<>();

    private static List<IssueCard> issueList = new ArrayList<>();

    // making it public so that it can be used in other classes too.
    public static final String KEY_FIRST_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_PRN = "115A1018";
    public static final String KEY_BRANCH = "Computer Engineering";
    public static final String KEY_YEAR = "Second Year";


    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void CreateLoginSession(String fname,String lname,String prn,String branch,String year,String email)
    {
        Log.v("in","Create login");
        // setting flag
        editor.putString(IS_LOGIN,"true");
        //Log.v("value",IS_LOGIN);
        // storing details
        editor.putString(KEY_FIRST_NAME,fname);
        editor.putString(KEY_LAST_NAME,lname);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PRN,prn);
        editor.putString(KEY_BRANCH,branch);
        editor.putString(KEY_YEAR,year);
        editor.apply();
    }


    public void logoutUser(Context context) {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.apply();

        // After logout redirect user to Main Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    public String getKeyFirstName()
    {
        return pref.getString(KEY_FIRST_NAME,"first name");
    }

    public String getKeyLastName()
    {
        return pref.getString(KEY_LAST_NAME,"last name");
    }

    public String getIsLogin()
    {
        return pref.getString(IS_LOGIN,"false");
    }

    public String getKeyPrn()
    {
        return pref.getString(KEY_PRN,"115A1018");
    }

    public  String getKeyEmail() {
        return pref.getString(KEY_EMAIL,"rohit.ramaswamy15@siesgst.ac.in");
    }

    public String getKeyBranch()
    {
        return pref.getString(KEY_BRANCH,"IDK");
    }

    public String getKeyYear()
    {
        return pref.getString(KEY_YEAR,"IDK");
    }

    public String getName()
    {
        return getKeyFirstName()+" "+getKeyLastName();
    }

    public void addUpdate(Book bk)
    {
        Log.v("added","to updateList");
        updateList.add(bk);
    }

    public void addBook(BookCard bk)
    {
        Log.v("added","to bookList");
        bookList.add(bk);
    }

    public void clear(){
        issueList.clear();
        updateList.clear();
        bookList.clear();
    }
    public void addIssue(IssueCard bk)
    {
        Log.v("added","to issueList");
        issueList.add(bk);
    }

    List<Book> getUpdateList()
    {
        return updateList;
    }

    List<BookCard> getBookList()
    {
        return bookList;
    }

    List<IssueCard> getIssueList()
    {
        return issueList;
    }
}
