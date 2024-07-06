package com.horidas.dbquery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    EditText edQuery;
    Button searchBtn;
    TextView tvDisplay;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edQuery = findViewById(R.id.edQuery);
        searchBtn = findViewById(R.id.searchBtn);
        tvDisplay = findViewById(R.id.tvDisplay);
        progressBar = findViewById(R.id.progressBar);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String name = edQuery.getText().toString();
                String url = "https://hey-php1.000webhostapp.com/apps/search.php?name="+name;

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                progressBar.setVisibility(View.GONE);

                                tvDisplay.setText(jsonArray.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                           progressBar.setVisibility(View.GONE);
                        Log.d("Server response",volleyError.toString());
                           tvDisplay.setText("Something went wrong");
                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(jsonArrayRequest);
            }
        });


    }
}