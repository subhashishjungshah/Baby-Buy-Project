package com.example.babybuy;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.models.LatLongModel;
import com.example.babybuy.models.PurchaseList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.babybuy.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private int pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Intent intent = getIntent();
        pid = Integer.parseInt(intent.getStringExtra("purchaseID"));
//        Toast.makeText(this, pid, Toast.LENGTH_SHORT).show();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ktm = new LatLng(27.7172, 85.3240);
        mMap.addMarker(new MarkerOptions().position(ktm).title("Kathmandu"));

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(MapsActivity.this);
        List<LatLongModel> getalllatlong = databaseHelper.userDao().getalllatlong(pid);
        if(!getalllatlong.isEmpty()){
            for (int i = 0; i < getalllatlong.size(); i++) {
                //looping throug all lat and long
                LatLng newlatlng = new LatLng(Float.parseFloat(getalllatlong.get(i).getLatitute()), Float.parseFloat(getalllatlong.get(i).getLongitude()));
                mMap.addMarker(new MarkerOptions().position(newlatlng));
                mMap.addCircle(new CircleOptions().center(newlatlng).radius(100).fillColor(Color.LTGRAY).strokeColor(Color.LTGRAY));
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ktm,16f));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked"));
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    ArrayList<Address> addresses = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
//                    Log.d("Addresses: ",addresses.get(0).getAddressLine(0));
//                    Log.d("Addresses: ", String.valueOf(addresses.get(0).getLatitude()));
//                    Log.d("Addresses: ", String.valueOf(addresses.get(0).getLongitude()));
                    String longg = String.valueOf(addresses.get(0).getLongitude());
                    String lattt = String.valueOf(addresses.get(0).getLatitude());
                    DatabaseHelper databaseHelper = DatabaseHelper.getDB(MapsActivity.this);
                    databaseHelper.userDao().updateAddressItematPurchaseList(addresses.get(0).getAddressLine(0),pid);
                    databaseHelper.userDao().addLatandLong(new LatLongModel(lattt,longg,pid));
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}