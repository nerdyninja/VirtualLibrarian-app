package com.example.virtuallibrarian.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.virtuallibrarian.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder1>{


    List<BookCard> bookCardList = new ArrayList<BookCard>();
    SessionManager sessionManager;
    private LayoutInflater layoutInflater;
    Context ctx;
    private Request request;
    String responseString;

    public BooksAdapter(Context context,List<Book> bookList)
    {
        ctx = context;
        sessionManager = new SessionManager(context);
        this.bookCardList = sessionManager.getBookList();
        Log.v("booklist Size",bookList.size()+" ");
        //bookList=session.getBookList();
    }



    public static class BookViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView bookTitle;
        TextView bookDescription;
        TextView bookAuthor;
        TextView bookPublisher;
        TextView bookAge;
        ItemClickListener itemClickListener;

        BookViewHolder1(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            bookTitle = (TextView)itemView.findViewById(R.id.book_design_name);
            bookDescription=(TextView)itemView.findViewById(R.id.book_design_surname);
            bookAuthor = (TextView)itemView.findViewById(R.id.author);
            bookPublisher = (TextView) itemView.findViewById(R.id.publisher);
            bookAge = (TextView)itemView.findViewById(R.id.book_design_age);

            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }


        public void setItemClickListener(ItemClickListener i) {
            this.itemClickListener = i;
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
        holder.bookAge.setText(bookCardList.get(position).dateToDisplay);
        Log.v("tag","tag");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                final String isbn =bookCardList.get(pos).getIsbn();
                final String prn = sessionManager.getKeyPrn();
                AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
                alert.setMessage("Are you sure you want to reserve this book?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.v("tag1","tag1");

                                post(isbn,prn);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Log.v("tag2","tag2");

                            }
                        });
            }
        });

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

    public void post(String isbn,String prn)
    {
        Log.v("tag3","tag3");
        String url = "http://192.168.43.220/apis/reserve.php";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("isbn",isbn)
                .add("prn",prn)
                .build();
        request = new Request.Builder()
                .url(url)
                .method("POST",body.create(null,new byte[0]))
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("error","error");
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseString = response.body().string();
                Log.v("response", responseString);
                try {
                    JSONObject root = new JSONObject(responseString);
                    // All the JSON Parsing magic.
                    String status = root.optString("status");
                    if(status.equalsIgnoreCase("true"))
                    {

                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
