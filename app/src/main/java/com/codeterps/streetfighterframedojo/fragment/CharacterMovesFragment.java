package com.codeterps.streetfighterframedojo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.MoveListAdapter;
import com.codeterps.streetfighterframedojo.model.Move;
import com.codeterps.streetfighterframedojo.ui.ListDividerItemDecoration;

import java.util.ArrayList;

public class CharacterMovesFragment extends Fragment {

    private static final String ARG_MOVES = "moves";

    private ArrayList<Move> mMoves;
    private MoveListAdapter mMovesAdapter;

    public CharacterMovesFragment() {
    }

    public static CharacterMovesFragment newInstance(ArrayList<Move> moves) {
        CharacterMovesFragment fragment = new CharacterMovesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVES, moves);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_moves, container, false);
        if (getArguments() != null) {
            mMoves = (ArrayList<Move>) getArguments().getSerializable(ARG_MOVES);
            mMovesAdapter = new MoveListAdapter(getActivity(), mMoves);

            RecyclerView recList = (RecyclerView) v.findViewById(R.id.character_move_list);
            recList.setHasFixedSize(true);
            recList.addItemDecoration(new ListDividerItemDecoration(getActivity().getResources().getDrawable(R.drawable.recycler_view_divider)));
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
            recList.setAdapter(mMovesAdapter);
        }

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMoves = null;
    }
}
