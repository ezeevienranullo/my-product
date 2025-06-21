package com.example.myproduct.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myproduct.databinding.FragmentProductBinding;
import com.example.myproduct.ui.adapter.ProductAdapter;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        productAdapter = new ProductAdapter();
        int numberOfColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        binding.rcProducts.setLayoutManager(gridLayoutManager);
        binding.rcProducts.setAdapter(productAdapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getProductList().observe(getViewLifecycleOwner(),
                products -> productAdapter.setProductList(products));

        productViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            // You can show/hide a progress bar here
        });

        productViewModel.getErrorMessage().observe(getViewLifecycleOwner(), msg -> {
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
