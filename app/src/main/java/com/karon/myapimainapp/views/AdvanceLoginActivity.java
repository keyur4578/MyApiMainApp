package com.karon.myapimainapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.karon.myapimainapp.MainActivity;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.helper.ApiHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdvanceLoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advance_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject params = new JSONObject();
                try {
                    params.put("username",edtUsername.getText().toString());
                    params.put("password",edtPassword.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");


                ApiHelper.postJsonRequest(AdvanceLoginActivity.this,
                        "https://dummyjson.com/auth/login",params,headers,response -> {

                            try {
                                JSONObject obj = new JSONObject(response.toString());
                                String accessToken =obj.getString("accessToken");
                                String id = obj.getString("id");
                                String name = obj.getString("firstName") + " " + obj.getString("lastName");


                                SharedPreferences sharedPreferences = getSharedPreferences("APP",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("uid",id);
                                editor.putString("sname",name);
                                editor.putString("accessToken",accessToken);
                                editor.putBoolean("islogin",true);
                                editor.commit();

                                Intent mainIntent = new Intent(AdvanceLoginActivity.this, MyProfileActivity.class);
                                startActivity(mainIntent);
                                finish();


                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }


                        },error -> {
                            Toast.makeText(AdvanceLoginActivity.this, "Username and Password not match!", Toast.LENGTH_SHORT).show();
                        });

            }
        });
    }
}