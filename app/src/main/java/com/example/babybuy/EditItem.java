package com.example.babybuy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babybuy.Acitivity.Myproducts;
import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.R;
import com.example.babybuy.models.Products;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EditItem extends AppCompatActivity {
    private int productID;
    EditText pname, pcat, pprice;
    ImageView img;
    AppCompatButton editbtn ,btngallery, btncamera;
    final int GALLERY_CODE = 200;
    final int CAMERA_CODE = 300;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        productID = Integer.parseInt(intent.getStringExtra("productID"));

        pname = findViewById(R.id.ProductName);
        pcat = findViewById(R.id.category);
        pprice = findViewById(R.id.Price);
        img = findViewById(R.id.imageView5);

        editbtn = findViewById(R.id.editbtn);
        btngallery = findViewById(R.id.gallery);
        btncamera = findViewById(R.id.camera);

        DatabaseHelper db = DatabaseHelper.getDB(this);
        List<Products> getallproductswithpid = db.userDao().getallproductswithpid(productID);
        pname.setText(getallproductswithpid.get(0).getProductname());
        pcat.setText(getallproductswithpid.get(0).getCategory());
        pprice.setText(String.valueOf(getallproductswithpid.get(0).getPrice()));
        img.setImageBitmap(getallproductswithpid.get(0).getImageformat());

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery,GALLERY_CODE);
            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icamera,CAMERA_CODE);
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productname = pname.getText().toString();
                String productcategory = pcat.getText().toString();
                String productprice = pprice.getText().toString();
                if(productname.isEmpty() || productcategory.isEmpty() || productprice.isEmpty()){
                    Toast.makeText(EditItem.this,"Empty field aren't accepted!",Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseHelper db = DatabaseHelper.getDB(EditItem.this);
                    db.userDao().updateProductList(productname,productcategory,Float.parseFloat(productprice),convertImageViewtoByteArray(img),productID);
                    startActivity(new Intent(EditItem.this, Myproducts.class));
                }

            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == GALLERY_CODE){
                img.setImageURI(data.getData());
            }
            if(requestCode == CAMERA_CODE){
                Bitmap imge = (Bitmap) (data.getExtras().get("data"));
                img.setImageBitmap(imge);
            }
        }
    }
    private byte[] convertImageViewtoByteArray(ImageView imgview){
        Bitmap bitmap = ((BitmapDrawable) imgview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }

    @Override
    public void onBackPressed() {
    }
}