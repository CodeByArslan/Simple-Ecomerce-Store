package com.example.e_commerce_galaxy_mart.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce_galaxy_mart.R;
import com.example.e_commerce_galaxy_mart.activities.DetailedActivity;
import com.example.e_commerce_galaxy_mart.models.NewProductsModel;
import com.example.e_commerce_galaxy_mart.models.PopularProductModel;

import java.util.List;

public class PopularProductAdaptor extends RecyclerView.Adapter<PopularProductAdaptor.ViewHolder> {

    private Context context;

    private List<PopularProductModel>popularProductModelsList;

    public PopularProductAdaptor(Context context, List<PopularProductModel> popularProductModelsList) {
        this.context = context;
        this.popularProductModelsList = popularProductModelsList;
    }

    @NonNull
    @Override
    public PopularProductAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_itmes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdaptor.ViewHolder holder, int position) {

        Glide.with(context).load(popularProductModelsList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductModelsList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelsList.get(position).getPrice()));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            // Use the adapter position to retrieve the correct item
                            PopularProductModel clickedItem = popularProductModelsList.get(adapterPosition);

                            // Start the detailed activity with the clicked item
                            Intent intent = new Intent(context, DetailedActivity.class);
                            intent.putExtra("detailed", clickedItem);
                            context.startActivity(intent);
                        }
                    }
                });
            }
        });

    }
    @Override
    public int getItemCount() {
        return popularProductModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.all_img);
            name= itemView.findViewById(R.id.all_product_name);
            price= itemView.findViewById(R.id.all_price);

        }
    }
}
