package com.example.boardmania.PlayerChooser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.boardmania.Data.Player;
import com.example.boardmania.R;
import com.example.boardmania.TicTacToeGame.TicTacToeActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class PlayerChooserActivity extends AppCompatActivity
{
    private RecyclerView recView;
    private Bundle gameBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gameBundle = getIntent().getExtras();


        Realm realm = Realm.getDefaultInstance();

        List<Player> player = realm.where(Player.class).findAll();

        recView = (RecyclerView) findViewById(R.id.listPlayerChooser);
        PlayerChooserModel model = new PlayerChooserModel((Button)findViewById(R.id.button_Start));
        PlayChooserAdapter adapter = new PlayChooserAdapter(player,model);
        recView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void start(View view)
    {
        Intent intent = new Intent(this, TicTacToeActivity.class);
        Player player1 = null;
        Player player2 = null;

        Realm r = Realm.getDefaultInstance();
        for (Player p: r.where(Player.class).findAll())
        {
            if(p.isSelected())
            {
                if(player1 == null)
                {
                    player1 = p;
                }
                else
                {
                    player2 = p;
                }
            }
        }

        intent.putExtra("Player1", ""+player1.getName());
        intent.putExtra("Player2", ""+player2.getName());
        intent.putExtra("Game",gameBundle);
        startActivity(intent);

    }

}
