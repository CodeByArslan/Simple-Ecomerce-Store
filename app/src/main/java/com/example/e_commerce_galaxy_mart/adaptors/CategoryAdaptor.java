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
import com.example.e_commerce_galaxy_mart.activities.ShowAllMainActivity;
import com.example.e_commerce_galaxy_mart.models.CategoryModel;
import com.example.e_commerce_galaxy_mart.models.NewProductsModel;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {


    public CategoryAdaptor(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
    private List<CategoryModel> list;



    @NonNull
    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {


        Glide.with(context).load(list.get(position).getImg_url()).into(holder.catimg);
        holder.catname.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Use the adapter position to retrieve the correct item
                    CategoryModel clickedItem = list.get(adapterPosition);

                    // Start the detailed activity with the clicked item
                    Intent intent = new Intent(context, ShowAllMainActivity.class);
                    intent.putExtra("type", clickedItem);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView catimg;
        TextView catname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catimg= itemView.findViewById(R.id.cat_img);
            catname= itemView.findViewById(R.id.cat_name);
        }
    }
}
