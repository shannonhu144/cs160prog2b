package com.example.shann.proj2;

import java.io.Serializable;

public class Bill implements Serializable {
    private String date;
    private String name;
    private String number;
    private String url;
    private int drawableId;
    public Bill(String n, String d, String number, String url) {
        date = d;
        name = n;
        this.number = number;
        this.url = url;
        drawableId = R.drawable.expand;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getNumber() {
        return number;
    }

    public String getUrl() {
        return url;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    @Override
    public String toString() {
        return "Number: " + number + " Name:" + name + " Date: " + date;
    }
}
