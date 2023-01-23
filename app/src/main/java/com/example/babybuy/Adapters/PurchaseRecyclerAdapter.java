package com.example.babybuy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.MapsActivity;
import com.example.babybuy.R;
import com.example.babybuy.models.Products;
import com.example.babybuy.models.PurchaseList;
import com.example.babybuy.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class PurchaseRecyclerAdapter extends RecyclerView.Adapter<PurchaseRecyclerAdapter.ViewHolder> {

    Context context;
    List<PurchaseList> products;
    private int userID;
    LayoutInflater inflater;

    public PurchaseRecyclerAdapter(Context context, List<PurchaseList> products) {
        this.context = context;
        this.products = products;
        inflater = LayoutInflater.from(context);
        SessionManagement sessionManagement = new SessionManagement(context);
        this.userID = sessionManagement.getSession();
    }
    @NonNull
    @Override
    public PurchaseRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.purchase_frag_card, parent, false);
        PurchaseRecyclerAdapter.ViewHolder viewHolder = new PurchaseRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PurchaseList purchaseList = products.get(position);
        String image = products.get(position).getImage();
        if(image == null){
            holder.imgview.setImageBitmap(products.get(position).getImageformat());
        }
        else{
            Glide.with(context).load(image).into(holder.imgview);
        }
        float price = (float) products.get(position).getPrice();
        int quantity = products.get(position).getQuantity();
        float totalamount = price * quantity;
        String purchStatuss = products.get(position).isPurchase() ? "Yes" : "No";
        holder.txtname.setText(products.get(position).getProductname());
        holder.txtquantity.setText(String.valueOf(quantity));
        holder.txtcategory.setText(products.get(position).getDesc());
        holder.totalAmt.setText(String.valueOf(totalamount));
        holder.purchaseStatus.setText(purchStatuss);
        holder.position = position;
        holder.purchaseList = purchaseList;

    }


    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtname, txtcategory, txtquantity, totalAmt, purchaseStatus;
        ImageView imgview;
        ImageButton msgbtn, mapbtn;
        int position;
        PurchaseList purchaseList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.purprodimage);
            txtname = itemView.findViewById(R.id.prodtname);
            txtcategory = itemView.findViewById(R.id.prodDesc);
            txtquantity = itemView.findViewById(R.id.prodquan);
            totalAmt = itemView.findViewById(R.id.totalAmt);
            purchaseStatus = itemView.findViewById(R.id.purchaseStatus);
            msgbtn = itemView.findViewById(R.id.msgbtn);
            mapbtn = itemView.findViewById(R.id.mapBtn);

            mapbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Sending the purchase list ID so that we can play with database
                    Intent intent = new Intent(v.getContext(),MapsActivity.class);
                    intent.putExtra("purchaseID",String.valueOf(purchaseList.getPurchaseID()));
                    v.getContext().startActivity(intent);
                }
            });
            msgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                    builder.setTitle("Enter Phone Number:");
                    // set the custom layout
                    final View customLayout = inflater.inflate(R.layout.custom_layout, null);
                    builder.setView(customLayout);
                    EditText editText = customLayout.findViewById(R.id.editText);

                    builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String stringPhone = editText.getText().toString().trim();
                            String fullmessage = "Product Name: " + purchaseList.getProductname() + "Price: "+purchaseList.getPrice()+ "Quantity: "+purchaseList.getQuantity() + "Address: "+purchaseList.getAddress();
                            try {
                                SmsManager smsman = SmsManager.getDefault();
                                smsman.sendTextMessage(stringPhone, null, fullmessage, null, null);
                                Toast.makeText(itemView.getContext(), "Message Delivered!", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                Toast.makeText(itemView.getContext(), "Fail to Deliver Message!", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setOnShowListener(arg0 -> {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.pink));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.pink));
                    });
                    dialog.show();
                }
            });

        }
    }


}
