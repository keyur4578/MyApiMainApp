package com.karon.myapimainapp.views;

import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.adapters.ProductAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.models.Product;
import com.karon.myapimainapp.models.ProductResponse;
import com.karon.myapimainapp.models.Quote;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewProducts extends AppCompatActivity implements ProductAdapter.OnProductClickListner{
    ListView mylistview;
    ArrayList<Product> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_products);
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
        load_data();
    }
    void load_data()
    {
        StringRequest request = new StringRequest(Request.Method.GET, ApiConstant.PRODUCT_API,
                response -> {

                    ProductResponse productResponse = new Gson().fromJson(response,ProductResponse.class);
                    data.addAll(productResponse.products);
                    ProductAdapter adapter = new ProductAdapter(ViewProducts.this,data,this);
                    mylistview.setAdapter(adapter);

                },
                error -> Toast.makeText(this, "Volley Error: " + error.getMessage(), Toast.LENGTH_LONG).show()
        );
        Volley.newRequestQueue(this).add(request);
    }

    @Override
    public void onMainItemClick(Product obj) {

    }
}