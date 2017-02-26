package com.example.virtuallibrarian.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rohitramaswamy on 26/02/17.
 */

public class FetchBooks extends AsyncTask<Void,Void,String> {

    Context context;
    SessionManager session;
    RecyclerView recyclerView;
    ProgressDialog prog;

    public FetchBooks(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
    }
    public FetchBooks() {}

    @Override
    protected String doInBackground(Void... voids) {
        String link = "http://192.168.43.220/apis/listBooks.php";
        Log.v("jkj","kjk");
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        session = new SessionManager(context);
        try{

            URL url = new URL(link);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            inputStream =  new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine())!=null) {
                stringBuilder.append(line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            httpURLConnection.disconnect();
        }
        String result = stringBuilder.toString();
        Log.v("FetchBookResp",result);
        JSONObject root = null;
        try {
            root = new JSONObject(result);
            String status = root.optString("status");
            JSONArray resultArray = root.optJSONArray("result");
            for(int i=0;i<resultArray.length();i++)
            {
                JSONObject jsonObject = resultArray.optJSONObject(i);
                String id = jsonObject.optString("id");
                String title = jsonObject.optString("title");
                String description = jsonObject.optString("description");
                String branch = jsonObject.optString("branch");
                String year = jsonObject.optString("year");
                String type = jsonObject.optString("categories");
                String created_at = jsonObject.optString("created_at");
                String updated_at = jsonObject.optString("updated_at");
                String author = jsonObject.optString("authors");
                String publisher = jsonObject.optString("publisher");
                String isbn = jsonObject.optString("isbn");
                String categories = jsonObject.optString("categories");
                Long createdAt = Long.parseLong(created_at);
                createdAt*=1000;
                Date date = new Date(createdAt);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");
                String dateToDisplay = simpleDateFormat.format(date);
                session.addBook(new BookCard(title,description,author,publisher,isbn,dateToDisplay));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        prog = new ProgressDialog(context);
        prog.setCancelable(true);
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.setMessage("Fetching Book List...");
        prog.setTitle("vLibrarian");
        prog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        recyclerView.setAdapter(new BooksAdapter(context,session.getUpdateList()));
        prog.dismiss();
        Toast.makeText(context,"List Updated", Toast.LENGTH_SHORT).show();    }
}
