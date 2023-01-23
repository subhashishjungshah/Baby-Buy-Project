package com.example.babybuy.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babybuy.Adapters.WishlistRecycleAdapter;
import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.models.PurchaseList;
import com.example.babybuy.models.Wishlist;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MyList extends Fragment {
    List<Wishlist> wishlists = new ArrayList<>();
    RecyclerView recyclerView;
    private int userID;
    private int quantityDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_list, container, false);

        // Getting session to check the list owner
        SessionManagement sessionManagement = new SessionManagement(inflate.getContext());
        userID = sessionManagement.getSession();

        //Setting Up recycle view
        recyclerView = inflate.findViewById(R.id.recyclerProductmylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(inflate.getContext());
        wishlists = databaseHelper.userDao().getallwishlist(userID);
        WishlistRecycleAdapter wishlistRecycleAdapter = new WishlistRecycleAdapter(inflate.getContext(), wishlists);
        recyclerView.setAdapter(wishlistRecycleAdapter);


        //creating touch gesture events on swipe left
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        return inflate;
    }

    // Function to set Left Gesture swipe functionality
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        //Main logic for the swipe functionality
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            DatabaseHelper db = DatabaseHelper.getDB(getContext());
            ArrayList<Wishlist> alldataswipe = (ArrayList<Wishlist>) db.userDao().getallwishlist(userID);
            int wishListId = alldataswipe.get(position).getWishListId();
            Wishlist data = alldataswipe.get(position);
//            Log.d("imageins","hello" + data.getImage() +data.getImagebyte());

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    db.userDao().deleteData(wishListId);
                    // Extracting the remaining data inside the table
                    ArrayList<Wishlist> remainingdata = (ArrayList<Wishlist>) db.userDao().getallwishlist(userID);
                    WishlistRecycleAdapter wishlistRecycleAdapter = new WishlistRecycleAdapter(getContext(), remainingdata);
                    recyclerView.setAdapter(wishlistRecycleAdapter);
                    Toast.makeText(getActivity(), "Successfully Removed", Toast.LENGTH_SHORT).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    // Create an alert builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);
                    builder.setTitle("Quantity");
                    // set the custom layout
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    builder.setView(customLayout);
                    EditText editText = customLayout.findViewById(R.id.editText);
                    editText.requestFocus();
                    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(data.getImage()==null){
                                quantityDialog = Integer.parseInt(editText.getText().toString());
                                PurchaseList purchaseList = new PurchaseList(data.getProductname(), data.getCategory(), data.getPrice(), userID,data.getImagebyte(), quantityDialog,false);
                                db.userDao().addTopurchaselist(purchaseList);
                            }
                            else{
                                quantityDialog = Integer.parseInt(editText.getText().toString());
                                PurchaseList purchaseList = new PurchaseList(data.getProductname(), data.getCategory(), data.getPrice(), data.getImage(), userID,quantityDialog,false);
                                db.userDao().addTopurchaselist(purchaseList);
                            }
                            db.userDao().deleteData(wishListId);
                            ArrayList<Wishlist> remainingdata = (ArrayList<Wishlist>) db.userDao().getallwishlist(userID);
                            WishlistRecycleAdapter wishlistRecycleAdapter = new WishlistRecycleAdapter(getContext(), remainingdata);
                            recyclerView.setAdapter(wishlistRecycleAdapter);
                            Toast.makeText(getActivity(), "Added to Purchase List", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ArrayList<Wishlist> remainingdata = (ArrayList<Wishlist>) db.userDao().getallwishlist(userID);
                            WishlistRecycleAdapter wishlistRecycleAdapter = new WishlistRecycleAdapter(getContext(), remainingdata);
                            recyclerView.setAdapter(wishlistRecycleAdapter);
                            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setOnShowListener(arg0 -> {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pink));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.pink));
                    });
                    dialog.show();
                    break;

            }


        }

        //Decoration of our swipe
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftLabel("REMOVE")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.deletered))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .setSwipeLeftActionIconTint(getResources().getColor(R.color.deletered))
                    .addSwipeLeftBackgroundColor(getResources().getColor(R.color.themetwo))
                    .addSwipeRightLabel("ADD TO CART")
                    .setSwipeRightLabelColor(getResources().getColor(R.color.black))
                    .addSwipeRightActionIcon(R.drawable.ic_shopping_cart_24)
                    .setSwipeRightActionIconTint(getResources().getColor(R.color.black))
                    .addSwipeRightBackgroundColor(getResources().getColor(R.color.themetwo))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    };


}