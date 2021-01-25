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
import com.jezrelljolampong.echicapp.Adapter.MedicineAdapter;
import com.jezrelljolampong.echicapp.Model.Disease;
import com.jezrelljolampong.echicapp.Model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listshowrcy;
    List<Medicine> medicinelist = new ArrayList<>();
    MedicineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        setTitle("Vitamins");
        MedicineCategory();

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
                final  List<Medicine> filtermodelist=filter(medicinelist,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
        return true;
    }
    private List<Medicine> filter(List<Medicine> pl,String query)
    {
        query=query.toLowerCase();
        final List<Medicine> filteredModeList=new ArrayList<>();
        for (Medicine model:pl)
        {
            final String text=model.getMedicinename().toLowerCase();
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

    public void MedicineCategory(){
        medicinelist.add(new Medicine("Amboxy",
                R.drawable.amtyl));
        medicinelist.add(new Medicine("Ambroxytil",
                R.drawable.ambroxytil));
        medicinelist.add(new Medicine("Ornistat",
                R.drawable.ornistat));
        medicinelist.add(new Medicine("Tepox",
                R.drawable.tepox));
        medicinelist.add(new Medicine("Zeromite",
                R.drawable.zeromite));

        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new MedicineAdapter(medicinelist, MedicineActivity.this);
        listshowrcy.setAdapter(adapter);
    }
}
