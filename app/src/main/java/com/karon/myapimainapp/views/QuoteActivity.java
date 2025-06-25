package com.karon.myapimainapp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.adapters.QuoteAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.models.Quote;
import com.karon.myapimainapp.models.QuoteResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuoteActivity extends AppCompatActivity {

    ListView mylistview;
    ArrayList<Quote> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quote);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mylistview = (ListView) findViewById(R.id.mylistview);
        data = new ArrayList<>();
        loadData();
    }
    void loadData()
    {
        StringRequest request = new StringRequest(Request.Method.GET, ApiConstant.QUOTE_API,
                response -> {

                        QuoteResponse quoteResponse = new Gson().fromJson(response,QuoteResponse.class);
                        data.addAll(quoteResponse.quotes);

//                        JSONArray quotes = response.getJSONArray("quotes");
//                        for(int i=0;i<quotes.length();i++)
//                        {
//                            JSONObject obj = quotes.getJSONObject(i);
//                            String quote = obj.getString("quote");
//                            String auJSONObject obj = quotes.getJSONObject(i);
////                            String quote = obj.getString("quote");
////                            String author = obj.getString("author");
////thor = obj.getString("author");
//
//                            Quote q = new Quote();
//                            q.setQuote(quote);
//                            q.setAuthor(author);
//
//                            data.add(q);
//
//                        }
                        QuoteAdapter adapter = new QuoteAdapter(QuoteActivity.this,data);
                        mylistview.setAdapter(adapter);




                },
                error->{
                    Toast.makeText(this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                );
        RequestQueue queue = Volley.newRequestQueue(QuoteActivity.this);
        queue.add(request);
    }
}