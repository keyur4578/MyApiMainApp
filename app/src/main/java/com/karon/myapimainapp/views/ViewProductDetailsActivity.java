package com.karon.myapimainapp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.adapters.ProductAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.models.ProductResponse;
import com.karon.myapimainapp.models.SingleProduct;

public class ViewProductDetailsActivity extends AppCompatActivity {

    ImageView txtimage;
    TextView txtname,txtprice;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_product_details);
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

        id = getIntent().getStringExtra("id");


        txtimage = (ImageView) findViewById(R.id.txtimage);
        txtname = (TextView) findViewById(R.id.txtname);
        txtprice = (TextView) findViewById(R.id.txtprice);


        StringRequest request = new StringRequest(Request.Method.GET, ApiConstant.PRODUCT_SINGLE_API+id,
                response -> {

                    SingleProduct obj = new Gson().fromJson(response,SingleProduct.class);
                    txtname.setText(obj.title.toString());
                    txtprice.setText("Rs "+String.valueOf(obj.price));
                    Glide.with(ViewProductDetailsActivity.this).load(obj.thumbnail.toString()).into(txtimage);

                },
                error -> Toast.makeText(this, "Volley Error: " + error.getMessage(), Toast.LENGTH_LONG).show()
        );
        Volley.newRequestQueue(this).add(request);


    }
}