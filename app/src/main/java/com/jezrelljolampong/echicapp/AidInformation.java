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

public class AidInformation extends AppCompatActivity {

    String key;
    WebView webview;
    String category = "First Aid and Tips";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aid_information);
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

                        new SweetAlertDialog(AidInformation.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Failed!")
                                .setContentText("Bookmark Already Exist")
                                .show();
                    }else{
                        Bookmark _save= new Bookmark(key, category.trim());
                        new SweetAlertDialog(AidInformation.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!")
                                .setContentText("Bookmark Save")
                                .show();
                        _save.save();
                    }
                }catch(Exception ex){
                    Toast.makeText(AidInformation.this, ex.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch (key){

            case "Asses & Clean the Injuries":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/aid/clean.html");
                image_grddetails.setImageResource(R.drawable.clean);
                setTitle(key);
                break;
            case "Infirmary & Recovery Space":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/aid/aid.html");
                image_grddetails.setImageResource(R.drawable.chickenh);
                setTitle(key);
                break;
            case "Isolate":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/aid/isolate.html");
                image_grddetails.setImageResource(R.drawable.care1);
                setTitle(key);
                break;
            case "Food & Water":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/aid/food.html");
                image_grddetails.setImageResource(R.drawable.food2);
                setTitle(key);
                break;
            case "Stop any Bleeding":
                webview = (WebView) findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/aid/bleeding.html");
                image_grddetails.setImageResource(R.drawable.bleed1);
                setTitle(key);
                break;
        }


    }

}
