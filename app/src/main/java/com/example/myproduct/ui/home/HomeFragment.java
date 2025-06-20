package com.example.myproduct.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproduct.databinding.FragmentHomeBinding;
import com.example.myproduct.model.Product;
import com.example.myproduct.ui.adapter.ProductAdapter;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProductAdapter productAdapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        productAdapter = new ProductAdapter();
        binding.rcProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcProducts.setAdapter(productAdapter);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getText().observe(getViewLifecycleOwner(),
                title -> binding.tvTitle.setText(title));

        homeViewModel.getProductList().observe(getViewLifecycleOwner(),
                products -> productAdapter.setProductList(products));

        homeViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            // You can show/hide a progress bar here
        });

        homeViewModel.getErrorMessage().observe(getViewLifecycleOwner(), msg -> {
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
