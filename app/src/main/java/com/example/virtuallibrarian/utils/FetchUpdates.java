package com.example.virtuallibrarian.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.virtuallibrarian.R;
import com.example.virtuallibrarian.activities.HomeActivity;

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
    NotificationManager manager;
    ProgressDialog prog;




    public FetchUpdates(Context context, RecyclerView recyclerView,NotificationManager manager) {
        this.context = context;
        this.manager = manager;
        this.recyclerView=recyclerView;
    }

    public void showNotif(String title)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText("Click to reserve your book");
        Intent intent = new Intent(context, HomeActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(HomeActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        manager.notify(0,builder.build());
    }

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
        recyclerView.setAdapter(new UpdatesAdapter(context,session.getUpdateList()));
        prog.dismiss();
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
            session.clear();

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
                if(type.equalsIgnoreCase("exams"))
                {
                    showNotif(title);
                }
                session.addUpdate(new Book(title,description,type));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
