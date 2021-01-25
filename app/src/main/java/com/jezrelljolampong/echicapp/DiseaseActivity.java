package com.jezrelljolampong.echicapp;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jezrelljolampong.echicapp.Adapter.DiseaseAdapter;
import com.jezrelljolampong.echicapp.Model.Disease;

import java.util.ArrayList;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listshowrcy;
    List<Disease> diseaselist = new ArrayList<>();
    DiseaseAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        DiseaseCategory();

        setTitle("Disease with Treatment");
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
                final  List<Disease> filtermodelist=filter(diseaselist,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
        return true;
    }
    private List<Disease> filter(List<Disease> pl,String query)
    {
        query=query.toLowerCase();
        final List<Disease> filteredModeList=new ArrayList<>();
        for (Disease model:pl)
        {
            final String text=model.getDiseasename().toLowerCase();
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

    public void DiseaseCategory(){
        diseaselist.add(new Disease("Coccidiosis",
                R.drawable.coccidiosis));
        diseaselist.add(new Disease("Colibacillosis",
                R.drawable.colibacillosis));
        diseaselist.add(new Disease("Fowl Cholera",
                R.drawable.fowl_cholera));
        diseaselist.add(new Disease("Fowl Typhoid",
                R.drawable.fowl_typhoid));
        diseaselist.add(new Disease("Mycoplasmosis",
                R.drawable.mycoplasmosis));


        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new DiseaseAdapter(diseaselist, DiseaseActivity.this);
        listshowrcy.setAdapter(adapter);
    }


}
