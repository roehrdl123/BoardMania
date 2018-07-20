package at.johannesrohr.boardmania.ui;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import at.johannesrohr.boardmania.Data.HistoryEntry;
import com.johannesrohr.boardmania.R;

import java.util.ArrayList;
import java.util.Date;
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

        Date time = mValues.get(position).getTime();
        String timeText = DateFormat.format("HH:mm",time).toString();

        holder.mItem = mValues.get(position);
        holder.mGame.setImageResource(mValues.get(position).getGame().getIcon());
        holder.mGameName.setText(mValues.get(position).getGame().getName());
        holder.mPlayer1.setText(mValues.get(position).getP1().getName());
        holder.mPlayer2.setText(mValues.get(position).getP2().getName());
        holder.mTime.setText(timeText);

        String winner = mValues.get(position).getWinner();
        String p1Name = mValues.get(position).getP1().getName();
        String p2Name = mValues.get(position).getP2().getName();

        if(winner.equals(p1Name))
        {
            holder.mPlayer1.setTypeface(null, Typeface.BOLD);
        }
        else if(winner.equals(p2Name))
        {
            holder.mPlayer2.setTypeface(null, Typeface.BOLD);
        }
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
        public final TextView mGameName;
        public final TextView mTime;
        public HistoryEntry mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mGame = (ImageView) view.findViewById(R.id.Game_icon_history);
            mPlayer1 = (TextView) view.findViewById(R.id.Player1_History);
            mPlayer2 = (TextView) view.findViewById(R.id.Player2_History);
            mGameName = (TextView) view.findViewById(R.id.history_GameName);
            mTime = (TextView) view.findViewById(R.id.history_time);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mGameName.getText() + "'";
        }
    }
}
