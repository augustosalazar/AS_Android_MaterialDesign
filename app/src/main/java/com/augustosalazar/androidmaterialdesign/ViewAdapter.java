package com.augustosalazar.androidmaterialdesign;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>  {

    private LayoutInflater inflater;
    private List<Information> data = Collections.emptyList();

    public ViewAdapter(Context context,List<Information> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_row,parent,false);// obtenemos el parent, linearLayout
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        Information current = data.get(i);
        holder.mTextViewTitle.setText(current.title);
        holder. mImageView.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewTitle;
        ImageView mImageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.listText);
            mImageView = (ImageView) itemView.findViewById(R.id.listIcon);


        }
    }
}
