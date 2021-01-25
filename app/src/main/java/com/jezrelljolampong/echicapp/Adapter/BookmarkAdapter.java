package com.jezrelljolampong.echicapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jezrelljolampong.echicapp.Model.Bookmark;
import com.jezrelljolampong.echicapp.R;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.NoteVH> {
    Context context;
    List<Bookmark> notes;

    BookmarkAdapter.OnItemClickListener clickListener;

    public BookmarkAdapter(Context context, List<Bookmark> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public BookmarkAdapter.NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item, parent, false);
        BookmarkAdapter.NoteVH viewHolder = new BookmarkAdapter.NoteVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookmarkAdapter.NoteVH holder, int position) {

        holder.bookmark.setText(notes.get(position).getName());
        holder.category.setText(notes.get(position).getCategory());
    }

    public int getItemCount() {
        return notes.size();
    }

    class NoteVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bookmark, category;

        public NoteVH(View itemView) {
            super(itemView);

            bookmark = itemView.findViewById(R.id.bookmarkname);
            category = itemView.findViewById(R.id.categoryname);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final BookmarkAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
