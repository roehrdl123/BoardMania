package com.example.boardmania.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boardmania.Data.HistoryEntry;
import com.example.boardmania.R;

import java.util.List;


public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>
{

    private final List<HistoryEntry> mValues;

    public HistoryRecyclerViewAdapter(List<HistoryEntry> items)
    {
        mValues = items;
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
        holder.mGame.setText(mValues.get(position).getGame().getName());
        holder.mPlayer1.setText(mValues.get(position).getP1().getName());
        holder.mPlayer2.setText(mValues.get(position).getP2().getName());
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final TextView mGame;
        public final TextView mPlayer1;
        public final TextView mPlayer2;
        public HistoryEntry mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mGame = (TextView) view.findViewById(R.id.Game_name_history);
            mPlayer1 = (TextView) view.findViewById(R.id.Player1_History);
            mPlayer2 = (TextView) view.findViewById(R.id.Player2_History);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mGame.getText() + "'";
        }
    }
}
