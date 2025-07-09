package com.karon.myapimainapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.models.Category;
import com.karon.myapimainapp.models.Product;
import com.karon.myapimainapp.models.Quote;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Category> categories;
    OnClickListner listner;

    public interface OnClickListner
    {
        void onDeleteClick(Category obj);
        void onEditClick(Category obj);
    }
    public CategoryAdapter(Context context, ArrayList<Category> categories,OnClickListner listner)
    {
        this.context = context;
        this.categories = categories;
        this.listner = listner;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.single_category,null);

        TextView txtcatname = (TextView) view.findViewById(R.id.txtcatname);
        ImageView txtimage = (ImageView) view.findViewById(R.id.txtimage);
        Button btndelete = (Button) view.findViewById(R.id.btndelete);
        Button btnedit = (Button) view.findViewById(R.id.btnedit);


        Category obj = categories.get(i);

        txtcatname.setText(obj.catname.toString());
        Glide.with(context).load(obj.catimage.toString()).into(txtimage);

        btndelete.setOnClickListener(v->listner.onDeleteClick(obj));
        btnedit.setOnClickListener(v->listner.onEditClick(obj));


        return view;
    }
}
