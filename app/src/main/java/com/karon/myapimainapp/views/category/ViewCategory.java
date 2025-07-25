package com.karon.myapimainapp.views.category;

import android.content.Intent;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.adapters.CategoryAdapter;
import com.karon.myapimainapp.adapters.QuoteAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.controllers.QuoteManager;
import com.karon.myapimainapp.helper.ApiHelper;
import com.karon.myapimainapp.models.Category;
import com.karon.myapimainapp.models.Quote;
import com.karon.myapimainapp.views.QuoteActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCategory extends AppCompatActivity implements CategoryAdapter.OnClickListner{
    ListView mylistview;
    ArrayList<Category> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_category);
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


//        Type listType = new TypeToken<List<Category>>(){}.getType();
//        data = new Gson().fromJson(response, listType);
//        // data.addAll(categories);
//        CategoryAdapter adapter = new CategoryAdapter(ViewCategory.this,data,this);
//        mylistview.setAdapter(adapter);


        ApiHelper.getRequest(ViewCategory.this, ApiConstant.ALL_CATEGORY,null, response->{
            Type listType = new TypeToken<List<Category>>(){}.getType();
            data = new Gson().fromJson(response, listType);
           // data.addAll(categories);
            CategoryAdapter adapter = new CategoryAdapter(ViewCategory.this,data,this);
            mylistview.setAdapter(adapter);

        },error->{
            Toast.makeText(this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public void onDeleteClick(Category obj) {
        Map<String,String> params = new HashMap<>();
        params.put("id",obj.cat_id.toString());
        ApiHelper.postRequest(ViewCategory.this, ApiConstant.DELETE_CATEGORY,params,null, response->{
            try {
                JSONObject mainobj = new JSONObject(response);
                if(mainobj.getString("status").equals("yes"))
                {
                    loadData();
                }
                else
                {
                    String message =  mainobj.getString("message").toString();
                    Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //Toast.makeText(this, ""+response, Toast.LENGTH_SHORT).show();

        },error->{
            Toast.makeText(this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onEditClick(Category obj) {
        Intent intent = new Intent(ViewCategory.this, EditCategory.class);
        intent.putExtra("id",obj.cat_id.toString());
        startActivity(intent);
    }
}