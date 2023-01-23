package com.example.babybuy.Helperclass;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.babybuy.Adapters.FirestoreProduct;
import com.example.babybuy.models.Firestoreproduct;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FetchdataFirestore {

    public void fetchdatafromfirestore(Context context, String collectionName, FirestoreProduct firestoreProductadapter, ArrayList<Firestoreproduct> mArrayList){
        FirebaseFirestore.getInstance().collection(collectionName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty()){
//                    Log.d("subhu", "onSuccess: LIST EMPTY");
                    return;
                }
                else{
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {

                        Firestoreproduct m = d.toObject(Firestoreproduct.class);
                        mArrayList.add(m);
                    }
                    firestoreProductadapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Error getting Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
