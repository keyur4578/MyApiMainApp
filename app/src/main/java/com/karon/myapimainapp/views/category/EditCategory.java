package com.karon.myapimainapp.views.category;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.helper.ApiHelper;
import com.karon.myapimainapp.helper.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditCategory extends AppCompatActivity {

    ImageView selectedImg;
    Button btnCamera,btnGallery,btnSubmit;
    Uri imageuri;
    Bitmap selectedphoto;
    EditText edtCatname;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_category);
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

        selectedImg = (ImageView) findViewById(R.id.selectedImg);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        edtCatname = (EditText)findViewById(R.id.edtCatname);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        loaddata();



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String catname = edtCatname.getText().toString();

                VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, ApiConstant.UPDATE_CATEGORY, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        String resultResponse = new String(response.data);

                        try {
                            JSONObject obj = new JSONObject(resultResponse);
                            if(obj.getString("status").equals("yes"))
                            {
                                Intent intent = new Intent(EditCategory.this, ViewCategory.class);
                                startActivity(intent);
                                finish();
                                finish();
                            }
                            else
                            {
                                String message = obj.getString("message").toString();
                                Toast.makeText(EditCategory.this, ""+message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                        Toast.makeText(EditCategory.this, ""+resultResponse, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditCategory.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("catname",catname);
                        params.put("id",id);
                        return params;
                    }

                    @Override
                    protected Map<String, DataPart> getByteData() throws AuthFailureError {
                        Map<String, DataPart> params = new HashMap<>();
                        long imagename = System.currentTimeMillis();
                        if(selectedphoto!=null)
                        {
                            params.put("catimage", new DataPart(imagename + ".png", getFileDataFromDrawable(selectedphoto)));
                        }
                        return params;
                    }
                };
                Volley.newRequestQueue(EditCategory.this).add(volleyMultipartRequest);

            }
        });

    }

    void loaddata()
    {
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        ApiHelper.postRequest(EditCategory.this, ApiConstant.SINGLE_CATEGORY,params,null, response->{
            try {
                JSONObject mainobj = new JSONObject(response);
                JSONObject data = mainobj.getJSONObject("data");
                String catname = data.getString("catname").toString();
                String catimage = data.getString("catimage").toString();
                edtCatname.setText(catname);
                Glide.with(EditCategory.this).load(catimage).into(selectedImg);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //Toast.makeText(this, ""+response, Toast.LENGTH_SHORT).show();

        },error->{
            Toast.makeText(this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    void openCamera()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            //ASK for permission
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},101);
        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,201);
        }
    }
    void openGallery()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED)
        {
            //ASK for permission
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_MEDIA_IMAGES},102);
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media. EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,202);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null)
        {
            if(requestCode == 201 && data.getExtras() !=null)
            {
                Bitmap photo  = (Bitmap) data.getExtras().get("data");
                selectedImg.setImageBitmap(photo);
                selectedphoto = photo;
            }
            if(requestCode == 202 && data.getExtras() !=null)
            {
                imageuri = data.getData();
                selectedImg.setImageURI(imageuri);


                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                selectedphoto = bitmap;
            }
        }
    }
}