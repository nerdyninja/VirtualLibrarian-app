package com.example.virtuallibrarian.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtuallibrarian.R;
import com.example.virtuallibrarian.utils.SessionManager;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String t = "LOG_TAG";
    private Request request;
    String responseString;
    ProgressDialog prog;
    SessionManager session ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText Email = (EditText) findViewById(R.id.email_editText);
        final EditText Password = (EditText) findViewById(R.id.pass_editText);

        session= new SessionManager(LoginActivity.this);
        Button LoginButton = (Button) findViewById(R.id.login);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prog = new ProgressDialog(LoginActivity.this);
                prog.setCancelable(true);
                prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                prog.setMessage(" Logging in...");
                prog.setTitle("vLibrarian");
                prog.show();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String url = "http://192.168.43.220/apis/login.php";

                OkHttpClient client = new OkHttpClient();

                RequestBody body = new FormBody.Builder()
                        .add("email", email)
                        .add("password", password)
                        .build();
                request = new Request.Builder()
                        .url(url)
                        .method("POST", body.create(null, new byte[0]))
                        .post(body)
                        .build();
                Log.v("login", body.toString());

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.v(t,"fail");
                        prog.dismiss();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        responseString = response.body().string();
                        Log.v("response", responseString);
                        if (responseString.contains("SUCCESS")) {
                            try {
                                JSONObject root  = new JSONObject(responseString);
                                String status = root.optString("status");
                                JSONObject result = root.optJSONObject("result");
                                String prn = result.optString("prn");
                                String fname = result.optString("fname");
                                String lname = result.optString("lname");
                                String email = result.optString("email");
                                String branch = result.optString("branch");
                                String year = result.optString("year");
                                Long created = result.optLong("created_at");
                                Long updated = result.optLong("updated_at");
                                session.CreateLoginSession(fname, lname, prn, branch, year,email);
                                Log.v("u","i");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            prog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "WRONG", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}