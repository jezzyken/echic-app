package com.jezrelljolampong.echicapp.Model;

public class Nutrition {

    public String nutritionname;
    public int nutritionphoto;

    public Nutrition(String nutritionname, int nutritionphoto) {
        this.nutritionname = nutritionname;
        this.nutritionphoto = nutritionphoto;
    }

    public String getNutritionname() {
        return nutritionname;
    }

    public void setNutritionname(String nutritionname) {
        this.nutritionname = nutritionname;
    }

    public int getNutritionphoto() {
        return nutritionphoto;
    }

    public void setNutritionphoto(int nutritionphoto) {
        this.nutritionphoto = nutritionphoto;
    }
}
