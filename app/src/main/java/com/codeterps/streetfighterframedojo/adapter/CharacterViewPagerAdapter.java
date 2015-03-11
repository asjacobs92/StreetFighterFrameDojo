package com.codeterps.streetfighterframedojo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.codeterps.streetfighterframedojo.fragment.CharacterAttributesFragment;
import com.codeterps.streetfighterframedojo.fragment.CharacterMovesFragment;
import com.codeterps.streetfighterframedojo.fragment.CharacterPersonalNotesFragment;
import com.codeterps.streetfighterframedojo.model.Character;

import java.util.ArrayList;

/**
 * Created by Arthur Jacobs on 3/10/2015.
 */
public class CharacterViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final CharSequence TAB_TITLES[] = {
            "Moves", "Attributes", "Notes"
    };

    private static final int TAB_COUNT = 3;

    private Character mCharacter;

    public CharacterViewPagerAdapter(FragmentManager fm, Character character) {
        super(fm);
        mCharacter = character;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CharacterMovesFragment.newInstance(new ArrayList<>(mCharacter.getCharacterMoves()));
            case 1:
                return CharacterAttributesFragment.newInstance(new ArrayList<>(mCharacter.getCharacterAttributes()));
            case 2:
                return CharacterPersonalNotesFragment.newInstance(new ArrayList<>(mCharacter.getCharacterPersonalNotes()));
            default:
                return CharacterMovesFragment.newInstance(new ArrayList<>(mCharacter.getCharacterMoves()));
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }


}
