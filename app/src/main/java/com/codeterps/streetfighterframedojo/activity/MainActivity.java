package com.codeterps.streetfighterframedojo.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.adapter.NavDrawerAdapter;
import com.codeterps.streetfighterframedojo.fragment.HomeFragment;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.model.NavDrawerItem;
import com.codeterps.streetfighterframedojo.util.SessionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends ActionBarActivity {

    private SessionManager mSessionManager;

    private ExpandableListView mDrawerList;
    private NavDrawerAdapter mDrawerAdapter;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mDrawerListLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private Button mLoginButton;
    private Button mProfileButton;

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListLayout = (LinearLayout) findViewById(R.id.drawer_list_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer_list);
        mDrawerToggle = getActionBarDrawerToggle();

        mLoginButton = (Button) findViewById(R.id.account_sign_in_button);
        mProfileButton = (Button) findViewById(R.id.account_profile_button);

        initDrawerLayout();
        initProfileView();
        setLoginButtonListener();
        setProfileButtonListener();

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initProfileView();
    }

    private void initProfileView() {
        TextView usernameView = (TextView) findViewById(R.id.account_profile_username);
        ImageView profilePicView = (ImageView) findViewById(R.id.account_profile_pic);

        if (getSessionManager().isLoggedIn()) {
            mLoginButton.setVisibility(View.GONE);
            mProfileButton.setVisibility(View.VISIBLE);

            usernameView.setText(getSessionManager().getCurrentUser().get(SessionManager.KEY_USERNAME));
            profilePicView.setImageDrawable(getResources().getDrawable(R.drawable.ken, null));
        } else {
            mLoginButton.setVisibility(View.VISIBLE);
            mProfileButton.setVisibility(View.GONE);

            usernameView.setText(getResources().getString(R.string.not_signed_in));
            profilePicView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_person, null));
        }
    }

    private void initDrawerLayout() {
        String[] navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        String[] navGameTitles = getResources().getStringArray(R.array.game_titles);
        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<>();
        for (int i = 0; i < navMenuTitles.length; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
        }
        navMenuIcons.recycle();

        NavDrawerItem item = navDrawerItems.get(1);
        HashMap<NavDrawerItem, ArrayList<String>> navDrawerSubItems = new HashMap<>();
        navDrawerSubItems.put(item, new ArrayList<>(Arrays.asList(navGameTitles)));

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerAdapter = new NavDrawerAdapter(this, navDrawerItems, navDrawerSubItems);
        mDrawerList.setAdapter(mDrawerAdapter);
        mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (mDrawerAdapter.getChildrenCount(groupPosition) == 0) {
                    selectItem(groupPosition);
                } else {
                    if (mDrawerList.isGroupExpanded(groupPosition)) {
                        mDrawerList.collapseGroup(groupPosition);
                    } else {
                        mDrawerList.expandGroup(groupPosition, true);
                    }
                }
                return true;
            }
        });

        mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {
                Game.GameId gameId;
                switch (i2) {
                    case 0:
                        gameId = Game.GameId.USF4;
                        break;
                    case 1:
                        gameId = Game.GameId.SF3TS;
                        break;
                    case 2:
                        gameId = Game.GameId.SSF2T;
                        break;
                    default:
                        gameId = Game.GameId.USF4;
                        break;
                }

                Intent intent = new Intent(view.getContext(), MatchupActivity.class);
                intent.putExtra(MatchupActivity.ARG_GAME, gameId);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
                return false;
            }
        });
        mDrawerList.expandGroup(1);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setLoginButtonListener() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext()).toBundle());
            }
        });
    }

    private void setProfileButtonListener() {
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) v.getContext(),
                                Pair.create(findViewById(R.id.account_profile_pic), "profile_pic"),
                                Pair.create(findViewById(R.id.account_profile_username), "profile_username"));
                startActivity(intent, options.toBundle());
            }
        });
    }

    private ActionBarDrawerToggle getActionBarDrawerToggle() {
        return new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_default, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new HomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerLayout.closeDrawer(mDrawerListLayout);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private SessionManager getSessionManager() {
        if (mSessionManager == null) {
            mSessionManager = new SessionManager(this);
        }
        return mSessionManager;
    }
}
