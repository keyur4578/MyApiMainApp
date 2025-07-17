package com.karon.myapimainapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karon.myapimainapp.R;
import com.karon.myapimainapp.models.Category;
import com.karon.myapimainapp.models.Quote;

import java.util.ArrayList;

public class CategoryAdvanceAdapter extends  RecyclerView.Adapter<CategoryAdvanceAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category> categories;
    CategoryAdapter.OnClickListner listner;

    public interface OnClickListner
    {
        void onDeleteClick(Category obj);
        void onEditClick(Category obj);
    }

    public CategoryAdvanceAdapter(Context context, ArrayList<Category> categories)
    {
        this.context = context;
        this.categories = categories;
        this.listner = listner;
    }
    public static class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtcatname;
        ImageView txtimage;
        Button btndelete;
        Button btnedit;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
             txtcatname = (TextView) itemView.findViewById(R.id.txtcatname);
             txtimage = (ImageView) itemView.findViewById(R.id.txtimage);
             btndelete = (Button) itemView.findViewById(R.id.btndelete);
             btnedit = (Button) itemView.findViewById(R.id.btnedit);
        }
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category obj = categories.get(position);

        holder.txtcatname.setText(obj.catname.toString());
        Glide.with(context).load(obj.catimage.toString()).into(holder.txtimage);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }



}
