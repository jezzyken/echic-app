package com.jezrelljolampong.echicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jezrelljolampong.echicapp.Model.Disease;
import com.jezrelljolampong.echicapp.Model.Nutrition;
import com.jezrelljolampong.echicapp.NutritionInformation;
import com.jezrelljolampong.echicapp.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.Holderview> {
    private List<Nutrition> menulist;
    private Context context;
    private  String filename;

    public NutritionAdapter(List<Nutrition> menulist, Context context) {
        this.menulist = menulist;
        this.context = context;
    }

    @Override
    public NutritionAdapter.Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.nutrition_item, parent, false);
        return new NutritionAdapter.Holderview(layout);
    }

    @Override
    public void onBindViewHolder(NutritionAdapter.Holderview holder, final int position) {
        holder.v_name.setText(menulist.get(position).getNutritionname());
        holder.v_image.setImageResource(menulist.get(position).getNutritionphoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent (view.getContext(), NutritionInformation.class);
                i.putExtra("key", menulist.get(position).getNutritionname());
                view.getContext().startActivity(i);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Downloading...")
                        .setContentText("Do you want to download PDF File?")
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
//
                                pdfValue(position);
                                CopyAssets();

                                sDialog.dismissWithAnimation();

                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                            }
                        })
                        .show();



                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public void setfilter(List<Nutrition> listitem) {
        menulist = new ArrayList<>();
        menulist.addAll(listitem);
        notifyDataSetChanged();
    }

    class Holderview extends RecyclerView.ViewHolder {
        ImageView v_image;
        TextView v_name;

        Holderview(View itemview) {
            super(itemview);
            v_image = (ImageView) itemview.findViewById(R.id.product_image);
            v_name = (TextView) itemView.findViewById(R.id.product_title);
        }
    }

    private void pdfValue(int articleNum)
    {
        switch (articleNum)
        {
            case  0:
                filename = "scratch.pdf";
                break;

            case  1:
                filename = "grower.pdf";
                break;

            case  2:
                filename = "mash.pdf";
                break;

            case  3:
                filename = "pellets.pdf";
                break;

            case  4:
                filename = "starter.pdf";
                break;
        }
    }

    private void CopyAssets() {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        String foldername = "Download";
        try {
            files = assetManager.list("Files");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

//        for(String filename : files) {


        System.out.println("File name => " + filename);
//            Toast.makeText(getContext(), "" + filename, Toast.LENGTH_SHORT).show();

        Toast.makeText(context, "" + (Environment.getExternalStorageDirectory().toString()   +"/" + filename), Toast.LENGTH_SHORT).show();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("Files/" + filename);   // if files resides inside the "Files" directory itself
            out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() +"/" + filename);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch(Exception e) {
            Log.e("tag", e.getMessage());
        }
//        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}