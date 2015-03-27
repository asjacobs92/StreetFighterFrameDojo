package com.codeterps.streetfighterframedojo.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.MatchupSpinnerAdapter;
import com.codeterps.streetfighterframedojo.data.DatabaseHelper;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.util.MediaUtils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class MatchupActivity extends ActionBarActivity {

    public static final String ARG_CHARACTER = "character";

    private Character mCharacter;

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

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            mCharacter = (Character) b.getSerializable(ARG_CHARACTER);

            mCharacters = new ArrayList<>();
            mMatchupAdapter = new MatchupSpinnerAdapter(this, mCharacters);

            ImageView characterImage = (ImageView) findViewById(R.id.character_item_image);
            characterImage.setImageDrawable(MediaUtils.getDrawableFromAssets(this, mCharacter.getCharacterImagePath()));

            TextView characterName = (TextView) findViewById(R.id.character_item_name);
            characterName.setText(mCharacter.getCharacterName());

            Spinner mathcupSpinner = (Spinner) findViewById(R.id.matchup_spinner);
            mathcupSpinner.setAdapter(mMatchupAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPopulateCharacterListTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default, menu);
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
            if (mCharacter != null) {
                try {
                    getDbHelper().getCharacterDao().refresh(mCharacter);
                    Game game = mCharacter.getGame();
                    getDbHelper().getGameDao().refresh(game);
                    characterList = new ArrayList<>(game.getGameCharacters());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
