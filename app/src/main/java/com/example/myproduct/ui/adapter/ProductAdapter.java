package com.example.myproduct.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproduct.R;
import com.example.myproduct.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList = new ArrayList<>();

    public void setProductList(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice, tvRating;
        ImageView ivProductImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);

        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.tvTitle.setText(productList.get(position).getTitle());
        holder.tvPrice.setText("₱ "+productList.get(position).getPrice()+"");
        holder.tvRating.setText("★ "+productList.get(position).getRating()+"");

        Glide.with(holder.itemView.getContext())
                .load(productList.get(position).getThumbnail())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
