package com.example.boardmania.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boardmania.Data.Game;
import com.example.boardmania.Data.Player;
import com.example.boardmania.PlayerChooser.PlayerChooserActivity;
import com.example.boardmania.R;

import java.util.List;

import io.realm.Realm;


public class GamesRecyclerViewAdapter extends RecyclerView.Adapter<GamesRecyclerViewAdapter.ViewHolder>
{

    private final List<Game> mValues;

    public GamesRecyclerViewAdapter(List<Game> items)
    {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mIconView.setImageResource(mValues.get(position).getIcon());
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
        public final TextView mContentView;
        public Game mItem;

        private ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIconView = (ImageView) view.findViewById(R.id.icon_List_Games);
            mContentView = (TextView) view.findViewById(R.id.Game_name);

            mView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Context context = v.getContext();
                    int pos = getAdapterPosition();
                    Game game = mValues.get(pos);
                    if(game.getName().equals("TicTacToe"))
                    {
                        Intent intent = new Intent(context, PlayerChooserActivity.class);
                        intent.putExtra("Game", game.getName());
                        context.startActivity(intent);
                    }

                }
            });
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }


}
