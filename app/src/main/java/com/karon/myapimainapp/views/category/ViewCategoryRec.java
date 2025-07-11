package com.karon.myapimainapp.views.category;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.adapters.CategoryAdapter;
import com.karon.myapimainapp.adapters.CategoryAdvanceAdapter;
import com.karon.myapimainapp.adapters.QuoteAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.controllers.QuoteManager;
import com.karon.myapimainapp.helper.ApiHelper;
import com.karon.myapimainapp.models.Category;
import com.karon.myapimainapp.models.Quote;
import com.karon.myapimainapp.views.QuoteActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewCategoryRec extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Category> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_category_rec);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        data = new ArrayList<>();
        loadData();
    }
    void loadData()
    {
        ApiHelper.getRequest(ViewCategoryRec.this, ApiConstant.ALL_CATEGORY,null, response->{
            Type listType = new TypeToken<List<Category>>(){}.getType();
            data = new Gson().fromJson(response, listType);
            // data.addAll(categories);
            CategoryAdvanceAdapter adapter = new CategoryAdvanceAdapter(ViewCategoryRec.this,data);
            recyclerView.setAdapter(adapter);

        },error->{
            Toast.makeText(this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        });





    }
}