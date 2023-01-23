package com.example.babybuy.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.fragment.HomeFragment;
import com.example.babybuy.fragment.MyList;
import com.example.babybuy.fragment.ProfileFragment;
import com.example.babybuy.fragment.PurchaseFragment;
import com.example.babybuy.fragment.addProduct;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    FloatingActionButton fab;
    private int userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.pink));
        }
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        userID = sessionManagement.getSession();

        bnv = findViewById(R.id.bottomNavigationView);
        bnv.setBackground(null);
        fab = findViewById(R.id.addproduct);

        fab.setOnClickListener(v->{
            bnv.setSelectedItemId(R.id.holder);
            loadFragment(new addProduct(), false);
//            Log.d("subhuu","value is" + userID);
        });

        bnv.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.btnnav_home){
                    loadFragment(new HomeFragment(), false);
                }
                else if(id==R.id.wishlist){
                    loadFragment(new MyList(), false);
                }
                else if(id==R.id.btnnav_list){
                    loadFragment(new PurchaseFragment(),false);
                }
                else{
                    loadFragment(new ProfileFragment(),false);
                }
                return true;
            }
        });
         // Default selected fragment
        bnv.setSelectedItemId(R.id.btnnav_home);

    }

    // function to decide whether to add or replace fragment
    public void loadFragment(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag){
            ft.add(R.id.Frame_layout,fragment);
        }
        else{
            ft.replace(R.id.Frame_layout,fragment);
        }
        ft.commit();
   }


}

