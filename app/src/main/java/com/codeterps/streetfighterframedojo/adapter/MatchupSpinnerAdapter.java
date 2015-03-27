package com.codeterps.streetfighterframedojo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.util.MediaUtils;

import java.util.ArrayList;

/**
 * Created by Arthur Jacobs on 3/26/2015.
 */
public class MatchupSpinnerAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Character> mDataset = new ArrayList<>();

    public MatchupSpinnerAdapter(Context context, ArrayList<Character> dataset) {
        mDataset = dataset;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Character getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = getLayoutInflater().inflate(R.layout.character_item_image, parent, false);
            view.setTag("DROPDOWN");
        }

        view.setPadding(15, 15, 15, 15);
        ImageView imageView = (ImageView) view.findViewById(R.id.character_item_image);
        imageView.setImageDrawable(MediaUtils.getDrawableFromAssets(mContext, getItem(position).getCharacterImagePath()));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = getLayoutInflater().inflate(R.layout.character_item_vertical, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        view.setPadding(15, 15, 15, 15);

        TextView textView = (TextView) view.findViewById(R.id.character_item_name);
        textView.setText(getItem(position).getCharacterName());

        ImageView imageView = (ImageView) view.findViewById(R.id.character_item_image);
        imageView.setImageDrawable(MediaUtils.getDrawableFromAssets(mContext, getItem(position).getCharacterImagePath()));
        return view;
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

}