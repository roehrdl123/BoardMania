package at.johannesrohr.boardmania.PlayerChooser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import at.johannesrohr.boardmania.Data.Player;
import com.johannesrohr.boardmania.R;
import at.johannesrohr.boardmania.TicTacToeGame.TicTacToeActivity;

import java.util.List;

import io.realm.Realm;

public class PlayerChooserActivity extends AppCompatActivity
{
    private RecyclerView recView;
    private Bundle gameBundle;
    private List<Player> player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gameBundle = getIntent().getExtras();


        Realm realm = Realm.getDefaultInstance();

        player = realm.where(Player.class).findAll();

        realm.beginTransaction();

        for(Player p: player)
        {
            p.setSelected(false);
        }

        realm.commitTransaction();

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
            Realm r = Realm.getDefaultInstance();
            r.beginTransaction();
            for(Player play:player)
            {
                play.setSelected(false);
            }
            r.commitTransaction();

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
