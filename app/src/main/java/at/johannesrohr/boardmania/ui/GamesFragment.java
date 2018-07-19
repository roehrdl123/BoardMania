package at.johannesrohr.boardmania.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.johannesrohr.boardmania.Data.Game;

import com.johannesrohr.boardmania.R;

import io.realm.Realm;


public class GamesFragment extends Fragment
{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private GamesRecyclerViewAdapter adapter;


    public GamesFragment()
    {
    }


    public static GamesFragment newInstance(int columnCount)
    {
        GamesFragment fragment = new GamesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        if (view instanceof RecyclerView)
        {
            Realm realm = Realm.getDefaultInstance();

            adapter = new GamesRecyclerViewAdapter(realm.where(Game.class).findAll());
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }


        return view;
    }


}
