package com.example.babybuy.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class PurchasewithLatlong {
    @Embedded
    PurchaseList purchaseList;

    @Relation(parentColumn = "pid", entityColumn = "pid", entity = PurchaseList.class)
    public LoginModel loginModel;

    public PurchasewithLatlong(PurchaseList purchaseList, LoginModel loginModel) {
        this.purchaseList = purchaseList;
        this.loginModel = loginModel;
    }

    public PurchasewithLatlong() {
    }

    public PurchaseList getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(PurchaseList purchaseList) {
        this.purchaseList = purchaseList;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }
}
