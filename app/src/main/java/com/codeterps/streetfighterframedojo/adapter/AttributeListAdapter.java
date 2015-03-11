package com.codeterps.streetfighterframedojo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.Attribute;

import java.util.List;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class AttributeListAdapter extends RecyclerView.Adapter<AttributeListAdapter.ViewHolder> {

    private Context mContext;
    private List<Attribute> mDataset;

    public AttributeListAdapter(Context c, List<Attribute> myDataset) {
        mContext = c;
        mDataset = myDataset;
    }

    @Override
    public AttributeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attribute_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mSmallTextView.setText(getItem(position).getAttributeName());
        String attrValue = getItem(position).getAttributeValue();
        holder.mLargeTextView.setText(attrValue.isEmpty() ? "-" : attrValue);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Attribute getItem(int position) {
        return mDataset.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mSmallTextView;
        public TextView mLargeTextView;

        public ViewHolder(View v) {
            super(v);
            mSmallTextView = (TextView) v.findViewById(R.id.attribute_item_name);
            mLargeTextView = (TextView) v.findViewById(R.id.attribute_item_value);
        }

    }
}
