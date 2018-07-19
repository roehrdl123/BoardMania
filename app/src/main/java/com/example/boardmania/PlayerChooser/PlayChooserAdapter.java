package com.example.boardmania.PlayerChooser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boardmania.Data.Game;
import com.example.boardmania.Data.Player;
import com.example.boardmania.R;
import com.example.boardmania.ui.HistoryRecyclerViewAdapter;

import java.util.List;

import io.realm.Realm;

public class PlayChooserAdapter extends RecyclerView.Adapter<PlayChooserAdapter.ViewHolder>
{

    private final List<Player> mValues;
    private boolean clickable = true;
    PlayerChooserModel model;

    public PlayChooserAdapter(List<Player> players, PlayerChooserModel model)
    {
        mValues = players;
        this.model = model;
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
        holder.mCheckbox.setChecked(mValues.get(position).isSelected());
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
        public final CheckBox mCheckbox;
        public Player mItem;

        private ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIconView = (ImageView) view.findViewById(R.id.playerAvatar);
            mContentView = (TextView) view.findViewById(R.id.Player_name);
            mCheckbox = (CheckBox) view.findViewById(R.id.Player_CheckBox);

            mView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(!clickable)
                    { return; }

                    int pos = getAdapterPosition();
                    Player player = mValues.get(pos);
                    Toast.makeText(v.getContext(), "You clicked " + player.getName(), Toast.LENGTH_SHORT).show();

                    Realm r = Realm.getDefaultInstance();
                    r.beginTransaction();
                    clickable = model.onItemClick(player,v, mValues);
                    r.commitTransaction();
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
