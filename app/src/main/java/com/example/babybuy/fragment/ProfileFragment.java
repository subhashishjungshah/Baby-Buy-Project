package com.example.babybuy.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.models.LoginWithUser;
import com.example.babybuy.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    TextView fullname, email, usrname,country,gender;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_profile, container, false);
        SessionManagement sessionManagement = new SessionManagement(inflate.getContext());
        int userID = sessionManagement.getSession();

        fullname = inflate.findViewById(R.id.textView5);
        email = inflate.findViewById(R.id.textView6);
        usrname = inflate.findViewById(R.id.usrnam);
        gender = inflate.findViewById(R.id.genderr);
        country = inflate.findViewById(R.id.country);
        DatabaseHelper db = DatabaseHelper.getDB(getContext());
        List<LoginWithUser> userModel = new ArrayList<>();
        userModel = db.userDao().getloginwithuser(userID);
        usrname.setText(userModel.get(0).getLoginModel().getUsername());
        fullname.setText(userModel.get(0).getUserModel().getFullname());
        email.setText(userModel.get(0).getUserModel().getEmail());
        country.setText(userModel.get(0).getUserModel().getCountry());
        gender.setText(userModel.get(0).getUserModel().getGender());





        return inflate;
    }
}