package com.jezrelljolampong.echicapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jezrelljolampong.echicapp.Model.Bookmark;
import com.jezrelljolampong.echicapp.Model.Favorite;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkButtonBuilder;
import com.varunest.sparkbutton.SparkEventListener;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DiseaseInformation extends AppCompatActivity {


    String key;
    String category = "Disease with Treatment";

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        key = getIntent().getStringExtra("key");
        ImageView image_grddetails = findViewById(R.id.image_grddetails);

//        SparkButton sparkButton = findViewById(R.id.spark_button);
//
//        sparkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playHeartAnimation(v);
//            }
//        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    List<Bookmark> book = Bookmark.find(Bookmark.class, "name = ?", key);

                    if (book.size() > 0){

                        new SweetAlertDialog(DiseaseInformation.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Failed!")
                                .setContentText("Bookmark Already Exist")
                                .show();
                    }else{
                        Bookmark _save= new Bookmark(key, category.trim());
                        new SweetAlertDialog(DiseaseInformation.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!")
                                .setContentText("Bookmark Save")
                                .show();
                        _save.save();
                    }
                }catch(Exception ex){
                    Toast.makeText(DiseaseInformation.this, ex.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch (key){

            case "Coccidiosis":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/disease/Coccidiosis.html");
                image_grddetails.setImageResource(R.drawable.coccidiosis);
                setTitle(key);
                break;
            case "Colibacillosis":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/disease/Colibacillosis.html");
                image_grddetails.setImageResource(R.drawable.colibacillosis);
                setTitle(key);
                break;
            case "Fowl Cholera":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/disease/Fowl%20Typhoid.html");
                image_grddetails.setImageResource(R.drawable.fowl_cholera);
                setTitle(key);
                break;
            case "Fowl Typhoid":
                webview = findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/disease/Fowl%20Typhoid.html");
                image_grddetails.setImageResource(R.drawable.fowl_typhoid);
                setTitle(key);
                break;
            case "Mycoplasmosis":
                webview = (WebView) findViewById(R.id.web);
                webview.loadUrl("file:///android_asset/disease/Mycoplasmosis.html");
                image_grddetails.setImageResource(R.drawable.mycoplasmosis);
                setTitle(key);
                break;
        }

    }

//    private void playHeartAnimation(final View heartLayout) {
//        ((SparkButton) heartLayout.findViewById(R.id.spark_button)).setChecked(false);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ((SparkButton) heartLayout.findViewById(R.id.spark_button)).setChecked(true);
//                ((SparkButton) heartLayout.findViewById(R.id.spark_button)).playAnimation();
//            }
//        }, 300);
//    }

}
