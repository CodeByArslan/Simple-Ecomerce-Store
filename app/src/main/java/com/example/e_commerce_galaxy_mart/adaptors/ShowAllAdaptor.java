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
import com.example.e_commerce_galaxy_mart.models.ShowAllModel;

import java.util.List;

public class ShowAllAdaptor extends RecyclerView.Adapter<ShowAllAdaptor.ViewHolder> {

    private Context context;
    private List<ShowAllModel>list;

    public ShowAllAdaptor(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowAllAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAdaptor.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImage);
        holder.mCost.setText("$" + list.get(position).getPrice());
        holder.mName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Use the adapter position to retrieve the correct item
                    ShowAllModel clickedItem = list.get(adapterPosition);

                    // Start the detailed activity with the clicked item
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("detailed",  clickedItem);
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

        private ImageView mItemImage;
        private TextView mCost;
        private  TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemImage= itemView.findViewById(R.id.item_image);
            mCost= itemView.findViewById(R.id.item_cost);
            mName= itemView.findViewById(R.id.item_nam);

        }
    }
}
