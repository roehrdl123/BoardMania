package com.example.boardmania.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boardmania.Data.HistoryEntry;
import com.example.boardmania.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>
{

    private final List<HistoryEntry> mValues;

    public HistoryRecyclerViewAdapter(List<HistoryEntry> items)
    {
        mValues = new ArrayList<>();
        for (HistoryEntry h: items)
        {
            mValues.add(0,h);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_historyfragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mGame.setImageResource(mValues.get(position).getGame().getIcon());
        holder.mPlayer1.setText(mValues.get(position).getP1().getName());
        holder.mPlayer2.setText(mValues.get(position).getP2().getName());
        holder.mWinner.setText(mValues.get(position).getWinner());
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final ImageView mGame;
        public final TextView mPlayer1;
        public final TextView mPlayer2;
        public final TextView mWinner;
        public HistoryEntry mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mGame = (ImageView) view.findViewById(R.id.Game_icon_history);
            mPlayer1 = (TextView) view.findViewById(R.id.Player1_History);
            mPlayer2 = (TextView) view.findViewById(R.id.Player2_History);
            mWinner = (TextView) view.findViewById(R.id.History_Winner);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mWinner.getText() + "'";
        }
    }
}
