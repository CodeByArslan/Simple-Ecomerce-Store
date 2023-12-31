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
import com.example.e_commerce_galaxy_mart.activities.DetailedActivity;
import com.example.e_commerce_galaxy_mart.models.NewProductsModel;

import java.util.List;

public class NewProductsAdaptor extends RecyclerView.Adapter<NewProductsAdaptor.ViewHolder>{


    private Context context;
    private List<NewProductsModel>list;

    public NewProductsAdaptor(Context context, List<NewProductsModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(com.example.e_commerce_galaxy_mart.R.layout.new_products,parent,false));    }



    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newImg);
        holder.newName.setText(list.get(position).getName());
        holder.newPrice.setText(String.valueOf(list.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Use the adapter position to retrieve the correct item
                    NewProductsModel clickedItem = list.get(adapterPosition);

                    // Start the detailed activity with the clicked item
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("detailed", clickedItem);
                    context.startActivity(intent);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newImg;
        TextView newName,newPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImg= itemView.findViewById(com.example.e_commerce_galaxy_mart.R.id.new_img);
            newName= itemView.findViewById(com.example.e_commerce_galaxy_mart.R.id.new_product_name);
            newPrice= itemView.findViewById(com.example.e_commerce_galaxy_mart.R.id.new_price);


        }
    }
}
