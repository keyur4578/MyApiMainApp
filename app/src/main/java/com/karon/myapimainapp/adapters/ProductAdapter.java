package com.karon.myapimainapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> products;
    OnProductClickListner listner;

    public interface OnProductClickListner
    {
        void onMainItemClick(Product obj);
    }

    public ProductAdapter(Context context, ArrayList<Product> products,OnProductClickListner listner)
    {
        this.context = context;
        this.products = products;
        this.listner = listner;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.single_product,null);

        TextView txtproductname = (TextView) view.findViewById(R.id.txtproductname);
        TextView txtcategory = (TextView) view.findViewById(R.id.txtcategory);
        TextView txtprice = (TextView) view.findViewById(R.id.txtprice);
        TextView txtrating = (TextView) view.findViewById(R.id.txtrating);
        ImageView txtimage = (ImageView) view.findViewById(R.id.txtimage);
        LinearLayout mainlayout = (LinearLayout) view.findViewById(R.id.mainlayout);



        Product obj = products.get(i);
        txtproductname.setText(obj.title.toString());
        txtcategory.setText(obj.category.toString());
        txtprice.setText("Rs." + String.valueOf(obj.price));
        txtrating.setText(String.valueOf(obj.rating));

        Glide.with(context).load(obj.thumbnail.toString()).into(txtimage);


        mainlayout.setOnClickListener(v->listner.onMainItemClick(obj));
        return view;
    }
}
