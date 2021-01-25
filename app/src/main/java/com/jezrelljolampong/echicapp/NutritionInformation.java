package com.jezrelljolampong.echicapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jezrelljolampong.echicapp.Model.Bookmark;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NutritionInformation extends AppCompatActivity {

    String key;
    WebView webview;
    String category = "Nutrition and Dietaries";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        key = getIntent().getStringExtra("key");
        ImageView image_grddetails = findViewById(R.id.image_grddetails);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    List<Bookmark> book = Bookmark.find(Bookmark.class, "name = ?", key);

                    if (book.size() > 0){

                        new SweetAlertDialog(NutritionInformation.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Failed!")
                                .setContentText("Bookmark Already Exist")
                                .show();
                    }else{
                        Bookmark _save= new Bookmark(key, category.trim());
                        new SweetAlertDialog(NutritionInformation.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!")
                                .setContentText("Bookmark Save")
                                .show();
                        _save.save();
                    }
                }catch(Exception ex){
                    Toast.makeText(NutritionInformation.this, ex.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch (key){

            case "Chicken Scartch":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/nutrition/scratch.html");
                image_grddetails.setImageResource(R.drawable.scratch);
                setTitle(key);
                break;
            case "Grower Chicken Feed":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/nutrition/grower.html");
                image_grddetails.setImageResource(R.drawable.pellets);
                setTitle(key);
                break;
            case "Mash":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/nutrition/mash.html");
                image_grddetails.setImageResource(R.drawable.scratch);
                setTitle(key);
                break;
            case "Pellets":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/nutrition/pellets.html");
                image_grddetails.setImageResource(R.drawable.pellets);
                setTitle(key);
                break;
            case "Starter Chicken Feed":
                webview = (WebView) findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/nutrition/starter.html");
                image_grddetails.setImageResource(R.drawable.scratch);
                setTitle(key);
                break;
        }
    }

}
