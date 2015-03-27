package com.codeterps.streetfighterframedojo.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.activity.MoveDetailsActivity;
import com.codeterps.streetfighterframedojo.model.Move;

import java.util.List;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.ViewHolder> {

    private Context mContext;

    private List<Move> mDataset;

    public MoveListAdapter(Context context, List<Move> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MoveListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.move_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(getItem(position).getName());
        holder.itemView.setOnClickListener(getOnMoveClickListener(position));
    }

    private View.OnClickListener getOnMoveClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoveDetailsActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(MoveDetailsActivity.ARG_MOVE, getItem(position));
                intent.putExtras(args);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) mContext);
                mContext.startActivity(intent, options.toBundle());
            }
        };
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Move getItem(int position) {
        return mDataset.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.move_item_name);
        }

    }
}
