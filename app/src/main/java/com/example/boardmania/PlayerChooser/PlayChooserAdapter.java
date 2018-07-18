package com.example.boardmania.PlayerChooser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boardmania.Data.Game;
import com.example.boardmania.Data.Player;
import com.example.boardmania.R;
import com.example.boardmania.ui.HistoryRecyclerViewAdapter;

import java.util.List;

public class PlayChooserAdapter extends RecyclerView.Adapter<PlayChooserAdapter.ViewHolder>
{

    private final List<Player> mValues;

    public PlayChooserAdapter(List<Player> players)
    {
        mValues = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview, parent, false);
        return new PlayChooserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mIconView.setImageResource(mValues.get(position).getAvatarIcon());
        holder.mContentView.setText(mValues.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final ImageView mIconView;
        public final CheckedTextView mContentView;
        public Player mItem;

        private ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIconView = (ImageView) view.findViewById(R.id.playerAvatar);
            mContentView = (CheckedTextView) view.findViewById(R.id.Player_name);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}
