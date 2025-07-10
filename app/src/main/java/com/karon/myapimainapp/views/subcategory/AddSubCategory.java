package com.karon.myapimainapp.views.subcategory;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.adapters.CategoryAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.helper.ApiHelper;
import com.karon.myapimainapp.models.Category;
import com.karon.myapimainapp.views.category.ViewCategory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSubCategory extends AppCompatActivity {


    Spinner edtCatname;
    EditText edtSubCatname;
    Button btnSubmit;
    ArrayList<Category> data;
    String catid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_sub_category);
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

        edtCatname = (Spinner) findViewById(R.id.edtCatname);
        edtSubCatname = (EditText) findViewById(R.id.edtSubCatname);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        edtCatname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category obj = data.get(i);
                catid = obj.cat_id.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        data = new ArrayList<>();
        loadData();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subcatname = edtSubCatname.getText().toString();
                Map<String,String> params = new HashMap<>();
                params.put("catid",catid);
                params.put("subcatname",subcatname);


                ApiHelper.postRequest(AddSubCategory.this, ApiConstant.ADD_SUB_CATEGORY,params,null, response->{
                    Toast.makeText(AddSubCategory.this, ""+response, Toast.LENGTH_SHORT).show();

                },error->{
                    Toast.makeText(AddSubCategory.this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
            }
        });





    }
    void loadData()
    {

        ApiHelper.getRequest(AddSubCategory.this, ApiConstant.ALL_CATEGORY,null, response->{
            Type listType = new TypeToken<List<Category>>(){}.getType();
            data = new Gson().fromJson(response, listType);
            ArrayAdapter<Category> adapter = new ArrayAdapter<>(AddSubCategory.this, android.R.layout.simple_spinner_item,data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edtCatname.setAdapter(adapter);

        },error->{
            Toast.makeText(this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        });


    }
}