package com.example.myproduct.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myproduct.databinding.FragmentRecipeBinding;
import com.example.myproduct.ui.adapter.ProductAdapter;
import com.example.myproduct.ui.adapter.RecipeAdapter;
import com.example.myproduct.ui.product.ProductViewModel;

public class RecipeFragment extends Fragment {

    private FragmentRecipeBinding binding;
    private RecipeAdapter recipeAdapter;
    private RecipeViewModel recipeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recipeAdapter = new RecipeAdapter();
        int numberOfColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        binding.rcRecipes.setLayoutManager(gridLayoutManager);
        binding.rcRecipes.setAdapter(recipeAdapter);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        recipeViewModel.getRecipeList().observe(getViewLifecycleOwner(),
                products -> recipeAdapter.setProductList(products));

        recipeViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            // You can show/hide a progress bar here
        });

        recipeViewModel.getErrorMessage().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}