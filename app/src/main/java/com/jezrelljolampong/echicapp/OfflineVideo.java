package com.jezrelljolampong.echicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jezrelljolampong.echicapp.Adapter.MenuAdapter;
import com.jezrelljolampong.echicapp.Adapter.OfflineVideoAdapter;
import com.jezrelljolampong.echicapp.Model.Menu;

import java.util.ArrayList;
import java.util.List;

public class OfflineVideo extends AppCompatActivity {

    RecyclerView listshowrcy;
    List<Menu> menulist = new ArrayList<>();
    OfflineVideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_video);
        setTitle("Offline Video");
        Menu();
    }

    public void Menu(){
        menulist.add(new Menu("How to Start Chicken Farming",
                R.drawable.poultry));
        menulist.add(new Menu("Modern Poultry",
                R.drawable.poultry2));
        menulist.add(new Menu("Chicken Cage",
                R.drawable.poultry1));

        listshowrcy = findViewById(R.id.listshow);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        listshowrcy.setLayoutManager(gridLayoutManager);

        adapter = new OfflineVideoAdapter(menulist, OfflineVideo.this);
        listshowrcy.setAdapter(adapter);
    }

}
