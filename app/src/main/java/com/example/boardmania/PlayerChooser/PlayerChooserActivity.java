package com.example.boardmania.PlayerChooser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.boardmania.Data.Player;
import com.example.boardmania.R;
import com.example.boardmania.TicTacToeGame.TicTacToeActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class PlayerChooserActivity extends AppCompatActivity
{
    List<String> items = new ArrayList<>();
    ArrayAdapter<String> adapterP1, adapterP2;
    Spinner spinnerPlayer1, spinnerPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser);

        Realm realm = Realm.getDefaultInstance();
        List<Player> player = realm.where(Player.class).findAll();

        for (Player p:player)
        {
            items.add(p.getName());
        }
        spinnerPlayer1 = (Spinner) findViewById(R.id.spinnerPlayer1);
        adapterP1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        adapterP1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayer1.setAdapter(adapterP1);




        spinnerPlayer2 = (Spinner) findViewById(R.id.spinnerPlayer2);
        adapterP2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        adapterP2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayer2.setAdapter(adapterP2);
    }

    public void selectPlayer(View view)
    {
        Intent intent = new Intent(this, TicTacToeActivity.class);
        intent.putExtra("namePlayer1", items.get(spinnerPlayer1.getSelectedItemPosition()));
        intent.putExtra("namePlayer2", items.get(spinnerPlayer2.getSelectedItemPosition()));
        startActivity(intent);
    }

}
