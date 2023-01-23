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

public class Clothes extends AppCompatActivity {
    private static ArrayList<Firestoreproduct> mArrayList = new ArrayList<>();
    FirestoreProduct firestoreProductadapter;
    ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        RecyclerView recyclerView = findViewById(R.id.recyclerProductClothes);
        //setting layout inside recylce view
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firestoreProductadapter = new FirestoreProduct(Clothes.this, mArrayList);
        recyclerView.setAdapter(firestoreProductadapter);
        //Retrieving data from firestore making reusable code
        FetchdataFirestore fetchdataFirestore = new FetchdataFirestore();
        fetchdataFirestore.fetchdatafromfirestore(this,"clothesItems",firestoreProductadapter,mArrayList);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        mArrayList.clear();
    }
}