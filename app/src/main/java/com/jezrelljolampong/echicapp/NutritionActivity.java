package com.jezrelljolampong.echicapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jezrelljolampong.echicapp.Adapter.DiseaseAdapter;
import com.jezrelljolampong.echicapp.Adapter.NutritionAdapter;
import com.jezrelljolampong.echicapp.Model.Disease;
import com.jezrelljolampong.echicapp.Model.Medicine;
import com.jezrelljolampong.echicapp.Model.Nutrition;

import java.util.ArrayList;
import java.util.List;

public class NutritionActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listshowrcy;
    List<Nutrition> nutritionlist = new ArrayList<>();
    NutritionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        setTitle("Nutrition and Dietaries");
        NutritionCategory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                final  List<Nutrition> filtermodelist=filter(nutritionlist, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
        return true;
    }
    private List<Nutrition> filter(List<Nutrition> pl,String query)
    {
        query=query.toLowerCase();
        final List<Nutrition> filteredModeList=new ArrayList<>();
        for (Nutrition model:pl)
        {
            final String text=model.getNutritionname().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    public void NutritionCategory(){
        nutritionlist.add(new Nutrition("Chicken Scartch",
                R.drawable.scratch));
        nutritionlist.add(new Nutrition("Grower Chicken Feed",
                R.drawable.pellets));
        nutritionlist.add(new Nutrition("Mash",
                R.drawable.scratch));
        nutritionlist.add(new Nutrition("Pellets",
                R.drawable.pellets));
        nutritionlist.add(new Nutrition("Starter Chicken Feed",
                R.drawable.scratch));

        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new NutritionAdapter(nutritionlist, NutritionActivity.this);
        listshowrcy.setAdapter(adapter);
    }
}
