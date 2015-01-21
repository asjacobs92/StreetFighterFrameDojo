package com.codeterps.streetfighterframedojo.model;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class NavDrawerItem {

    private String title;
    private int icon;

    public NavDrawerItem() {
    }

    public NavDrawerItem(int icon) {
        this.title = "";
        this.icon = icon;
    }

    public NavDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public NavDrawerItem(String title, int icon, boolean isCounterVisible, String count) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
