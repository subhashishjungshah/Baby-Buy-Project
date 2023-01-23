package com.example.babybuy.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlist")
public class Wishlist {
    @ColumnInfo(name = "pid")
    @PrimaryKey(autoGenerate = true)
    private int wishListId;
    @ColumnInfo(name = "pname")
    private String productname;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name="price")
    private Float price;
    @ColumnInfo(name="image")
    private String image;
    @ColumnInfo(name = "userid")
    private int userid;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] imagebyte;



    public Wishlist() {
    }

    public Wishlist(int wishListId, String productname, String category, Float price, String image, int userid, byte[] imagebyte) {
        this.wishListId = wishListId;
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.image = image;
        this.userid = userid;
        this.imagebyte = imagebyte;
    }

    public Wishlist(String productname, String category, Float price, int userid, byte[] imagebyte) {
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.userid = userid;
        this.imagebyte = imagebyte;
    }

    public Wishlist(String productname, String category, Float price, String image, int userid) {
        this.productname = productname;
        this.category = category;
        this.price = price;
        this.image = image;
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public byte[] getImagebyte() {
        return imagebyte;
    }

    public void setImagebyte(byte[] imagebyte) {
        this.imagebyte = imagebyte;
    }

    public Bitmap getImageformat(){
        byte[] blob = this.getImagebyte();
        Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
        return bmp;
    }
}
