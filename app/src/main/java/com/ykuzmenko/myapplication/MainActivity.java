package com.ykuzmenko.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import org.json.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView1 = (TextView) findViewById(R.id.res1);
        final TextView textView2 = (TextView) findViewById(R.id.res2);
        final TextView textView3 = (TextView) findViewById(R.id.res3);
        final TextView textView4 = (TextView) findViewById(R.id.res4);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.covid19api.com/summary";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response).getJSONObject("Global");
                            String TotalConfirmed = obj.getString("TotalConfirmed");
                            String NewConfirmed = obj.getString("NewConfirmed");
                            String TotalDeaths = obj.getString("TotalDeaths");
                            String NewDeaths = obj.getString("NewDeaths");
                            textView1.setText("Total cases: " + TotalConfirmed);
                            textView2.setText("New Confirmed cases: " + NewConfirmed);
                            textView3.setText("Total deaths: " + TotalDeaths);
                            textView4.setText("New Confirmed deaths: " + NewDeaths);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView1.setText("That didn't work!");
                textView2.setText("That didn't work!");
                textView3.setText("That didn't work!");
                textView4.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);
    }
}