package com.jezrelljolampong.echicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jezrelljolampong.echicapp.AidActivity;
import com.jezrelljolampong.echicapp.ChickenActivity;
import com.jezrelljolampong.echicapp.DiseaseActivity;
import com.jezrelljolampong.echicapp.MedicineActivity;
import com.jezrelljolampong.echicapp.Model.Medicine;
import com.jezrelljolampong.echicapp.Model.Menu;
import com.jezrelljolampong.echicapp.Model.Nutrition;
import com.jezrelljolampong.echicapp.NutritionActivity;
import com.jezrelljolampong.echicapp.OfflineVideo;
import com.jezrelljolampong.echicapp.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Holderview> {
    private List<Menu> menulist;
    private Context context;

    public MenuAdapter(List<Menu> menulist, Context context) {
        this.menulist = menulist;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.menu_item, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {
        holder.v_name.setText(menulist.get(position).getName());
        holder.v_image.setImageResource(menulist.get(position).getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (menulist.get(position).getName().equals("Chicken Information")){
                   Intent i = new Intent (view.getContext(), ChickenActivity.class);
                   view.getContext().startActivity(i);
               }else if (menulist.get(position).getName().equals("Disease with Treatment")){
                   Intent i = new Intent (view.getContext(), DiseaseActivity.class);
                   view.getContext().startActivity(i);
               }
               else if (menulist.get(position).getName().equals("Vitamins and Immunization")){
                   Intent i = new Intent (view.getContext(), MedicineActivity.class);
                   view.getContext().startActivity(i);
               }
               else if (menulist.get(position).getName().equals("First Aid and Tips")){
                   Intent i = new Intent (view.getContext(), AidActivity.class);
                   view.getContext().startActivity(i);
               }
               else if (menulist.get(position).getName().equals("Nutrition and Dietaries")){
                   Intent i = new Intent (view.getContext(), NutritionActivity.class);
                   view.getContext().startActivity(i);
               }
               else if (menulist.get(position).getName().equals("Offline Videos")){
                   Intent i = new Intent (view.getContext(), OfflineVideo.class);
                   view.getContext().startActivity(i);
               }

            }
        });
    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public void setfilter(List<Menu> listitem) {
        menulist = new ArrayList<>();
        menulist.addAll(listitem);
        notifyDataSetChanged();
    }

    class Holderview extends RecyclerView.ViewHolder {
        ImageView v_image;
        TextView v_name;

        Holderview(View itemview) {
            super(itemview);
            v_image = (ImageView) itemview.findViewById(R.id.picture_name);
            v_name = (TextView) itemView.findViewById(R.id.menu_name);
        }
    }
}