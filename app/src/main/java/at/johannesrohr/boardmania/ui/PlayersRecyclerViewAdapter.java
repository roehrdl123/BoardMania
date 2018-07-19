package at.johannesrohr.boardmania.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import at.johannesrohr.boardmania.Data.Player;
import com.johannesrohr.boardmania.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_players, parent, false);
        return new ViewHolder(view);
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
        public final TextView mContentView;
        public Player mItem;

        private ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIconView = (ImageView) view.findViewById(R.id.playerAvatarMenu);
            mContentView = (TextView) view.findViewById(R.id.Player_nameMenu);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}
