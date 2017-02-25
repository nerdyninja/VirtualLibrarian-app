package com.example.virtuallibrarian.utils;

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

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class FetchUpdates extends AsyncTask<Void,Void,String> {

    Context context;
    SessionManager session;
    RecyclerView recyclerView;

    public FetchUpdates(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        recyclerView.setAdapter(new UpdatesAdapter(context,session.getBookList()));
        Toast.makeText(context,"List Updated", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected String doInBackground(Void... params) {
        String url2 = "http://192.168.43.220/apis/updates.php";
        Log.v("jkj","kjk");
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        session = new SessionManager(context);
        try{

            URL url = new URL(url2);
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
        Log.v("tag",result);
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
                String type = jsonObject.optString("type");
                String created_at = jsonObject.optString("created_at");
                String updated_at = jsonObject.optString("updated_at");

                session.addBook(new Book(title,description,type));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
