package com.example.boardmania.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boardmania.Data.Player;
import com.example.boardmania.R;

import java.util.List;


public class PlayersRecyclerViewAdapter extends RecyclerView.Adapter<PlayersRecyclerViewAdapter.ViewHolder>
{

    private final List<Player> mValues;

    public PlayersRecyclerViewAdapter(List<Player> items)
    {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mName.setText(mValues.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final TextView mName;
        public Player mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.name_Player);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
