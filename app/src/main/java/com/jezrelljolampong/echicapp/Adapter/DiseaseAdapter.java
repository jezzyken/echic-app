package com.jezrelljolampong.echicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jezrelljolampong.echicapp.BookmarkActivity;
import com.jezrelljolampong.echicapp.DiseaseInformation;
import com.jezrelljolampong.echicapp.Model.Disease;
import com.jezrelljolampong.echicapp.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.Holderview> {
    private List<Disease> menulist;
    private Context context;
    String filename ;

    public DiseaseAdapter(List<Disease> menulist, Context context) {
        this.menulist = menulist;
        this.context = context;
    }

    @Override
    public DiseaseAdapter.Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.disease_item, parent, false);
        return new DiseaseAdapter.Holderview(layout);
    }

    @Override
    public void onBindViewHolder(DiseaseAdapter.Holderview holder, final int position) {
        holder.v_name.setText(menulist.get(position).getDiseasename());
        holder.v_image.setImageResource(menulist.get(position).getDiseasephoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent (view.getContext(), DiseaseInformation.class);
                i.putExtra("key", menulist.get(position).getDiseasename());
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

    public void setfilter(List<Disease> listitem) {
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
                filename = "coccidiosis.pdf";
                break;

            case  1:
                filename = "colibacillosis.pdf";
                break;

            case  2:
                filename = "cholera.pdf";
                break;

            case  3:
                filename = "typhoid.pdf";
                break;

            case  4:
                filename = "mycoplasmosis.pdf";
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