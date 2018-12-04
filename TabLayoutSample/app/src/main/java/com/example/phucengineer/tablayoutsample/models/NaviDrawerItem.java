package com.example.phucengineer.tablayoutsample.models;

/*
 * Created by Phuc Engineer on 9/25/2018.
 */
public class NaviDrawerItem {
    private boolean showNotify;
    private String title;


    public NaviDrawerItem() {

    }

    public NaviDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
