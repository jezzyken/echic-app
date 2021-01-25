package com.jezrelljolampong.echicapp.Model;

import com.orm.SugarRecord;

public class Favorite extends SugarRecord {
    public String favoritename;
    public String categoryname;

    public Favorite(String favoritename, String categoryname) {
        this.favoritename = favoritename;
        this.categoryname = categoryname;
    }

    public String getFavoritename() {
        return favoritename;
    }

    public void setFavoritename(String favoritename) {
        this.favoritename = favoritename;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
