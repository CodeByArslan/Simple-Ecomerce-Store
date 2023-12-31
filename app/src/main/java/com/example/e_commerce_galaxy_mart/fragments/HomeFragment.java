package com.example.e_commerce_galaxy_mart.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.e_commerce_galaxy_mart.R;
import com.example.e_commerce_galaxy_mart.activities.ShowAllMainActivity;
import com.example.e_commerce_galaxy_mart.adaptors.CategoryAdaptor;
import com.example.e_commerce_galaxy_mart.adaptors.NewProductsAdaptor;
import com.example.e_commerce_galaxy_mart.adaptors.PopularProductAdaptor;
import com.example.e_commerce_galaxy_mart.models.CategoryModel;
import com.example.e_commerce_galaxy_mart.models.NewProductsModel;
import com.example.e_commerce_galaxy_mart.models.PopularProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a home fragment and it does simple thing.
 */
public class HomeFragment extends Fragment {

    TextView catShowAll,popularShowAll,newProductShowAll;

    LinearLayout linearLayout;
    ProgressDialog progressDialog;

    RecyclerView catRecyclerview,newProductRecyclerview,popularRecyclerview;

    // Category recyclerview
    CategoryAdaptor categoryAdaptor;
    List<CategoryModel> categoryModelsList;


    // New Product RecyclerView
    NewProductsAdaptor newProductsAdaptor;
    List<NewProductsModel> newProductsModelList;

    //Popular Recyclerview
    PopularProductAdaptor popularProductAdaptor;
    List<PopularProductModel>popularProductModelList;

    //FireStore
    FirebaseFirestore db;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        db= FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getActivity());
        catRecyclerview= root.findViewById(R.id.rec_category);
        newProductRecyclerview= root.findViewById(R.id.new_product_rec);
        popularRecyclerview= root.findViewById(R.id.popular_rec);


        catShowAll=root.findViewById(R.id.category_see_all);
        popularShowAll=root.findViewById(R.id.popular_see_all);
        newProductShowAll=root.findViewById(R.id.newProducts_see_all);

        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), ShowAllMainActivity.class);
                startActivity(intent);
            }
        });
        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), ShowAllMainActivity.class);
                startActivity(intent);
            }
        });
        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), ShowAllMainActivity.class);
                startActivity(intent);
            }
        });

        linearLayout= root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);


        //ImageSlider
        ImageSlider imageSlider= root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels= new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1,"Discount On Shoes Items", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount On Perfume", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"70% OFF", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);

        //ProgressBar
        progressDialog.setTitle("Welcome To GalaxyMart");
        progressDialog.setMessage("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        //Category RV
        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelsList= new ArrayList<>();
        categoryAdaptor = new CategoryAdaptor(getContext(),categoryModelsList);
        catRecyclerview.setAdapter(categoryAdaptor);

        db.collection("Category")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document :task.getResult()){
                                CategoryModel categoryModel= document.toObject(CategoryModel.class);
                                categoryModelsList.add(categoryModel);

                                categoryAdaptor.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        }else {
                            Toast.makeText(getActivity(), "faild"+task.getException(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });

        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList= new ArrayList<NewProductsModel>();
        newProductsAdaptor = new NewProductsAdaptor(getContext(),newProductsModelList);
        newProductRecyclerview.setAdapter(newProductsAdaptor);


        db.collection("NewProducts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document :task.getResult()){
                                NewProductsModel newProductsModel= document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdaptor.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "faild"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });








        //Popular Products
        popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductModelList= new ArrayList<PopularProductModel>();
        popularProductAdaptor = new PopularProductAdaptor(getContext(),popularProductModelList);
        popularRecyclerview.setAdapter(popularProductAdaptor);

        db.collection("AllProdicts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document :task.getResult()){
                                PopularProductModel popularProductModel= document.toObject(PopularProductModel.class);
                                popularProductModelList.add(popularProductModel);
                                popularProductAdaptor.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "faild"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;

    }
}









