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

public class ChickenActivity extends AppCompatActivity {


    String category = "Chicken Information";
    String key = "Chicken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Chicken Information");

        FloatingActionButton fab = findViewById(R.id.fab);
        ImageView image_grddetails = findViewById(R.id.image_grddetails);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    List<Bookmark> book = Bookmark.find(Bookmark.class, "name = ?", key);

                    if (book.size() > 0){

                        new SweetAlertDialog(ChickenActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Failed!")
                                .setContentText("Bookmark Already Exist")
                                .show();
                    }else{
                        Bookmark _save= new Bookmark(key, category.trim());
                        new SweetAlertDialog(ChickenActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!")
                                .setContentText("Bookmark Save")
                                .show();
                        _save.save();
                    }
                }catch(Exception ex){
                    Toast.makeText(ChickenActivity.this, ex.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }
        });


        image_grddetails.setImageResource(R.drawable.care1);


        WebView webview = (WebView) findViewById(R.id.web);
        webview.loadUrl("file:///android_asset/chicken/chicken.html");
    }
}
