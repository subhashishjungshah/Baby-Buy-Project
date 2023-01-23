package com.example.babybuy.fragment;

import android.content.DialogInterface;
import android.graphics.Canvas;
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
import android.widget.Toast;

import com.example.babybuy.Adapters.PurchaseRecyclerAdapter;
import com.example.babybuy.Adapters.WishlistRecycleAdapter;
import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.models.PurchaseList;
import com.example.babybuy.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class PurchaseFragment extends Fragment {
    RecyclerView recyclerView;
    private int userID;
    List<PurchaseList> purchaseLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_purchase, container, false);
        // Getting session to check the list owner
        SessionManagement sessionManagement = new SessionManagement(inflate.getContext());
        userID = sessionManagement.getSession();

        recyclerView = inflate.findViewById(R.id.recyclerProductPurchaseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(inflate.getContext());
        purchaseLists = databaseHelper.userDao().getAllPurchaseList(userID);
        PurchaseRecyclerAdapter purchaseRecyclerAdapter = new PurchaseRecyclerAdapter(inflate.getContext(), purchaseLists);
        recyclerView.setAdapter(purchaseRecyclerAdapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        return inflate;
    }

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
            ArrayList<PurchaseList> alldataswipe = (ArrayList<PurchaseList>) db.userDao().getAllPurchaseList(userID);
            int purchaseID = alldataswipe.get(position).getPurchaseID();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    db.userDao().deletePurchaslistData(purchaseID);
                    db.userDao().deletemapinfo(purchaseID);
                    List<PurchaseList> newPurchaselist = db.userDao().getAllPurchaseList(userID);
                    PurchaseRecyclerAdapter purchaseRecyclerAdapter1 = new PurchaseRecyclerAdapter(getContext(), newPurchaselist);
                    recyclerView.setAdapter(purchaseRecyclerAdapter1);
                    break;
                case ItemTouchHelper.RIGHT:
                    db.userDao().updatePurchaseList(true,purchaseID);
                    List<PurchaseList> allPurchaseList = db.userDao().getAllPurchaseList(userID);
                    PurchaseRecyclerAdapter purchaseRecyclerAdapter2 = new PurchaseRecyclerAdapter(getContext(), allPurchaseList);
                    recyclerView.setAdapter(purchaseRecyclerAdapter2);
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
                    .addSwipeRightLabel("MARK AS PURCHASED")
                    .setSwipeRightLabelColor(getResources().getColor(R.color.greensuccess))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_check_24)
                    .setSwipeRightActionIconTint(getResources().getColor(R.color.greensuccess))
                    .addSwipeRightBackgroundColor(getResources().getColor(R.color.themetwo))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    };
}