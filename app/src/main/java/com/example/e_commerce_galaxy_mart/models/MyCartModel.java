package com.example.e_commerce_galaxy_mart.models;

import java.io.Serializable;

public class MyCartModel  {

    String currentTime;
    String currentDate;
    String productName;
    String productPrice;
    int totalPrice;
    String totalQuantity;

    public MyCartModel() {
    }

    public MyCartModel(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public MyCartModel(String currentTime, String currentDate, String productName, String productPrice, String totalPrice, String totalQuantity) {
        this.currentTime = currentTime;
        this.currentDate = currentDate;
        this.productName = productName;
        this.productPrice = productPrice;


        this.totalQuantity = totalQuantity;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }





    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
