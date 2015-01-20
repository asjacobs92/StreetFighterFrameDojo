package com.codeterps.streetfighterframedojo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.DrawerListItem;

import java.util.ArrayList;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class NavDrawerAdapter extends ArrayAdapter<DrawerListItem> {

    private LayoutInflater mInflater;
    private ArrayList<DrawerListItem> mNavDrawerItems;

    public NavDrawerAdapter(Context context, ArrayList<DrawerListItem> navDrawerItems) {
        super(context, R.layout.nav_drawer_item, navDrawerItems);
        this.mNavDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return mNavDrawerItems.size();
    }

    @Override
    public DrawerListItem getItem(int position) {
        return mNavDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.nav_drawer_item, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.nav_drawer_item_image);
            holder.text = (TextView) convertView.findViewById(R.id.nav_drawer_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(mNavDrawerItems.get(position).getIcon());
        holder.text.setText(mNavDrawerItems.get(position).getTitle());

        return convertView;
    }

    public LayoutInflater getLayoutInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(getContext());
        }
        return mInflater;
    }

    private static class ViewHolder {
        ImageView image;
        TextView text;
    }
}