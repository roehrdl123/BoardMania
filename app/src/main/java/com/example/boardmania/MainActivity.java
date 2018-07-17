package com.example.boardmania;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.boardmania.Data.Game;
import com.example.boardmania.Data.HistoryEntry;
import com.example.boardmania.Data.Player;
import com.example.boardmania.TicTacToeGame.TicTacToeActivity;
import com.example.boardmania.ui.GamesFragment;
import com.example.boardmania.ui.HistoryFragment;
import com.example.boardmania.ui.PlayersFragment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.main_bottombar);


        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Game gametype = new Game();
        gametype.setIcon(R.drawable.tictactoeicon);
        gametype.setName("TicTacToe");
        realm.copyToRealmOrUpdate(gametype);

        Game g = new Game();
        g.setIcon(R.drawable.viergewinnticon);
        g.setName("4gewinnt");
        realm.copyToRealmOrUpdate(g);


        Player p1 = new Player();
        p1.setName("Hans");

        realm.copyToRealmOrUpdate(p1);

        Player p2 = new Player();
        p2.setName("Alfons");

        realm.copyToRealmOrUpdate(p2);

        HistoryEntry historyEntry = new HistoryEntry();
        historyEntry.setGame(gametype);
        historyEntry.setP1(p1);
        historyEntry.setP2(p2);

        realm.copyToRealmOrUpdate(historyEntry);

        HistoryEntry historyEntry1 = new HistoryEntry();
        historyEntry1.setGame(g);
        historyEntry1.setP1(p1);
        historyEntry1.setP2(p2);

        realm.copyToRealmOrUpdate(historyEntry1);

        realm.commitTransaction();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menu_games:
                        showFragment(GamesFragment.newInstance(1));
                        return true;
                    case R.id.menu_players:
                        showFragment(new PlayersFragment());
                        return true;
                    case R.id.menu_History:
                        showFragment(new HistoryFragment());
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.menu_games);
    }

    private void showFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
    }


    public void onClick(View view)
    {
        Realm realm = Realm.getDefaultInstance();
        Intent intent = new Intent(this, TicTacToeActivity.class);
        //intent.putExtra(realm.where(Player.class).beginsWith("name","Hans"));
        startActivity(intent);
    }
}