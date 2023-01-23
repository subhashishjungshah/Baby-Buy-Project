package com.example.babybuy.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.R;
import com.example.babybuy.models.Products;

import java.io.ByteArrayOutputStream;

public class addProduct extends Fragment implements View.OnClickListener {

    ImageView imgview;
    EditText productname, category, price;
    AppCompatButton btngallery, btncamera, btnaddproduct;
    final int GALLERY_CODE = 200;
    final int CAMERA_CODE = 300;

    private byte[] convertImageViewtoByteArray(ImageView imgview){
        Bitmap bitmap = ((BitmapDrawable) imgview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        //Extracting values
        imgview = (ImageView) view.findViewById(R.id.imageView5);
        btngallery = (AppCompatButton) view.findViewById(R.id.gallery);
        btncamera = (AppCompatButton) view.findViewById(R.id.camera);
        btnaddproduct = (AppCompatButton) view.findViewById(R.id.savebtn);
        productname = (EditText) view.findViewById(R.id.ProductName);
        category = (EditText) view.findViewById(R.id.category);
        price = (EditText) view.findViewById(R.id.Price);

        //adding events to the btns in fragment
        btngallery.setOnClickListener(this);
        btncamera.setOnClickListener(this);
        btnaddproduct.setOnClickListener(this);

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gallery:
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery,GALLERY_CODE);
                break;
            case R.id.camera:
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icamera,CAMERA_CODE);
                break;
            case R.id.savebtn:
                DatabaseHelper databaseHelper = DatabaseHelper.getDB(getActivity());
                String productName = productname.getText().toString();
                String categor = category.getText().toString();
                String Price = price.getText().toString();
                if(productName.isEmpty() || categor.isEmpty() || Price.isEmpty()){
                    Toast.makeText(getContext(),"Empty field aren't accepted!",Toast.LENGTH_SHORT).show();
                    break;
                }
                else{
                    databaseHelper.userDao().addProduct(new Products(productName,categor.toLowerCase(),Float.parseFloat(Price),convertImageViewtoByteArray(imgview)));
                    Toast.makeText(getContext(),"New Product Added",Toast.LENGTH_SHORT).show();
                    productname.getText().clear();
                    category.getText().clear();
                    price.getText().clear();
                    break;
                }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == GALLERY_CODE){
                imgview.setImageURI(data.getData());
            }
            if(requestCode == CAMERA_CODE){
                Bitmap img = (Bitmap) (data.getExtras().get("data"));
                imgview.setImageBitmap(img);
            }
        }
    }
}