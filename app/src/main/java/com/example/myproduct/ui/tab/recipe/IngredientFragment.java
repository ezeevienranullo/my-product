package com.example.myproduct.ui.tab.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myproduct.databinding.FragmentIngredientBinding;
import com.example.myproduct.model.Recipe;
import com.example.myproduct.ui.adapter.StringListAdapter;

import java.util.List;

public class IngredientFragment extends Fragment {
    private Recipe recipe;
    private FragmentIngredientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            recipe = getArguments().getParcelable("recipe");
        }

        if (recipe != null) {
            List<String> ingredients = recipe.getIngredients();
            StringListAdapter ingredientsAdapter = new StringListAdapter(ingredients);
            binding.rcIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcIngredients.setAdapter(ingredientsAdapter);
            binding.rcIngredients.setNestedScrollingEnabled(false);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
