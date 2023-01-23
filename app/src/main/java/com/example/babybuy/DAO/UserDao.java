package com.example.babybuy.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.babybuy.models.LatLongModel;
import com.example.babybuy.models.LoginModel;
import com.example.babybuy.models.LoginWithUser;
import com.example.babybuy.models.Products;
import com.example.babybuy.models.PurchaseList;
import com.example.babybuy.models.UserModel;
import com.example.babybuy.models.UserwithWishList;
import com.example.babybuy.models.Wishlist;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {

    // Queries for extracting while table data
    @Query("select * from User_tbl")
    List<UserModel> getallusers();

    @Query("select * from User_tbl where id =:id")
    public List<LoginWithUser> getloginwithuser(int id);

    // Query for extracting username from login table
    @Query("select username from login")
    List<String> getallloginusernames();

    // Queries for extracting products
    @Query("select * from products")
    List<Products> getallProducts();

    @Query("select * from products where pid = :pid")
    List<Products> getallproductswithpid(int pid);


    @Query("select * from login where username = :username and password = :password ")
    List<LoginModel> checkusernameandpassword(String username, String password);

    @Query("select * from wishlist where userid =:userid")
    List<Wishlist> getallwishlist(int userid);

    @Query("select * from purchaseList where userid = :userid")
    List<PurchaseList> getAllPurchaseList(int userid);

    //Update Query
    @Query("UPDATE purchaseList set isPurchased= :isPurchase where pid = :pid")
    void updatePurchaseList(Boolean isPurchase, int pid);

    @Query("UPDATE products set pname= :pname, category=:pcat, price=:pprice, image = :image where pid = :pid")
    void updateProductList(String pname, String pcat, Float pprice, byte[] image, int pid);

    //Update Query
    @Query("UPDATE purchaseList set address= :address where pid = :pid")
    void updateAddressItematPurchaseList(String address, int pid);


    @Transaction
    @Query("SELECT * FROM user_tbl")
    public List<UserwithWishList> getUserswithWishList();

    //Insert Queries
    @Insert
    void addUser(UserModel userModel);

    @Insert
    void addProduct(Products products);

    @Insert
    void addlogincreds(LoginModel loginModel);

    @Insert
    void addtowishlist(Wishlist wishlist);

    @Insert
    void addTopurchaselist(PurchaseList purchaseList);

    @Insert
    void addLatandLong(LatLongModel latlng);

    @Query("DELETE FROM wishlist WHERE pid = :wishlistid")
    void deleteData(int wishlistid);

    @Query("DELETE FROM products WHERE pid = :pid")
    void deleteProductData(int pid);

    @Query("DELETE FROM latlongtbl WHERE pid = :pid")
    void deletemapinfo(int pid);


    @Query("DELETE FROM purchaseList WHERE pid = :pid")
    void deletePurchaslistData(int pid);

    @Query("select * from purchaseList where pid =:pid")
    List<PurchaseList> getPurchaselistwithID(int pid);

    @Query("select * from latlongtbl where pid =:pid")
    List<LatLongModel> getalllatlong(int pid);


}
