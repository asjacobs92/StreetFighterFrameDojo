package com.codeterps.streetfighterframedojo.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.activity.CharacterDetailsActivity;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.util.MediaUtils;

import java.util.List;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {

    private Context mContext;
    private List<Character> mDataset;

    public CharacterListAdapter(Context c, List<Character> myDataset) {
        mContext = c;
        mDataset = myDataset;
    }

    @Override
    public CharacterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(getOnCharacterClickListener(position));
        holder.mTextView.setText(getItem(position).getCharacterName());
        holder.mImageView.setImageDrawable(MediaUtils.getDrawableFromAssets(mContext,
                getItem(position).getCharacterImagePath()));
    }

    private View.OnClickListener getOnCharacterClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CharacterDetailsActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(CharacterDetailsActivity.ARG_CHARACTER, getItem(position));
                intent.putExtras(args);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) mContext,
                                Pair.create(v.findViewById(R.id.character_item_name), "character_item_name"),
                                Pair.create(v.findViewById(R.id.character_item_image), "character_item_image"));
                mContext.startActivity(intent, options.toBundle());
            }
        };
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Character getItem(int position) {
        return mDataset.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.character_item_name);
            mImageView = (ImageView) v.findViewById(R.id.character_item_image);
        }

    }
}
