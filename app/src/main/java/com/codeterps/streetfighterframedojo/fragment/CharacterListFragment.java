package com.codeterps.streetfighterframedojo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.CharacterListAdapter;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.ui.ListDividerItemDecoration;
import com.codeterps.streetfighterframedojo.util.MediaUtils;

import java.util.ArrayList;
import java.util.List;

public class CharacterListFragment extends Fragment {

    private static final String ARG_GAME = "game";
    private static final String ARG_TRANSITION_NAMES = "transition_names";

    private Game mGame;
    private String mTransitionNames[];

    private List<Character> mCharacters;
    private CharacterListAdapter mCharactersAdapter;

    public CharacterListFragment() {
    }

    public static CharacterListFragment newInstance(Game game, String transitionNames[]) {
        CharacterListFragment fragment = new CharacterListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GAME, game);
        args.putStringArray(ARG_TRANSITION_NAMES, transitionNames);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGame = (Game) getArguments().getSerializable(ARG_GAME);
            mTransitionNames = getArguments().getStringArray(ARG_TRANSITION_NAMES);

            mCharacters = new ArrayList<>();
            mCharactersAdapter = new CharacterListAdapter(getActivity(), mCharacters);

            new PopulateCharacterListTask().execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setExitTransition(new Slide());

        View v = inflater.inflate(R.layout.fragment_character_list, container, false);
        v.setTransitionName(mTransitionNames[0]);

        ImageView gameLogoView = (ImageView) v.findViewById(R.id.game_card_logo);
        gameLogoView.setImageDrawable(MediaUtils.getDrawableFromAssets(getActivity(), mGame.getGameLogoPath()));
        gameLogoView.setTransitionName(mTransitionNames[1]);

        TextView gameTitleView = (TextView) v.findViewById(R.id.game_card_title);
        gameTitleView.setText(mGame.getGameName());
        gameTitleView.setTransitionName(mTransitionNames[2]);

        ImageButton imageButton = (ImageButton) v.findViewById(R.id.game_card_fab);
        imageButton.setTransitionName(mTransitionNames[3]);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        };

        v.setOnClickListener(clickListener);
        imageButton.setOnClickListener(clickListener);

        RecyclerView recList = (RecyclerView) v.findViewById(R.id.character_list);
        recList.setHasFixedSize(true);
        recList.setTransitionGroup(true);
        recList.addItemDecoration(new ListDividerItemDecoration(getActivity().getResources().getDrawable(R.drawable.recycler_view_divider)));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(mCharactersAdapter);

        return v;
    }

    private class PopulateCharacterListTask extends AsyncTask<Void, Void, ArrayList<Character>> {

        @Override
        protected ArrayList<Character> doInBackground(Void... params) {
            return new ArrayList<>(mGame.getGameCharacters());
        }

        @Override
        protected void onPostExecute(ArrayList<Character> result) {
            if (result != null) {
                mCharacters.clear();
                mCharacters.addAll(result);
                mCharactersAdapter.notifyDataSetChanged();
            }
        }
    }
}
