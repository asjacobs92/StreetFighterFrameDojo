package com.codeterps.streetfighterframedojo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.CharacterListAdapter;
import com.codeterps.streetfighterframedojo.data.DatabaseHelper;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.ui.ListDividerItemDecoration;
import com.codeterps.streetfighterframedojo.util.MediaUtils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterListFragment extends Fragment {

    private static final String ARG_GAME = "game";
    private static final String ARG_TRANSITION_NAMES = "transition_names";

    private Game mGame;
    private List<Character> mCharacters;
    private CharacterListAdapter mCharactersAdapter;

    private DatabaseHelper mDbHelper;
    private PopulateCharacterListTask mPopulateListTask;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_list, container, false);
        setEnterTransition(new Slide());
        setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.game_list_return));

        if (getArguments() != null) {
            mGame = (Game) getArguments().getSerializable(ARG_GAME);
            String transitionNames[] = getArguments().getStringArray(ARG_TRANSITION_NAMES);

            mCharacters = new ArrayList<>();
            mCharactersAdapter = new CharacterListAdapter(getActivity(), mCharacters);

            ImageView gameLogoView = (ImageView) v.findViewById(R.id.game_card_logo);
            TextView gameTitleView = (TextView) v.findViewById(R.id.game_card_title);
            ImageButton imageButton = (ImageButton) v.findViewById(R.id.game_card_fab);
            RecyclerView recList = (RecyclerView) v.findViewById(R.id.character_list);

            v.setTransitionName(transitionNames[0]);
            gameLogoView.setTransitionName(transitionNames[1]);
            gameTitleView.setTransitionName(transitionNames[2]);
            imageButton.setTransitionName(transitionNames[3]);

            gameTitleView.setText(mGame.getGameName());
            gameLogoView.setImageDrawable(MediaUtils.getDrawableFromAssets(getActivity(), mGame.getGameLogoPath()));

            recList.setHasFixedSize(true);
            recList.setTransitionGroup(true);
            recList.addItemDecoration(new ListDividerItemDecoration(getActivity().getResources().getDrawable(R.drawable.recycler_view_divider, null)));
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
            recList.setAdapter(mCharactersAdapter);

            View.OnClickListener clickListener = getOnViewClickListener();
            v.setOnClickListener(clickListener);
            imageButton.setOnClickListener(clickListener);
        }

        return v;
    }

    private View.OnClickListener getOnViewClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        getPopulateCharacterListTask().execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDbHelper != null) {
            OpenHelperManager.releaseHelper();
            mDbHelper = null;
        }
        mGame = null;
        mCharacters = null;
        mCharactersAdapter = null;
    }

    private PopulateCharacterListTask getPopulateCharacterListTask() {
        if (mPopulateListTask == null) {
            mPopulateListTask = new PopulateCharacterListTask();
        }

        return mPopulateListTask;
    }

    private DatabaseHelper getDbHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mDbHelper;
    }

    private class PopulateCharacterListTask extends AsyncTask<Void, Void, ArrayList<Character>> {

        @Override
        protected ArrayList<Character> doInBackground(Void... params) {
            ArrayList<Character> characterList = null;
            try {
                getDbHelper().getGameDao().refresh(mGame);
                characterList = new ArrayList<>(mGame.getGameCharacters());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return characterList;
        }

        @Override
        protected void onPostExecute(ArrayList<Character> result) {
            mPopulateListTask = null;
            if (result != null) {
                mCharacters.clear();
                mCharacters.addAll(result);
                mCharactersAdapter.notifyDataSetChanged();
            }
        }
    }
}
