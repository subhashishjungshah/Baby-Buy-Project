package com.example.babybuy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babybuy.R;
import com.example.babybuy.models.Products;
import com.example.babybuy.models.Wishlist;

import java.util.List;

public class WishlistRecycleAdapter extends RecyclerView.Adapter<WishlistRecycleAdapter.ViewHolder>{

    Context context;
    List<Wishlist> products;
    public WishlistRecycleAdapter(Context context, List<Wishlist> products){
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public WishlistRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wishlistcard, parent, false);
        ViewHolder viewHolder = new WishlistRecycleAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistRecycleAdapter.ViewHolder holder, int position) {
        // Checking whether image is from url or in byte form[]
        String image = products.get(position).getImage();
        if(image == null){
            holder.imgview.setImageBitmap(products.get(position).getImageformat());
        }
        else{
            Glide.with(context).load(image).into(holder.imgview);
        }
        holder.txtname.setText(products.get(position).getProductname());
        holder.txtcategory.setText(products.get(position).getCategory());
        holder.txtprice.setText(String.valueOf(products.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtname, txtcategory, txtprice;
        ImageView imgview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.prodtname);
            txtcategory = itemView.findViewById(R.id.prodcategory);
            txtprice = itemView.findViewById(R.id.prdprice);
            imgview = itemView.findViewById(R.id.prodimage);

        }
    }

}
