package com.codeterps.streetfighterframedojo.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.CharacterViewPagerAdapter;
import com.codeterps.streetfighterframedojo.data.DatabaseHelper;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.ui.SlidingTabLayout;
import com.codeterps.streetfighterframedojo.util.MediaUtils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class CharacterDetailsActivity extends ActionBarActivity {

    public static final String ARG_CHARACTER = "character";

    private Character mCharacter;

    private DatabaseHelper mDbHelper;

    private UpdateCharacterTask mUpdateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.primary_theme_color_dark));

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            mCharacter = (Character) b.getSerializable(ARG_CHARACTER);

            ImageView characterImage = (ImageView) findViewById(R.id.character_item_image);
            characterImage.setImageDrawable(MediaUtils.getDrawableFromAssets(this, mCharacter.getCharacterImagePath()));

            TextView characterName = (TextView) findViewById(R.id.character_item_name);
            characterName.setText(mCharacter.getCharacterName());

            Bitmap characterBitmap = MediaUtils.getBitmapFromDrawable(characterImage.getDrawable());
            Palette.generateAsync(characterBitmap, getPaletteAsyncListener());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getUpdateCharacterTask().execute();
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

    private Palette.PaletteAsyncListener getPaletteAsyncListener() {
        return new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch primarySwatch = palette.getVibrantSwatch();
                Palette.Swatch darkPrimarySwatch = palette.getDarkVibrantSwatch();

                if (primarySwatch == null || darkPrimarySwatch == null) {
                    primarySwatch = palette.getMutedSwatch();
                    darkPrimarySwatch = palette.getDarkMutedSwatch();
                }

                SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.character_details_tabs);
                if (primarySwatch != null && darkPrimarySwatch != null) {
                    View header = findViewById(R.id.character_details_header);
                    header.setBackgroundColor(primarySwatch.getRgb());

                    tabs.setBackgroundColor(primarySwatch.getRgb());
                    tabs.setTextColor(primarySwatch.getTitleTextColor());

                    TextView characterName = (TextView) findViewById(R.id.character_item_name);
                    characterName.setTextColor(primarySwatch.getTitleTextColor());

                    final int tabIndicatorColor = primarySwatch.getTitleTextColor();
                    tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                        @Override
                        public int getIndicatorColor(int position) {
                            return tabIndicatorColor;
                        }
                    });

                    getWindow().setStatusBarColor(darkPrimarySwatch.getRgb());
                }
            }
        };
    }

    private DatabaseHelper getDbHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDbHelper;
    }

    public UpdateCharacterTask getUpdateCharacterTask() {
        if (mUpdateTask == null) {
            mUpdateTask = new UpdateCharacterTask();
        }
        return mUpdateTask;
    }

    private class UpdateCharacterTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                getDbHelper().getCharacterDao().refresh(mCharacter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mUpdateTask = null;

            CharacterViewPagerAdapter viewPagerAdapter = new CharacterViewPagerAdapter(getSupportFragmentManager(), mCharacter);

            ViewPager viewPager = (ViewPager) findViewById(R.id.character_details_pager);
            viewPager.setAdapter(viewPagerAdapter);

            SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.character_details_tabs);
            tabs.setDistributeEvenly(true);
            tabs.setViewPager(viewPager);
        }
    }
}


