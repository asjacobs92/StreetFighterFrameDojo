package com.codeterps.streetfighterframedojo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.CardListAdapter;
import com.codeterps.streetfighterframedojo.data.DatabaseHelper;
import com.codeterps.streetfighterframedojo.model.Game;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private DatabaseHelper mDbHelper;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recList = (RecyclerView) v.findViewById(R.id.game_card_list);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        List<Game> gameList = new ArrayList<Game>();
        try {
            gameList = getDbHelper().getGameDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Hardcoded, remove when db is populated.
        Game usf4 = new Game();
        usf4.setGameName("Ultra Street Fighter 4");
        usf4.setGameLogoPath("game_logos/usf4_logo.jpg");
        gameList.add(usf4);

        Game sf3ts = new Game();
        sf3ts.setGameName("Street Fighter 3 Third Strike");
        sf3ts.setGameLogoPath("game_logos/sf3ts_logo.jpg");
        gameList.add(sf3ts);

        Game ssf2t = new Game();
        ssf2t.setGameName("Super Street Fighter 2 Turbo");
        ssf2t.setGameLogoPath("game_logos/ssf2t_logo.jpg");
        gameList.add(ssf2t);

        recList.setAdapter(new CardListAdapter(getActivity(), gameList));

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDbHelper != null) {
            OpenHelperManager.releaseHelper();
            mDbHelper = null;
        }
    }

    private DatabaseHelper getDbHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mDbHelper;
    }
}
