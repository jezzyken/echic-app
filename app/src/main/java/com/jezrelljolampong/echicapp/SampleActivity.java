package com.jezrelljolampong.echicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.jezrelljolampong.echicapp.Model.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;


    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);



        key = getIntent().getStringExtra("key");

        setTitle(key);

        if (key.equals("How to Start Chicken Farming")){
            videoView = (VideoView) findViewById(R.id.videoView);
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a1);
            mediaController = new MediaController(SampleActivity.this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
        }else if (key.equals("Modern Poultry")){
            videoView = (VideoView) findViewById(R.id.videoView);
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a2);
            mediaController = new MediaController(SampleActivity.this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
        }else if (key.equals("Chicken Cage")){
            videoView = (VideoView) findViewById(R.id.videoView);
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a3);
            mediaController = new MediaController(SampleActivity.this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();

        }
    }
}
