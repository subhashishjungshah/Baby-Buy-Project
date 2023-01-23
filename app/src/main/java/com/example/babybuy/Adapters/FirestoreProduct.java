package com.example.babybuy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.models.Firestoreproduct;
import com.example.babybuy.models.Wishlist;

import java.util.List;

public class FirestoreProduct extends RecyclerView.Adapter<FirestoreProduct.ViewHolder>{
    Context context;
    List<Firestoreproduct> products;
    private int userID;

    public FirestoreProduct(Context context, List<Firestoreproduct> products){
        this.context = context;
        this.products = products;
        SessionManagement sessionManagement = new SessionManagement(context);
        this.userID = sessionManagement.getSession();
    }
    @NonNull
    @Override
    public FirestoreProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.products, parent, false);
        FirestoreProduct.ViewHolder viewHolder = new FirestoreProduct.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtname.setText(products.get(position).getName());
        holder.txtcategory.setText(products.get(position).getDesc());
        holder.txtprice.setText(String.valueOf(products.get(position).getPrice()));
        String Image = products.get(position).getImage();
        Glide.with(context).load(Image).into(holder.imgview);
        // Triggering event when button wish button was clicked
        holder.addtowishlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = holder.txtname.getText().toString();
                String desc = holder.txtcategory.getText().toString();
                Float price = Float.parseFloat(holder.txtprice.getText().toString());
                DatabaseHelper databaseHelper = DatabaseHelper.getDB(view.getContext());
                Wishlist wishlist = new Wishlist(name,desc,price,Image,userID);
                Log.d("wishlist", "hello" + wishlist);
                databaseHelper.userDao().addtowishlist(wishlist);
                //Disabling the button
                holder.addtowishlistbtn.setEnabled(false);
                holder.addtowishlistbtn.setBackgroundColor(R.drawable.loginbtnstyle);


            }
        });
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtname, txtcategory, txtprice;
        ImageView imgview;
        ImageButton addtowishlistbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.prodtname);
            txtcategory = itemView.findViewById(R.id.prodcategory);
            txtprice = itemView.findViewById(R.id.prdprice);
            imgview = itemView.findViewById(R.id.prodimage);
            addtowishlistbtn = itemView.findViewById(R.id.addtowishlist);


        }
    }
}
