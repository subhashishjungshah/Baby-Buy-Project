package com.example.babybuy.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.babybuy.Adapters.FirestoreProduct;
import com.example.babybuy.Helperclass.FetchdataFirestore;
import com.example.babybuy.R;
import com.example.babybuy.models.Firestoreproduct;

import java.util.ArrayList;

public class MilkItems extends AppCompatActivity {
    private static ArrayList<Firestoreproduct> mArrayList = new ArrayList<>();
    FirestoreProduct firestoreProductadapter;
    ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_items);
        RecyclerView recyclerView = findViewById(R.id.recyclerProductMilkItems);
        recyclerView.hasFixedSize();
        //setting layout inside recylce view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firestoreProductadapter = new FirestoreProduct(MilkItems.this, mArrayList);
        recyclerView.setAdapter(firestoreProductadapter);
            //Retrieving data from firestore making reusable code
        FetchdataFirestore fetchdataFirestore = new FetchdataFirestore();
        fetchdataFirestore.fetchdatafromfirestore(this,"milkItems",firestoreProductadapter,mArrayList);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        mArrayList.clear();
    }
}