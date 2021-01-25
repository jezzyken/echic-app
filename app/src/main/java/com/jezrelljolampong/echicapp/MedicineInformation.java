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

public class MedicineInformation extends AppCompatActivity {

    String key;
    WebView webview;
    String category = "Vitamins and Immunization";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_information);
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

                        new SweetAlertDialog(MedicineInformation.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Failed!")
                                .setContentText("Bookmark Already Exist")
                                .show();
                    }else{
                        Bookmark _save= new Bookmark(key, category.trim());
                        new SweetAlertDialog(MedicineInformation.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!")
                                .setContentText("Bookmark Save")
                                .show();
                        _save.save();
                    }
                }catch(Exception ex){
                    Toast.makeText(MedicineInformation.this, ex.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch (key){

            case "Amboxy":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/medicine/amboxy.html");
                image_grddetails.setImageResource(R.drawable.amtyl2);
                setTitle(key);
                break;
            case "Ambroxytil":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/medicine/ambroxytil.html");
                image_grddetails.setImageResource(R.drawable.ambroxytil2);
                setTitle(key);
                break;
            case "Ornistat":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/medicine/ornistat.html");
                image_grddetails.setImageResource(R.drawable.ornistat2);
                setTitle(key);
                break;
            case "Tepox":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/medicine/tepox.html");
                image_grddetails.setImageResource(R.drawable.tepox2);
                setTitle(key);
                break;
            case "Zeromite":
                webview = (WebView) findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/medicine/zeromite.html");
                image_grddetails.setImageResource(R.drawable.zeromite2);
                setTitle(key);
                break;
        }

    }

}
