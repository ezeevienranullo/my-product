package com.example.myproduct.ui.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myproduct.model.Recipe;
import com.example.myproduct.ui.tab.recipe.IngredientFragment;
import com.example.myproduct.ui.tab.recipe.InstructionFragment;

public class RecipeTabAdapter extends FragmentStateAdapter {
    private final Recipe recipe;

    public RecipeTabAdapter(@NonNull Fragment fragment, Recipe recipe) {
        super(fragment);
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", recipe);

        if (position == 0) {
            fragment = new IngredientFragment();
        } else {
            fragment = new InstructionFragment(); // example
        }

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}