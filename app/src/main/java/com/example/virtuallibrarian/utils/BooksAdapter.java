package com.example.virtuallibrarian.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.virtuallibrarian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder1>{


    List<BookCard> bookCardList = new ArrayList<BookCard>();
    SessionManager sessionManager;
    private LayoutInflater layoutInflater;
    Context ctx;

    public BooksAdapter(Context context,List<Book> bookList)
    {
        ctx = context;
        sessionManager = new SessionManager(context);
        this.bookCardList = sessionManager.getBookList();
        Log.v("booklist Size",bookList.size()+" ");
        //bookList=session.getBookList();
    }



    public static class BookViewHolder1 extends RecyclerView.ViewHolder {
        CardView cv;
        TextView bookTitle;
        TextView bookDescription;
        TextView bookAuthor;
        TextView bookPublisher;

        BookViewHolder1(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            bookTitle = (TextView)itemView.findViewById(R.id.book_design_name);
            bookDescription=(TextView)itemView.findViewById(R.id.book_design_surname);
            bookAuthor = (TextView)itemView.findViewById(R.id.author);
            bookPublisher = (TextView) itemView.findViewById(R.id.publisher);
        }
    }

    @Override
    public BookViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_design, parent, false);
        BookViewHolder1 pvh = new BookViewHolder1(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(BookViewHolder1 holder, int position) {

        holder.bookTitle.setText(bookCardList.get(position).title);
        holder.bookDescription.setText(bookCardList.get(position).description);
        holder.bookAuthor.setText(bookCardList.get(position).author);
        holder.bookPublisher.setText(bookCardList.get(position).publisher);
//        Log.v("SIES", bookCardList.get(position).categories);
    }

    @Override
    public int getItemCount() {
        if(bookCardList==null)
            return 0;
        else
            return (int) bookCardList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
