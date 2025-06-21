package com.example.myproduct.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Review implements Parcelable {
     private int rating;
     private String comment;
     private String date;
     private String reviewerName;
     private String email;

     public int getRating() { return rating; }
     public String getComment() { return comment; }
     public String getDate() { return date; }
     public String getReviewerName() { return reviewerName; }
     public String getEmail() { return email; }

    protected Review(Parcel in) {
        rating = in.readInt();
        comment = in.readString();
        date = in.readString();
        reviewerName = in.readString();
        email = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(rating);
        dest.writeString(comment);
        dest.writeString(date);
        dest.writeString(reviewerName);
        dest.writeString(email);
    }
}
