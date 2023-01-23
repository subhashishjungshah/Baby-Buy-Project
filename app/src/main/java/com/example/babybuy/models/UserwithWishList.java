package com.example.babybuy.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserwithWishList {
    @Embedded
    UserModel userModel;

    @Relation(parentColumn = "id", entityColumn = "userid", entity = Wishlist.class)
    public Wishlist wishlist;

    public UserwithWishList(UserModel userModel, Wishlist wishlist) {
        this.userModel = userModel;
        this.wishlist = wishlist;
    }

    public UserwithWishList() {
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }
}
