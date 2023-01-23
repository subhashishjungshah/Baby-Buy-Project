package com.example.babybuy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.babybuy.Acitivity.Clothes;
import com.example.babybuy.Acitivity.Diapher;
import com.example.babybuy.Acitivity.MilkItems;
import com.example.babybuy.Acitivity.Myproducts;
import com.example.babybuy.R;
import com.example.babybuy.Acitivity.Toys;
import com.example.babybuy.Acitivity.Travelling;

public class HomeFragment extends Fragment implements View.OnClickListener {
    CardView diaper,toys,clothes,travelling,milkItems, myproducts;
    TextView txtview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        diaper = (CardView) inflate.findViewById(R.id.diaperr);
        toys = (CardView) inflate.findViewById(R.id.toys);
        clothes = (CardView) inflate.findViewById(R.id.clothes);
        travelling = (CardView) inflate.findViewById(R.id.travelling);
        milkItems = (CardView) inflate.findViewById(R.id.milkItems);
        myproducts = (CardView) inflate.findViewById(R.id.myproducts);
        // adding events in grid buttons
        diaper.setOnClickListener(this);
        clothes.setOnClickListener(this);
        travelling.setOnClickListener(this);
        milkItems.setOnClickListener(this);
        toys.setOnClickListener(this);
        myproducts.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.diaperr:
                startActivity(new Intent(getContext(), Diapher.class));
                break;
            case R.id.toys:
                startActivity(new Intent(getContext(), Toys.class));
                break;
            case R.id.travelling:
                startActivity(new Intent(getContext(), Travelling.class));
                break;
            case R.id.clothes:
                startActivity(new Intent(getContext(), Clothes.class));
                break;
            case R.id.milkItems:
                startActivity(new Intent(getContext(), MilkItems.class));
                break;
            case R.id.myproducts:
                startActivity(new Intent(getContext(), Myproducts.class));
                break;
        }

    }
}
