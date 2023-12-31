package com.example.e_commerce_galaxy_mart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.e_commerce_galaxy_mart.R;
import com.example.e_commerce_galaxy_mart.adaptors.AddressAdaptor;
import com.example.e_commerce_galaxy_mart.models.AddressModel;
import com.example.e_commerce_galaxy_mart.models.NewProductsModel;
import com.example.e_commerce_galaxy_mart.models.PopularProductModel;
import com.example.e_commerce_galaxy_mart.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdaptor.SelectedAddress {

     Button addAddress;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdaptor addressAdaptor;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
//    Toolbar toolbar;
    Button paymentBtn;
    String mAddress="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AddressActivity", "Entering onCreate");
        setContentView(R.layout.activity_address);

//        toolbar= findViewById(R.id.address_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Object obj= getIntent().getSerializableExtra("item");

        firestore= FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();
//
        recyclerView= findViewById(R.id.address_recycler);
        paymentBtn= findViewById(R.id.payment_btn);
        addAddress= findViewById(R.id.add_address_btn);
//
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList= new ArrayList<>();
        addressAdaptor = new AddressAdaptor(addressModelList, this, this);
        recyclerView.setAdapter(addressAdaptor);
////
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            Log.d("AddressActivity", "Firestore query successful");

                            for (DocumentSnapshot doc:task.getResult().getDocuments()){
                                AddressModel addressModel= doc.toObject(AddressModel.class);
                                addressModelList.add(addressModel);
                                addressAdaptor.notifyDataSetChanged();
                            }


                        }
                        else {
                            Log.e("AddressActivity", "Error in Firestore query", task.getException());
                        }

                    }
                });

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(AddressActivity.this,PaymentActivity.class));
                double amount=0.0;
                if (obj instanceof NewProductsModel){
                    NewProductsModel newProductsModel= (NewProductsModel) obj;
                    amount= newProductsModel.getPrice();
                }
                if (obj instanceof PopularProductModel){
                    PopularProductModel popularProductModel= (PopularProductModel) obj;
                    amount= popularProductModel.getPrice();
                }
                if (obj instanceof ShowAllModel){
                    ShowAllModel showAllModel= (ShowAllModel) obj;
                    amount= showAllModel.getPrice();
                }
                Intent intent= new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);

            }
        });

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });




    }

    @Override
    public void setAddress(String address) {

        mAddress= address;
    }
}