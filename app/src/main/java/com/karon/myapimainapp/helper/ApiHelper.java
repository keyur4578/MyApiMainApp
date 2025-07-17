package com.karon.myapimainapp.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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
    public static void postJsonRequest(Context context,
                                       String url,
                                       JSONObject jsonParams,
                                       Map<String, String> headers,
                                       Response.Listener<JSONObject> listener,
                                       Response.ErrorListener errorListener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonParams, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> customHeaders = new HashMap<>();
               // customHeaders.put("Content-Type", "application/json");
                if (headers != null) {
                    customHeaders.putAll(headers);  // Add custom headers
                }
                return customHeaders;
            }
        };

        Volley.newRequestQueue(context).add(request);
    }
    public static void getJsonRequest(Context context,
                                       String url,
                                       Map<String, String> headers,
                                       Response.Listener<JSONObject> listener,
                                       Response.ErrorListener errorListener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> customHeaders = new HashMap<>();
                // customHeaders.put("Content-Type", "application/json"); // Optional for GET
                if (headers != null) {
                    customHeaders.putAll(headers);  // Add custom headers like Authorization
                }
                return customHeaders;
            }
        };

        Volley.newRequestQueue(context).add(request);
    }

}
