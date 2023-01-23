package com.example.babybuy.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.babybuy.Adapters.FirestoreProduct;
import com.example.babybuy.Helperclass.FetchdataFirestore;
import com.example.babybuy.R;
import com.example.babybuy.models.Firestoreproduct;

import java.util.ArrayList;

public class Toys extends AppCompatActivity {
    private static ArrayList<Firestoreproduct> mArrayList = new ArrayList<>();
    FirestoreProduct firestoreProductadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys);
        RecyclerView recyclerView = findViewById(R.id.recyclerProductToys);
        recyclerView.hasFixedSize();
        //setting layout inside recylce view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firestoreProductadapter = new FirestoreProduct(Toys.this, mArrayList);
        recyclerView.setAdapter(firestoreProductadapter);
        FetchdataFirestore fetchdataFirestore = new FetchdataFirestore();
        fetchdataFirestore.fetchdatafromfirestore(this,"toyItems",firestoreProductadapter,mArrayList);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        mArrayList.clear();
    }
}