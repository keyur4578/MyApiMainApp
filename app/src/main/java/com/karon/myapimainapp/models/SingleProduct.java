package com.karon.myapimainapp.models;

import java.util.ArrayList;
import java.util.Date;

class DimensionsSingle{
    public double width;
    public double height;
    public double depth;
}

class MetaSingle{
    public Date createdAt;
    public Date updatedAt;
    public String barcode;
    public String qrCode;
}

 class ReviewSingle{
    public int rating;
    public String comment;
    public Date date;
    public String reviewerName;
    public String reviewerEmail;
}

public class SingleProduct{
    public int id;
    public String title;
    public String description;
    public String category;
    public double price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public ArrayList<String> tags;
    public String brand;
    public String sku;
    public int weight;
    public DimensionsSingle dimensions;
    public String warrantyInformation;
    public String shippingInformation;
    public String availabilityStatus;
    public ArrayList<ReviewSingle> reviews;
    public String returnPolicy;
    public int minimumOrderQuantity;
    public MetaSingle meta;
    public ArrayList<String> images;
    public String thumbnail;
}


