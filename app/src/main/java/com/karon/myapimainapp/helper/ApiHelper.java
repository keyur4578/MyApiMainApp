package com.karon.myapimainapp.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ApiHelper {

    // 🔹 Generic GET Request with Optional Headers
    public static void getRequest(Context context,
                                  String url,
                                  Map<String, String> headers,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener errorListener) {

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return headers != null ? headers : new HashMap<>();
            }
        };

        Volley.newRequestQueue(context).add(request);
    }

    // 🔹 Generic POST Request with Optional Headers + Form Parameters
    public static void postRequest(Context context,
                                   String url,
                                   Map<String, String> params,
                                   Map<String, String> headers,
                                   Response.Listener<String> listener,
                                   Response.ErrorListener errorListener) {

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                return params != null ? params : new HashMap<>();
            }

            @Override
            public Map<String, String> getHeaders() {
                return headers != null ? headers : new HashMap<>();
            }
        };

        Volley.newRequestQueue(context).add(request);
    }
}
