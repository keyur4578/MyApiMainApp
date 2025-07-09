package com.karon.myapimainapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karon.myapimainapp.MainActivity;
import com.karon.myapimainapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        "https://studiogo.tech/student/shoppingapi/login.php",
                        response->{

                            try {
                                JSONObject obj = new JSONObject(response);
                                String status = obj.getString("status").toString();
                                if(status.equals("yes"))
                                {
                                    String message = obj.getString("message").toString();
                                    JSONObject userdata = obj.getJSONObject("userdata");
                                    String user_id = userdata.getString("user_id");
                                    String name = userdata.getString("name");

                                    SharedPreferences sharedPreferences = getSharedPreferences("APP",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("user_id",user_id);
                                    editor.putString("name",name);
                                    editor.putBoolean("islogin",true);
                                    editor.commit();

                                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                }

                                else
                                {
                                    String message = obj.getString("message").toString();
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, "Parsing Error", Toast.LENGTH_SHORT).show();
                            }


                        },
                        error->{
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("email",email);
                        params.put("password",password);
                        return params;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(stringRequest);



            }
        });
    }
}