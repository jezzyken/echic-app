package com.jezrelljolampong.echicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.jezrelljolampong.echicapp.Adapter.MenuAdapter;
import com.jezrelljolampong.echicapp.Model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView listshowrcy;
    List<Menu> menulist = new ArrayList<>();
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Menu();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      //  View header = navigationView.getHeaderView(0);
//        ImageView imageView = header.findViewById(R.id.imageView);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(MainActivity.this, BookmarkActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "The app");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Menu(){
        menulist.add(new Menu("Chicken Information",
                R.drawable.mainchicken));
        menulist.add(new Menu("Disease with Treatment",
                R.drawable.maindisease));
        menulist.add(new Menu("Vitamins and Immunization",
                R.drawable.mainvitamins));
        menulist.add(new Menu("First Aid and Tips",
                R.drawable.mainaid));
        menulist.add(new Menu("Nutrition and Dietaries",
                R.drawable.mainnutrition));
        menulist.add(new Menu("Offline Videos",
                R.drawable.poultry));

        listshowrcy = findViewById(R.id.listshow);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        listshowrcy.setLayoutManager(gridLayoutManager);

        adapter = new MenuAdapter(menulist, MainActivity.this);
        listshowrcy.setAdapter(adapter);
    }

}
