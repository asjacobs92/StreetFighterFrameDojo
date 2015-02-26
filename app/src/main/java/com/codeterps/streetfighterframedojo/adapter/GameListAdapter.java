package com.codeterps.streetfighterframedojo.adapter;

import android.content.Context;
import android.graphics.Outline;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.fragment.CharacterListFragment;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.util.MediaUtils;

import java.util.List;

/**
 * Created by Arthur Jacobs on 1/19/2015.
 */
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private Context mContext;
    private List<Game> mDataset;

    public GameListAdapter(Context c, List<Game> myDataset) {
        mContext = c;
        mDataset = myDataset;
    }

    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String transitionNames[] =
                {
                        "game_card_" + position,
                        "game_card_logo_" + position,
                        "game_card_title_" + position,
                        "game_card_fab_" + position,
                };

        holder.itemView.setTransitionName(transitionNames[0]);

        holder.mTextView.setText(mDataset.get(position).getGameName());
        holder.mTextView.setTransitionName(transitionNames[2]);

        holder.mImageView.setImageDrawable(MediaUtils.getDrawableFromAssets(mContext, mDataset.get(position).getGameLogoPath()));
        holder.mImageView.setTransitionName(transitionNames[1]);

        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = mContext.getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline.setOval(0, 0, size, size);
            }
        };
        holder.mImageButton.setOutlineProvider(viewOutlineProvider);
        holder.mImageButton.setTransitionName(transitionNames[3]);
        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterListFragment fragment = CharacterListFragment.newInstance(mDataset.get(position), transitionNames);
                fragment.setSharedElementEnterTransition(TransitionInflater.from(mContext).inflateTransition(R.transition.game_list_exit));
                fragment.setEnterTransition(TransitionInflater.from(mContext).inflateTransition(R.transition.character_list_enter));

                ((ActionBarActivity) mContext).getSupportFragmentManager()
                        .beginTransaction()
                        .addSharedElement(holder.itemView, transitionNames[0])
                        .addSharedElement(holder.mImageView, transitionNames[1])
                        .addSharedElement(holder.mTextView, transitionNames[2])
                        .addSharedElement(holder.mImageButton, transitionNames[3])
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        /*Palette.generateAsync(MediaUtils.getBitmapFromDrawable(holder.mImageView.getDrawable()), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (swatch != null) {
                    holder.mTextView.setBackgroundColor(swatch.getRgb());
                    holder.mTextView.setTextColor(swatch.getTitleTextColor());
                }
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public ImageButton mImageButton;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.game_card_title);
            mImageView = (ImageView) v.findViewById(R.id.game_card_logo);
            mImageButton = (ImageButton) v.findViewById(R.id.game_card_fab);
        }
    }
}
