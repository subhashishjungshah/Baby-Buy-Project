package com.example.babybuy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "latlongtbl")
public class LatLongModel {
    @ColumnInfo(name = "lid")
    @PrimaryKey (autoGenerate = true)
    private int lid;
    @ColumnInfo(name = "latitude")
    private String Latitute;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "pid")
    private int pid;

    public LatLongModel(int lid, String latitute, String longitude, int pid) {
        this.lid = lid;
        Latitute = latitute;
        this.longitude = longitude;
        this.pid = pid;
    }

    public LatLongModel(String latitute, String longitude, int pid) {
        Latitute = latitute;
        this.longitude = longitude;
        this.pid = pid;
    }

    public LatLongModel() {
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLatitute() {
        return Latitute;
    }

    public void setLatitute(String latitute) {
        Latitute = latitute;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
