package com.example.myproduct.ui.tab.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myproduct.databinding.FragmentInstructionBinding;
import com.example.myproduct.model.Recipe;
import com.example.myproduct.ui.adapter.StringListAdapter;

import java.util.List;

public class InstructionFragment extends Fragment {
    private Recipe recipe;
    private FragmentInstructionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInstructionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            recipe = getArguments().getParcelable("recipe");
        }

        if (recipe != null) {
            List<String> instructions = recipe.getInstructions();
            StringListAdapter ingredientsAdapter = new StringListAdapter(instructions);
            binding.rcInstructions.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcInstructions.setAdapter(ingredientsAdapter);
            binding.rcInstructions.setNestedScrollingEnabled(false);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
