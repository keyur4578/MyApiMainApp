package com.karon.myapimainapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.karon.myapimainapp.R;
import com.karon.myapimainapp.models.Quote;

import java.util.ArrayList;

public class QuoteAdapter extends BaseAdapter {

    Context context;
    ArrayList<Quote> quotes;

    public QuoteAdapter(Context context, ArrayList<Quote> quotes)
    {
        this.context = context;
        this.quotes = quotes;
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int i) {
        return quotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.single_quote,null);

        TextView txtquote = (TextView) view.findViewById(R.id.txtquote);
        TextView txtauthor = (TextView) view.findViewById(R.id.txtauthor);


        Quote obj = quotes.get(i);

        txtquote.setText(obj.quote.toString());
        txtauthor.setText(obj.author.toString());





        return view;
    }
}
