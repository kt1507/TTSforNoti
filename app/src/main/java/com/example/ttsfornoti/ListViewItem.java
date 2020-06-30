package com.example.ttsfornoti;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable iconDrawable;
    private String titleStr;

    public void setTitle(String title){
        titleStr = title;
    }
    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }

    public Drawable getIcon(){
        return this.iconDrawable;
    }
    public String getTitle(){
        return this.titleStr;
    }
}
