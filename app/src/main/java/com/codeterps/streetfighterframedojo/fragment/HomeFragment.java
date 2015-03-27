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

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.GameListAdapter;
import com.codeterps.streetfighterframedojo.data.DatabaseHelper;
import com.codeterps.streetfighterframedojo.model.Game;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public PopulateGameListTask mPopulateListTask;
    private List<Game> mGameList;
    private GameListAdapter mGameListAdapter;
    private DatabaseHelper mDbHelper;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Slide());
        setExitTransition(new Slide());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mGameList = new ArrayList<>();
        mGameListAdapter = new GameListAdapter(getActivity(), mGameList);

        RecyclerView recList = (RecyclerView) v.findViewById(R.id.game_card_list);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(mGameListAdapter);

        return v;
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
        mGameList = null;
        mGameListAdapter = null;
    }

    private DatabaseHelper getDbHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mDbHelper;
    }

    public PopulateGameListTask getPopulateCharacterListTask() {
        if (mPopulateListTask == null) {
            mPopulateListTask = new PopulateGameListTask();
        }
        return mPopulateListTask;
    }

    private class PopulateGameListTask extends AsyncTask<Void, Void, ArrayList<Game>> {

        @Override
        protected ArrayList<Game> doInBackground(Void... params) {
            List<Game> gameList = null;
            try {
                gameList = getDbHelper().getGameDao().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return (ArrayList<Game>) gameList;
        }

        @Override
        protected void onPostExecute(ArrayList<Game> result) {
            mPopulateListTask = null;
            if (result != null) {
                mGameList.clear();
                mGameList.addAll(result);
                mGameListAdapter.notifyDataSetChanged();
            }
        }
    }
}
