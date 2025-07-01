package com.karon.myapimainapp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.helper.ApiHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName,edtmobile,edtEmail,edtPassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
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

        edtName = (EditText) findViewById(R.id.edtName);
        edtmobile = (EditText) findViewById(R.id.edtmobile);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String mobile = edtmobile.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("contact",mobile);
                params.put("email",email);
                params.put("password",password);


                ApiHelper.postRequest(RegisterActivity.this, ApiConstant.REGISTER,params,null,response->{
                    try {
                        JSONObject obj = new JSONObject(response);
                        String status = obj.getString("status").toString();
                        if(status.equals("yes"))
                        {
                            String message = obj.getString("message").toString();
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                        else if(status.equals("exist"))
                        {
                            String message = obj.getString("message").toString();
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String message = obj.getString("message").toString();
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(RegisterActivity.this, "Parsing Error", Toast.LENGTH_SHORT).show();
                    }

                },error->{
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                });



//                StringRequest stringRequest = new StringRequest(
//                        Request.Method.POST,
//                        "https://studiogo.tech/student/shoppingapi/register.php",
//                        response->{
//
//                            try {
//                                JSONObject obj = new JSONObject(response);
//                                String status = obj.getString("status").toString();
//                                if(status.equals("yes"))
//                                {
//                                    String message = obj.getString("message").toString();
//                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//                                }
//                                else if(status.equals("exist"))
//                                {
//                                    String message = obj.getString("message").toString();
//                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    String message = obj.getString("message").toString();
//                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                Toast.makeText(RegisterActivity.this, "Parsing Error", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        },
//                        error->{
//                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//                ){
//                    @Nullable
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String,String> params = new HashMap<>();
//                        params.put("name",name);
//                        params.put("contact",mobile);
//                        params.put("email",email);
//                        params.put("password",password);
//                        return params;
//                    }
//                };
//
//                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//                queue.add(stringRequest);

            }
        });
    }
}