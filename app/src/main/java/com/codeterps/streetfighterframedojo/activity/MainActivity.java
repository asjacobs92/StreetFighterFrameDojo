package com.codeterps.streetfighterframedojo.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.CarouselAdapter;
import com.touchmenotapps.carousel.simple.HorizontalCarouselLayout;
import com.touchmenotapps.carousel.simple.HorizontalCarouselStyle;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private HorizontalCarouselStyle mStyle;
    private HorizontalCarouselLayout mCarousel;
    private CarouselAdapter mAdapter;
    private ArrayList<Integer> mData = new ArrayList<Integer>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData.add(R.drawable.usf4_logo);
        mData.add(R.drawable.sf3ts_logo);
        mData.add(R.drawable.ssf2t_logo);
        mAdapter = new CarouselAdapter(this);
        mAdapter.setData(mData);
        mCarousel = (HorizontalCarouselLayout) findViewById(R.id.carousel_layout);
        mStyle = new HorizontalCarouselStyle(this, HorizontalCarouselStyle.NO_STYLE);
        mCarousel.setStyle(mStyle);
        mCarousel.setAdapter(mAdapter);

        mCarousel.setOnCarouselViewChangedListener(new HorizontalCarouselLayout.CarouselInterface() {
            @Override
            public void onItemChangedListener(View v, int position) {
                Toast.makeText(MainActivity.this, "Position " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
