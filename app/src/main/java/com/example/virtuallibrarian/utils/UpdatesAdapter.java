package com.example.virtuallibrarian.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.virtuallibrarian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesAdapter.BookViewHolder>{


    List<Book> bookList = new ArrayList<Book>();
    SessionManager session;
    private LayoutInflater layoutInflater;
    Context ctx;

    public UpdatesAdapter(Context context,List<Book> bookList)
    {
        ctx = context;
        session = new SessionManager(context);
        this.bookList = session.getUpdateList();
        Log.v("booklist Size",bookList.size()+" ");
        //bookList=session.getBookList();
    }



    public static class BookViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personSurname;
        TextView personAge;
        ImageView personPhoto;

        BookViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.book_name);
            personSurname=(TextView)itemView.findViewById(R.id.book_surname);
            personAge = (TextView)itemView.findViewById(R.id.book_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.book_photo);
        }
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_design, parent, false);
        BookViewHolder pvh = new BookViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        holder.personName.setText(bookList.get(position).title);
        holder.personSurname.setText(bookList.get(position).description);
        //holder.personAge.setText(bookList.get(position).time);
        Log.v("SIES", bookList.get(position).type);

    }

    @Override
    public int getItemCount() {
        if(bookList==null)
            return 0;
        else
            return (int) bookList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
