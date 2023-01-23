package com.example.babybuy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.models.Products;
import com.example.babybuy.models.Wishlist;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductRecylcerAdapter extends RecyclerView.Adapter<ProductRecylcerAdapter.ViewHolder> {

    Context context;
    List<Products> products;
    private int userID;
    public ProductRecylcerAdapter(Context context, List<Products> products){
        this.context = context;
        this.products = products;
        SessionManagement sessionManagement = new SessionManagement(context);
        this.userID = sessionManagement.getSession();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.products, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgview.setImageBitmap(products.get(position).getImageformat());
        holder.txtname.setText(products.get(position).getProductname());
        holder.txtcategory.setText(products.get(position).getCategory());
        holder.txtprice.setText(String.valueOf(products.get(position).getPrice()));
        holder.addtowishlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = holder.txtname.getText().toString();
                String desc = holder.txtcategory.getText().toString();
                Float price = Float.parseFloat(holder.txtprice.getText().toString());
                DatabaseHelper databaseHelper = DatabaseHelper.getDB(view.getContext());
                Wishlist wishlist = new Wishlist(name,desc,price,userID,convertImageViewtoByteArray(holder.imgview));
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
    private byte[] convertImageViewtoByteArray(ImageView imgview){
        Bitmap bitmap = ((BitmapDrawable) imgview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }
}
