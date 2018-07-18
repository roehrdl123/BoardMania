package com.example.boardmania.PlayerChooser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.boardmania.Data.Player;
import com.example.boardmania.R;
import com.example.boardmania.TicTacToeGame.TicTacToeActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class PlayerChooserActivity extends AppCompatActivity
{
    private RecyclerView Lview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser);

        Realm realm = Realm.getDefaultInstance();
        List<Player> player = realm.where(Player.class).findAll();


        Lview = (RecyclerView) findViewById(R.id.listPlayerChooser);
        PlayChooserAdapter adapter = new PlayChooserAdapter(player);
        Lview.setAdapter(adapter);


    }


    public void selectPlayer(View view)
    {

    }

    public void start(View view)
    {
        Intent intent = new Intent(this, TicTacToeActivity.class);
        //intent.putExtra("namePlayer1", selectedItems.get(0));
        //intent.putExtra("namePlayer2", selectedItems.get(1));
        startActivity(intent);

    }

}
