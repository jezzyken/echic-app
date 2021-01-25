package com.jezrelljolampong.echicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jezrelljolampong.echicapp.ChickenActivity;
import com.jezrelljolampong.echicapp.DiseaseActivity;
import com.jezrelljolampong.echicapp.MedicineActivity;
import com.jezrelljolampong.echicapp.Model.Menu;
import com.jezrelljolampong.echicapp.R;
import com.jezrelljolampong.echicapp.SampleActivity;

import java.util.ArrayList;
import java.util.List;

public class OfflineVideoAdapter extends RecyclerView.Adapter<OfflineVideoAdapter.Holderview> {
    private List<Menu> menulist;
    private Context context;

    public OfflineVideoAdapter(List<Menu> menulist, Context context) {
        this.menulist = menulist;
        this.context = context;
    }

    @Override
    public OfflineVideoAdapter.Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.video_item, parent, false);
        return new OfflineVideoAdapter.Holderview(layout);
    }

    @Override
    public void onBindViewHolder(OfflineVideoAdapter.Holderview holder, final int position) {
        holder.v_name.setText(menulist.get(position).getName());
        holder.v_image.setImageResource(menulist.get(position).getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (menulist.get(position).getName().equals("How to Start Chicken Farming")){
                    Intent i = new Intent (view.getContext(), SampleActivity.class);
                    i.putExtra("key", menulist.get(position).getName());
                    view.getContext().startActivity(i);
                }else if (menulist.get(position).getName().equals("Modern Poultry")){
                    Intent i = new Intent (view.getContext(), SampleActivity.class);
                    i.putExtra("key", "Modern Poultry");
                    view.getContext().startActivity(i);
                }
                else if (menulist.get(position).getName().equals("Chicken Cage")){
                    Intent i = new Intent (view.getContext(), SampleActivity.class);
                    i.putExtra("key", "Chicken Cage");
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