package com.codeterps.streetfighterframedojo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class NavDrawerAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<NavDrawerItem> mDataset;
    private HashMap<NavDrawerItem, ArrayList<String>> mSubDataset;

    public NavDrawerAdapter(Context context, ArrayList<NavDrawerItem> myDataset, HashMap<NavDrawerItem, ArrayList<String>> subDataset) {
        this.mContext = context;
        this.mDataset = myDataset;
        this.mSubDataset = subDataset;
    }

    public LayoutInflater getLayoutInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        return mInflater;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mSubDataset.get(this.mDataset.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // TODO: Change logo accordion to child
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        SubViewHolder holder;
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.nav_drawer_subitem, parent, false);
            holder = new SubViewHolder();
           /* holder.mTextView = (TextView) convertView.findViewById(R.id.nav_drawer_subitem_text);
            holder.mTextView.setText(childText);*/
            convertView.setTag(holder);
        } else {
            holder = (SubViewHolder) convertView.getTag();
        }

        //holder.mTextView.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.mSubDataset.get(this.mDataset.get(groupPosition)) != null) {
            return this.mSubDataset.get(this.mDataset.get(groupPosition)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mDataset.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mDataset.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.nav_drawer_item, parent, false);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.nav_drawer_item_text);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.nav_drawer_item_image);
            holder.mTextView.setText(mDataset.get(groupPosition).getTitle());
            holder.mImageView.setImageResource(mDataset.get(groupPosition).getIcon());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(mDataset.get(groupPosition).getTitle());
        holder.mImageView.setImageResource(mDataset.get(groupPosition).getIcon());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolder {
        TextView mTextView;
        ImageView mImageView;
    }

    private static class SubViewHolder {
        TextView mTextView;
    }
}