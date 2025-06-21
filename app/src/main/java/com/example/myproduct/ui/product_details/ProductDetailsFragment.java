package com.example.myproduct.ui.product_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myproduct.R;
import com.example.myproduct.databinding.FragmentProductDetailsBinding;
import com.example.myproduct.model.Product;
import com.example.myproduct.ui.adapter.ReviewAdapter;

public class ProductDetailsFragment extends Fragment {
    private FragmentProductDetailsBinding binding;

    private Product product;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            product = getArguments().getParcelable("product");

            Glide.with(requireContext())
                    .load(product.getThumbnail())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(binding.ivProductImage);

            binding.tvPrice.setText("₱ " + product.getPrice());
            binding.tvName.setText(product.getTitle());
            binding.tvDescription.setText(product.getDescription());
            binding.tvStock.setText(product.getStock()+"");
            binding.tvDiscount.setText(product.getDiscount()+"");
            binding.tvProductRating.setText( product.getRating()+ " ★ Product Ratings");

            ReviewAdapter reviewAdapter = new ReviewAdapter();
            binding.rcReviews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcReviews.setAdapter(reviewAdapter);

            if (product.getReviews() != null) {
                reviewAdapter.setReviews(product.getReviews());
            }
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
