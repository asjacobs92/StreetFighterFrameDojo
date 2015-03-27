package com.codeterps.streetfighterframedojo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.PersonalCharacterNote;

import java.util.ArrayList;

public class CharacterPersonalNotesFragment extends Fragment {

    private static final String ARG_PERSONAL_NOTES = "personal_notes";

    private ArrayList<PersonalCharacterNote> mPersonalNotes;

    public CharacterPersonalNotesFragment() {
    }

    public static CharacterPersonalNotesFragment newInstance(ArrayList<PersonalCharacterNote> personalNotes) {
        CharacterPersonalNotesFragment fragment = new CharacterPersonalNotesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PERSONAL_NOTES, personalNotes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPersonalNotes = (ArrayList<PersonalCharacterNote>) getArguments().getSerializable(ARG_PERSONAL_NOTES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_personal_notes, container, false);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPersonalNotes = null;
    }
}
