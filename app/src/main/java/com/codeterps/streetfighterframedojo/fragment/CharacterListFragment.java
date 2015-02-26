package com.codeterps.streetfighterframedojo.fragment;

import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.CharacterListAdapter;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.util.DividerItemDecoration;
import com.codeterps.streetfighterframedojo.util.MediaUtils;

import java.util.ArrayList;
import java.util.List;

public class CharacterListFragment extends Fragment {

    private static final String ARG_GAME = "game";
    private static final String ARG_TRANSITION_NAMES = "transition_names";

    private Game mGame;
    private String mTransitionNames[];

    private List<Character> mCharacterList;
    private CharacterListAdapter mCharactersListAdapter;


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

            mCharacterList = new ArrayList<>();
            mCharactersListAdapter = new CharacterListAdapter(getActivity(), mCharacterList);

            new PopulateCharacterListTask().execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setExitTransition(new Slide());

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_character_list, container, false);
        v.setTransitionName(mTransitionNames[0]);

        ImageView gameLogoView = (ImageView) v.findViewById(R.id.game_card_logo);
        gameLogoView.setImageDrawable(MediaUtils.getDrawableFromAssets(getActivity(), mGame.getGameLogoPath()));
        gameLogoView.setTransitionName(mTransitionNames[1]);

        final TextView gameTitleView = (TextView) v.findViewById(R.id.game_card_title);
        gameTitleView.setText(mGame.getGameName());
        gameTitleView.setTransitionName(mTransitionNames[2]);

        final ImageButton imageButton = (ImageButton) v.findViewById(R.id.game_card_fab);
        imageButton.setTransitionName(mTransitionNames[3]);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline.setOval(0, 0, size, size);
            }
        };
        imageButton.setOutlineProvider(viewOutlineProvider);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        RecyclerView recList = (RecyclerView) v.findViewById(R.id.character_list);
        recList.addItemDecoration(new DividerItemDecoration(getActivity().getResources().getDrawable(R.drawable.recycler_list_divider), true, true));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recList.setTransitionGroup(true);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(mCharactersListAdapter);

        /*Palette.generateAsync(MediaUtils.getBitmapFromDrawable(gameLogoView.getDrawable()), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (swatch != null) {
                    gameTitleView.setBackgroundColor(swatch.getRgb());
                    gameTitleView.setTextColor(swatch.getTitleTextColor());
                }
            }
        });*/

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
                mCharacterList.clear();
                mCharacterList.addAll(result);
                mCharactersListAdapter.notifyDataSetChanged();
            }
        }
    }
}
