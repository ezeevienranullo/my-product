package com.example.myproduct.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Product implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String thumbnail;
    private double price;
    private double rating;
    private List<Review> reviews;
    private double discountPercentage;
    private int stock;

    public List<Review> getReviews() { return reviews; }

    // Getters

    public double getDiscount() { return discountPercentage; }
    public int getStock() { return stock; }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getThumbnail() { return thumbnail; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        thumbnail = in.readString();
        price = in.readDouble();
        reviews = in.createTypedArrayList(Review.CREATOR);
        discountPercentage = in.readDouble();
        stock = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeDouble(price);
        dest.writeTypedList(reviews);
        dest.writeDouble(discountPercentage);
        dest.writeInt(stock);
    }
}

