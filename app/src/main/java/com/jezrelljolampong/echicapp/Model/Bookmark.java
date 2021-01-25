package com.jezrelljolampong.echicapp.Model;

import com.orm.SugarRecord;

public class Bookmark extends SugarRecord {
    public String name;
    public String category;

    public Bookmark(){

    }

    public Bookmark(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
