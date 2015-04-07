package com.codeterps.streetfighterframedojo.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.MatchupSpinnerAdapter;
import com.codeterps.streetfighterframedojo.data.DatabaseHelper;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.model.Game;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class MatchupActivity extends ActionBarActivity {

    public static final String ARG_GAME = "game";

    private Game mGame;
    private Game.GameId mGameId;

    private ArrayList<Character> mCharacters;
    private MatchupSpinnerAdapter mMatchupAdapter;

    private DatabaseHelper mDbHelper;
    private PopulateCharacterListTask mPopulateListTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mGameId = (Game.GameId) this.getIntent().getSerializableExtra(ARG_GAME);

        if (mGameId != null) {
            mCharacters = new ArrayList<>();
            mMatchupAdapter = new MatchupSpinnerAdapter(this, mCharacters);

            Spinner matchupSpinner = (Spinner) findViewById(R.id.matchup_spinner);
            matchupSpinner.setAdapter(mMatchupAdapter);

            Spinner matchupSpinner2 = (Spinner) findViewById(R.id.matchup_spinner2);
            matchupSpinner2.setAdapter(mMatchupAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPopulateCharacterListTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_matchup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }

    public PopulateCharacterListTask getPopulateCharacterListTask() {
        if (mPopulateListTask == null) {
            mPopulateListTask = new PopulateCharacterListTask();
        }
        return mPopulateListTask;
    }

    private class PopulateCharacterListTask extends AsyncTask<Void, Void, ArrayList<Character>> {

        @Override
        protected ArrayList<Character> doInBackground(Void... params) {
            ArrayList<Character> characterList = null;
            try {
                switch (mGameId) {
                    case USF4:
                        mGame = getDbHelper().getGameDao().queryForEq("gameName", "Ultra Street Fighter 4").get(0);
                        break;
                    case SF3TS:
                        mGame = getDbHelper().getGameDao().queryForEq("gameName", "Street Fighter 3 Third Strike").get(0);
                        break;
                    case SSF2T:
                        mGame = getDbHelper().getGameDao().queryForEq("gameName", "Super Street Fighter 2 Turbo").get(0);
                        break;
                    default:
                        mGame = getDbHelper().getGameDao().queryForEq("gameName", "Ultra Street Fighter 4").get(0);
                        break;
                }

                if (mGame != null) {
                    characterList = new ArrayList<>(mGame.getGameCharacters());
                }
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
                mMatchupAdapter.notifyDataSetChanged();
            }
        }
    }
}
