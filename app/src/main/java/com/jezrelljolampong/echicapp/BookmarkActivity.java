package com.jezrelljolampong.echicapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

import com.jezrelljolampong.echicapp.Adapter.BookmarkAdapter;
import com.jezrelljolampong.echicapp.Model.Bookmark;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BookmarkActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    BookmarkAdapter adapter;
    List<Bookmark> bookmarlist = new ArrayList<>();

    long initialCount;

    int modifyPos = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        setTitle("Bookmark");

        recyclerView = (RecyclerView) findViewById(R.id.bookmark_list);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        recyclerView.setLayoutManager(gridLayoutManager);

        initialCount = Bookmark.count(Bookmark.class);

        if (savedInstanceState != null)
            modifyPos = savedInstanceState.getInt("modify");

        if (initialCount >= 0) {

            bookmarlist = Bookmark.listAll(Bookmark.class);
            adapter = new BookmarkAdapter(BookmarkActivity.this, bookmarlist);
            recyclerView.setAdapter(adapter);

            if (bookmarlist.isEmpty())
                Snackbar.make(recyclerView, "No Bookmarks added.", Snackbar.LENGTH_LONG).show();
        }
        adapter.SetOnItemClickListener(new BookmarkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i;
                switch(bookmarlist.get(position).getCategory()){
                    case "Disease with Treatment":
                        i = new Intent(BookmarkActivity.this, DiseaseInformation.class);
                        i.putExtra("key", bookmarlist.get(position).getName());
                        startActivity(i);
                        break;
                    case "Vitamins and Immunization":
                        i = new Intent(BookmarkActivity.this, MedicineInformation.class);
                        i.putExtra("key", bookmarlist.get(position).getName());
                        startActivity(i);
                        break;
                    case "First Aid and Tips":
                        i = new Intent(BookmarkActivity.this, AidInformation.class);
                        i.putExtra("key", bookmarlist.get(position).getName());
                        startActivity(i);
                        break;
                    case "Nutrition and Dietaries":
                        i = new Intent(BookmarkActivity.this, NutritionInformation.class);
                        i.putExtra("key", bookmarlist.get(position).getName());
                        startActivity(i);
                        break;
                    case "Chicken Information":
                        i = new Intent(BookmarkActivity.this, ChickenActivity.class);
                        i.putExtra("key", bookmarlist.get(position).getName());
                        startActivity(i);
                        break;
                }

            }

        });



        // Handling swipe to delete
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Remove swiped item from list and notify the RecyclerView

                final int position = viewHolder.getAdapterPosition();
                final Bookmark note = bookmarlist.get(viewHolder.getAdapterPosition());
                bookmarlist.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(position);

                note.delete();
                initialCount -= 1;

                new SweetAlertDialog(BookmarkActivity.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Success!")
                        .setContentText("Bookmark Removed")
                        .show();
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }


    /**
     * RecyclerView: Implementing single item click and long press (Part-II)
     *
     * - creating an Interface for single tap and long press
     * - Parameters are its respective view and its position
     * */

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

    /**
     * RecyclerView: Implementing single item click and long press (Part-II)
     *
     * - creating an innerclass implementing RevyvlerView.OnItemTouchListener
     * - Pass clickListener interface as parameter
     * */

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("modify", modifyPos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        modifyPos = savedInstanceState.getInt("modify");
    }

    @Override
    protected void onResume() {
        super.onResume();

        final long newCount = Bookmark.count(Bookmark.class);

        if (newCount > initialCount) {

            Bookmark _subject = Bookmark.last(Bookmark.class);

            bookmarlist.add(_subject);
            adapter.notifyItemInserted((int) newCount);

            initialCount = newCount;
        }

        try{
            if (modifyPos != -1) {

                bookmarlist.set(modifyPos, Bookmark.listAll(Bookmark.class).get(modifyPos));
                adapter.notifyItemChanged(modifyPos);
            }
        }catch(Exception e){

        }

    }

}
