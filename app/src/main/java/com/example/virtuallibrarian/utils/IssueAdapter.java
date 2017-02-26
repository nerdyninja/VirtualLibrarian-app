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
 * Created by rohitramaswamy on 26/02/17.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {


    List<IssueCard> issueCardList = new ArrayList<IssueCard>();
    SessionManager session;
    private LayoutInflater layoutInflater;
    Context ctx;

    public IssueAdapter(Context context,List<IssueCard> issueCardList)
    {
        ctx = context;
        session = new SessionManager(context);
        this.issueCardList = session.getIssueList();
        Log.v("booklist Size",issueCardList.size()+" ");
    }

    public static class IssueViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personSurname;
        TextView personAge;
        ImageView personPhoto;

        IssueViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.book_name);
            personSurname=(TextView)itemView.findViewById(R.id.book_surname);
            personAge = (TextView)itemView.findViewById(R.id.book_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.book_photo);
        }
    }



    @Override
    public IssueAdapter.IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_design, parent, false);
        IssueAdapter.IssueViewHolder pvh = new IssueAdapter.IssueViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(IssueAdapter.IssueViewHolder holder, int position) {

        holder.personName.setText(issueCardList.get(position).title);
        holder.personSurname.setText(issueCardList.get(position).created_at);
        //holder.personAge.setText(bookList.get(position).time);
        Log.v("SIES", /*issueCardList.get(position).type*/"ksk");

    }

    @Override
    public int getItemCount() {
        if(issueCardList==null)
            return 0;
        else
            return (int) issueCardList.size();
    }

}
