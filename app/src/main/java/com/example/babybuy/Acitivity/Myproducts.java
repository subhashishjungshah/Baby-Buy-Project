package com.example.babybuy.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.Toast;

import com.example.babybuy.Adapters.ProductRecylcerAdapter;
import com.example.babybuy.EditItem;
import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.R;
import com.example.babybuy.models.Products;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Myproducts extends AppCompatActivity {
    List<Products> arrayProducts = new ArrayList<>();
    DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproducts);
        recyclerView = findViewById(R.id.recyclerMyproducts);
        //setting layout inside recycle view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayProducts = databaseHelper.userDao().getallProducts();
        ProductRecylcerAdapter productRecylcerAdapter = new ProductRecylcerAdapter(this,arrayProducts);
        recyclerView.setAdapter(productRecylcerAdapter);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    // Function to set Left Gesture swipe functionality
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.
            SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        //Main logic for the swipe functionality
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            DatabaseHelper db = DatabaseHelper.getDB(Myproducts.this);
            ArrayList<Products> alldataswipe = (ArrayList<Products>) db.userDao().getallProducts();
            int productId = alldataswipe.get(position).getProduct_id();
            Products data = alldataswipe.get(position);

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    db.userDao().deleteProductData(productId);
                    ArrayList<Products> remainingdata = (ArrayList<Products>) db.userDao().getallProducts();
                    ProductRecylcerAdapter productRecylcerAdapter = new ProductRecylcerAdapter(Myproducts.this,
                            remainingdata);
                    recyclerView.setAdapter(productRecylcerAdapter);
                    Toast.makeText(Myproducts.this, "Successfully Removed", Toast.LENGTH_SHORT).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    Intent intent = new Intent(Myproducts.this, EditItem.class);
                    intent.putExtra("productID", String.valueOf(productId));
                    startActivity(intent);
                    break;
            }


        }

        //Decoration of our swipe
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
                                boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftLabel("REMOVE")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.deletered))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .setSwipeLeftActionIconTint(getResources().getColor(R.color.deletered))
                    .addSwipeLeftBackgroundColor(getResources().getColor(R.color.themetwo))
                    .addSwipeRightLabel("EDIT")
                    .setSwipeRightLabelColor(getResources().getColor(R.color.black))
                    .addSwipeRightActionIcon(R.drawable.ic_edit_24)
                    .setSwipeRightActionIconTint(getResources().getColor(R.color.bootblue))
                    .addSwipeRightBackgroundColor(getResources().getColor(R.color.themetwo))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Myproducts.this,MainActivity.class));
    }
}