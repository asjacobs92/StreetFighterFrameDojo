package com.codeterps.streetfighterframedojo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.AttributeListAdapter;
import com.codeterps.streetfighterframedojo.model.Attribute;
import com.codeterps.streetfighterframedojo.ui.GridDividerItemDecoration;

import java.util.ArrayList;

public class CharacterAttributesFragment extends Fragment {

    private static final String ARG_ATTRIBUTES = "attributes";
    private static final int GRID_LAYOUT_SPAN_COUNT = 2;

    private ArrayList<Attribute> mAttributes;
    private AttributeListAdapter mAttributesAdapter;

    public CharacterAttributesFragment() {
    }

    public static CharacterAttributesFragment newInstance(ArrayList<Attribute> attributes) {
        CharacterAttributesFragment fragment = new CharacterAttributesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ATTRIBUTES, attributes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_attributes, container, false);

        if (getArguments() != null) {
            mAttributes = (ArrayList<Attribute>) getArguments().getSerializable(ARG_ATTRIBUTES);
            mAttributesAdapter = new AttributeListAdapter(mAttributes);

            RecyclerView recList = (RecyclerView) v.findViewById(R.id.character_attribute_list);
            recList.setHasFixedSize(true);
            recList.addItemDecoration(new GridDividerItemDecoration(getActivity().getResources().getDrawable(R.drawable.recycler_view_divider)));
            GridLayoutManager glm = new GridLayoutManager(getActivity(), GRID_LAYOUT_SPAN_COUNT);
            glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (position == 0) ? 2 : 1;
                }
            });
            recList.setLayoutManager(glm);
            recList.setAdapter(mAttributesAdapter);
        }
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAttributes = null;
        mAttributesAdapter = null;
    }
}