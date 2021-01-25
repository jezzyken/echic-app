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

import com.jezrelljolampong.echicapp.Adapter.AidAdapter;
import com.jezrelljolampong.echicapp.Adapter.DiseaseAdapter;
import com.jezrelljolampong.echicapp.Model.Aid;
import com.jezrelljolampong.echicapp.Model.Disease;

import java.util.ArrayList;
import java.util.List;

public class AidActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listshowrcy;
    List<Aid> aidlist = new ArrayList<>();
    AidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aid);
        setTitle("First Aid and Tips");
        AidCategory();

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
                final  List<Aid> filtermodelist=filter(aidlist,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
        return true;
    }
    private List<Aid> filter(List<Aid> pl,String query)
    {
        query=query.toLowerCase();
        final List<Aid> filteredModeList=new ArrayList<>();
        for (Aid model:pl)
        {
            final String text=model.getAidname().toLowerCase();
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

    public void AidCategory(){
        aidlist.add(new Aid("Asses & Clean the Injuries",
                R.drawable.clean));
        aidlist.add(new Aid("Infirmary & Recovery Space",
                R.drawable.care1));
        aidlist.add(new Aid("Isolate",
                R.drawable.chickenh));
        aidlist.add(new Aid("Food & Water",
                R.drawable.food2));
        aidlist.add(new Aid("Stop any Bleeding",
                R.drawable.bleed1));

        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new AidAdapter(aidlist, AidActivity.this);
        listshowrcy.setAdapter(adapter);
    }

}
