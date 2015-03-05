package com.codeterps.streetfighterframedojo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.util.MediaUtils;
import com.melnykov.fab.FloatingActionButton;

public class CharacterDetailsActivity extends ActionBarActivity {

    public static final String ARG_CHAR = "character";

    private Character mCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            mCharacter = (Character) b.getSerializable(ARG_CHAR);

            ImageView characterImage = (ImageView) findViewById(R.id.character_item_image);
            characterImage.setImageDrawable(MediaUtils.getDrawableFromAssets(this,
                    MediaUtils.getCharPortraitUri(mCharacter.getGame().getGameName(), mCharacter.getCharacterName())));

            TextView characterName = (TextView) findViewById(R.id.character_item_name);
            characterName.setText(mCharacter.getCharacterName());

            Bitmap characterBitmap = MediaUtils.getBitmapFromDrawable(characterImage.getDrawable());
            Palette.generateAsync(characterBitmap, getPaletteAsyncListener());
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

                if (primarySwatch != null && darkPrimarySwatch != null) {
                    View headerLayout = findViewById(R.id.character_header_layout);
                    headerLayout.setBackgroundColor(primarySwatch.getRgb());
                    TextView characterName = (TextView) findViewById(R.id.character_item_name);
                    characterName.setTextColor(primarySwatch.getTitleTextColor());

                    FloatingActionButton characterMatchupFab = (FloatingActionButton) findViewById(R.id.character_matchup_fab);
                    characterMatchupFab.setColorNormal(darkPrimarySwatch.getRgb());
                    characterMatchupFab.setColorPressed(primarySwatch.getRgb());
                    characterMatchupFab.setColorRipple(darkPrimarySwatch.getTitleTextColor());
                }
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
}
