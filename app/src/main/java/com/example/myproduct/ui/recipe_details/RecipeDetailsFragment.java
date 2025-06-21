package com.example.myproduct.ui.recipe_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myproduct.R;
import com.example.myproduct.databinding.FragmentRecipeDetailsBinding;
import com.example.myproduct.model.Recipe;
import com.example.myproduct.ui.adapter.RecipeTabAdapter;
import com.example.myproduct.ui.adapter.StringListAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class RecipeDetailsFragment extends Fragment {
    private FragmentRecipeDetailsBinding binding;
    private Recipe recipe;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            recipe = getArguments().getParcelable("recipe");

            Glide.with(requireContext())
                    .load(recipe.getImage())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(binding.ivProductImage);

            binding.tvName.setText(recipe.getName());
            binding.tvCuisine.setText(recipe.getCuisine());
            binding.tvDifficulty.setText(recipe.getDifficulty());
            binding.tvServing.setText("Servings: "+recipe.getServing());
            binding.ratingBar.setRating((float) recipe.getRating());

            RecipeTabAdapter adapter = new RecipeTabAdapter(this, recipe);
            binding.viewPager.setAdapter(adapter);
        }

        binding.viewPager.setUserInputEnabled(false);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Ingredients");
                    } else if (position == 1) {
                        tab.setText("Instructions");
                    }
                }).attach();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
