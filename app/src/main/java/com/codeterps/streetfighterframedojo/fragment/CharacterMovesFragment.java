package com.codeterps.streetfighterframedojo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.Move;

import java.util.ArrayList;

public class CharacterMovesFragment extends Fragment {

    private static final String ARG_MOVES = "moves";

    private ArrayList<Move> mMoves;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMoves = (ArrayList<Move>) getArguments().getSerializable(ARG_MOVES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_moves, container, false);
    }
}
