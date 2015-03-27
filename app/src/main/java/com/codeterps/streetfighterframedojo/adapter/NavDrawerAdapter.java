package com.codeterps.streetfighterframedojo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    private static OnItemClickListener mListener;
    private ArrayList<NavDrawerItem> mDataset;

    public NavDrawerAdapter(ArrayList<NavDrawerItem> myDataset) {
        this.mDataset = myDataset;
    }

    public NavDrawerItem getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public NavDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_drawer_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(getItem(position).getTitle());
        holder.mImageView.setImageResource(getItem(position).getIcon());
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mListener = clickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public ImageView mImageView;


        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mTextView = (TextView) v.findViewById(R.id.nav_drawer_item_text);
            mImageView = (ImageView) v.findViewById(R.id.nav_drawer_item_image);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v, getAdapterPosition());
        }
    }
}