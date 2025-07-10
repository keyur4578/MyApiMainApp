package com.karon.myapimainapp.models;

import androidx.annotation.NonNull;

public class Category{
    public String cat_id;
    public String catname;
    public String catimage;

    @NonNull
    @Override
    public String toString() {
        return catname.toString();
    }
}

