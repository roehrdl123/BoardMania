package com.example.boardmania.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.boardmania.Data.Player;
import com.example.boardmania.R;

import io.realm.Realm;

public class PlayersFragment extends Fragment
{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private PlayersRecyclerViewAdapter adapter;

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

        if (view instanceof RecyclerView)
        {
            Realm realm = Realm.getDefaultInstance();

            adapter = new PlayersRecyclerViewAdapter(realm.where(Player.class).findAll());
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

}
