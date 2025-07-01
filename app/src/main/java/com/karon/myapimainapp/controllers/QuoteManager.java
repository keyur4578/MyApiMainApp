package com.karon.myapimainapp.controllers;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.karon.myapimainapp.adapters.QuoteAdapter;
import com.karon.myapimainapp.constants.ApiConstant;
import com.karon.myapimainapp.helper.ApiHelper;
import com.karon.myapimainapp.models.Quote;
import com.karon.myapimainapp.models.QuoteResponse;
import com.karon.myapimainapp.views.QuoteActivity;

import java.util.ArrayList;
import java.util.List;

public class QuoteManager {

    public interface QuoteCallback {
        void onSuccess(List<Quote> quotes);
        void onError(String error);
    }

    public static void fetchAllQuotes(Context context, QuoteCallback callback) {
        ApiHelper.getRequest(context, ApiConstant.QUOTE_API,null, response->{
            QuoteResponse quoteResponse = new Gson().fromJson(response,QuoteResponse.class);
            callback.onSuccess(quoteResponse.quotes);

        },error->{
            callback.onError("API error: " + error.getMessage());
        });
    }



}
