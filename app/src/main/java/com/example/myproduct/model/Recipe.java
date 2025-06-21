package com.example.myproduct.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Recipe implements Parcelable {
    private String name;
    private String image;
    private String cuisine;
    private String difficulty;
    private double rating;
    private int servings;
    private List<String> ingredients;
    private List<String> instructions;

    protected Recipe(Parcel in) {
        name = in.readString();
        image = in.readString();
        cuisine = in.readString();
        difficulty = in.readString();
        rating = in.readDouble();
        servings = in.readInt();
        ingredients = in.createStringArrayList();
        instructions = in.createStringArrayList();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    // Getters
    public String getName() { return name; }
    public String getImage() { return image; }
    public String getCuisine() { return cuisine; }
    public String getDifficulty() { return difficulty; }
    public double getRating() { return rating; }
    public int getServing() { return servings; }
    public List<String> getIngredients() { return ingredients; }
    public List<String> getInstructions() { return instructions; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(cuisine);
        dest.writeString(difficulty);
        dest.writeDouble(rating);
        dest.writeInt(servings);
        dest.writeStringList(ingredients);
        dest.writeStringList(instructions);
    }
}
