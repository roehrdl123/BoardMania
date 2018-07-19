package at.johannesrohr.boardmania.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.johannesrohr.boardmania.AddNewPlayer.AddPlayerActivity;
import at.johannesrohr.boardmania.Data.Player;
import com.johannesrohr.boardmania.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class PlayersFragment extends Fragment
{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private PlayersRecyclerViewAdapter adapter;
    int i = 0;

    public PlayersFragment()
    {
    }

    public static PlayersFragment newInstance(int columnCount)
    {
        PlayersFragment fragment = new PlayersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Player> players = realm.where(Player.class).findAll();
        players.addChangeListener(new RealmChangeListener<RealmResults<Player>>()
        {
            @Override
            public void onChange(RealmResults<Player> players)
            {
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new PlayersRecyclerViewAdapter(players);
        final Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_player);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, AddPlayerActivity.class);
                context.startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
        return view;
    }

}
