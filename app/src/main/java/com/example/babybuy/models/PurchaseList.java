package com.example.babybuy.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchaseList")
public class PurchaseList {
    @ColumnInfo(name = "pid")
    @PrimaryKey(autoGenerate = true)
    private int purchaseID;
    @ColumnInfo(name = "pname")
    private String productname;
    @ColumnInfo(name = "desc")
    private String desc;
    @ColumnInfo(name = "price")
    private Float price;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "userid")
    private int userid;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] imagebyte;
    @ColumnInfo(name = "quantity")
    private int quantity;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "isPurchased")
    private boolean isPurchase;

    public PurchaseList(int purchaseID, String productname, String desc, Float price, String image, int userid, byte[] imagebyte, int quantity, String address, boolean isPurchase) {
        this.purchaseID = purchaseID;
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.userid = userid;
        this.imagebyte = imagebyte;
        this.quantity = quantity;
        this.address = address;
        this.isPurchase = isPurchase;
    }

    public PurchaseList(String productname, String desc, Float price, String image, int userid, byte[] imagebyte, int quantity, String address, boolean isPurchase) {
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.userid = userid;
        this.imagebyte = imagebyte;
        this.quantity = quantity;
        this.address = address;
        this.isPurchase = isPurchase;
    }

    public PurchaseList(String productname, String desc, Float price, String image, int userid, byte[] imagebyte, int quantity, boolean isPurchase) {
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.userid = userid;
        this.imagebyte = imagebyte;
        this.quantity = quantity;
        this.isPurchase = isPurchase;
    }

    public PurchaseList(String productname, String desc, Float price, int userid, byte[] imagebyte, int quantity, boolean isPurchase) {
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.userid = userid;
        this.imagebyte = imagebyte;
        this.quantity = quantity;
        this.isPurchase = isPurchase;
    }

    public PurchaseList(String productname, String desc, Float price, String image, int userid, int quantity, boolean isPurchase) {
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.userid = userid;
        this.quantity = quantity;
        this.isPurchase = isPurchase;
    }

    public PurchaseList(String productname, String desc, Float price, int userid, int quantity, boolean isPurchase) {
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.userid = userid;
        this.quantity = quantity;
        this.isPurchase = isPurchase;
    }

    public PurchaseList(int purchaseID, String productname, String desc, Float price, String image, int userid, byte[] imagebyte, int quantity) {
        this.purchaseID = purchaseID;
        this.productname = productname;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.userid = userid;
        this.imagebyte = imagebyte;
        this.quantity = quantity;
    }

    public PurchaseList() {
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public byte[] getImagebyte() {
        return imagebyte;
    }

    public void setImagebyte(byte[] imagebyte) {
        this.imagebyte = imagebyte;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bitmap getImageformat() {
        byte[] blob = this.getImagebyte();
        Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        return bmp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPurchase() {
        return isPurchase;
    }

    public void setPurchase(boolean purchase) {
        isPurchase = purchase;
    }

}
