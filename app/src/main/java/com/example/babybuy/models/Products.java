package com.example.babybuy.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Products {
    @ColumnInfo(name = "pid")
    @PrimaryKey(autoGenerate = true)
    private int product_id;
    @ColumnInfo(name = "pname")
    private String productname;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name="price")
    private float price;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Products(int product_id, String productname, String category, float price, byte[] image) {
        this.product_id = product_id;
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public Products(String productname, String category, float price, byte[] image) {
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public Products() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Bitmap getImageformat(){
        byte[] blob = this.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
        return bmp;
    }
}
