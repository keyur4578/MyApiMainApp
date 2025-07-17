package com.karon.myapimainapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.helper.ApiHelper;
import com.karon.myapimainapp.models.Category;
import com.karon.myapimainapp.models.MyProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyProfileActivity extends AppCompatActivity {

    TextView txtname,txtemail;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //data = '[{"cat_id":"81","catname":"kkkkk","catimage":"https:\\/\\/studiogo.tech\\/student\\/shoppingapi\\/uploads\\/17514141563275.jpg"},{"cat_id":"82","catname":"nnnn","catimage":"https:\\/\\/studiogo.tech\\/student\\/shoppingapi\\/uploads\\/17514142199565.png"},{"cat_id":"85","catname":"dom\nbanega jon","catimage":"https:\\/\\/studiogo.tech\\/student\\/shoppingapi\\/uploads\\/17520657545263.png"}]';



        txtname = (TextView) findViewById(R.id.txtname);
        txtemail = (TextView) findViewById(R.id.txtemail);

        SharedPreferences sharedPreferences = getSharedPreferences("APP",MODE_PRIVATE);
        String token = sharedPreferences.getString("accessToken","");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + token);

        ApiHelper.getJsonRequest(MyProfileActivity.this,
                "https://dummyjson.com/user/me",headers,response -> {

                  //  MyProfile data = new Gson().fromJson(String.valueOf(response), MyProfile.class);
                  //  String color = data.hair.color;


                    try {




                        JSONObject obj = new JSONObject(response.toString());
                        String name = obj.getString("firstName") + " " + obj.getString("lastName");
                        String email = obj.getString("email");
                        txtname.setText(name);
                        txtemail.setText(email);


                        JSONObject hair = obj.getJSONObject("hair");
                        String color = hair.getString("color");

                        JSONObject address = obj.getJSONObject("address");
                        JSONObject coordinates = address.getJSONObject("coordinates");
                        String lat = coordinates.getString("lat");


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }



                },error -> {
                    Toast.makeText(MyProfileActivity.this, "Username and Password not match!", Toast.LENGTH_SHORT).show();
                });
    }
}