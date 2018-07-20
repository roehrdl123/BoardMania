package at.johannesrohr.boardmania.AddNewPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import at.johannesrohr.boardmania.Data.Player;
import com.johannesrohr.boardmania.R;

import java.util.List;

import io.realm.Realm;

public class AddPlayerActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((ImageView)findViewById(R.id.avatar_Player_add)).setImageResource(R.drawable.avatarteacher);
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

    public void addPlayer(View view)
    {
        EditText text = (EditText) findViewById(R.id.name_Player_add);
        if(text.getText().toString().equals(""))
        {
            Toast.makeText(AddPlayerActivity.this,"No Name!",Toast.LENGTH_SHORT).show();
            return;
        }

       Realm r = Realm.getDefaultInstance();
       List<Player> players = r.where(Player.class).findAll();

       for(Player p : players)
       {
           if(p.getName().equals(text.getText().toString()))
           {
               Toast.makeText(AddPlayerActivity.this,"Name already taken!",Toast.LENGTH_LONG).show();
               return;
           }
       }

       r.beginTransaction();
       Player player = new Player();
       player.setName(text.getText().toString());
       player.setAvatarIcon(R.drawable.avatarteacher);

       r.copyToRealmOrUpdate(player);

       r.commitTransaction();

       finish();
    }
}
