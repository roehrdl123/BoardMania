package at.johannesrohr.boardmania.AddNewPlayer;

import android.graphics.Color;
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
    Integer selectedAvatar;
    Integer resource1, resource2, resource3, resource4;
    ImageView view1, view2, view3, view4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resource1 = R.drawable.avatarteacher;
        resource2 = R.drawable.avatarboy;
        resource3 = R.drawable.avatarbusinessman;
        resource4 = R.drawable.avatarcoder;

        (view1 = (ImageView)findViewById(R.id.avatar_Player_add1)).setImageResource(resource1);
        (view2 = (ImageView)findViewById(R.id.avatar_Player_add2)).setImageResource(resource2);
        (view3 = (ImageView)findViewById(R.id.avatar_Player_add3)).setImageResource(resource3);
        (view4 = (ImageView)findViewById(R.id.avatar_Player_add4)).setImageResource(resource4);
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

        if(selectedAvatar == null)
        {
            Toast.makeText(AddPlayerActivity.this, "No Avatar!", Toast.LENGTH_SHORT);
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
       player.setAvatarIcon(selectedAvatar);

       r.copyToRealmOrUpdate(player);

       r.commitTransaction();

       finish();
    }

    public void selectAvatar(View view)
    {
        Integer i = view.getId();
        view1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        view2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        view3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        view4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        switch(i)
        {
            case R.id.avatar_Player_add1: view1.setBackgroundColor(Color.parseColor("#0000ff"));
                                          selectedAvatar = resource1;
                                         break;
            case R.id.avatar_Player_add2: view2.setBackgroundColor(Color.parseColor("#0000ff"));
                                          selectedAvatar = resource2;
                                          break;
            case R.id.avatar_Player_add3: view3.setBackgroundColor(Color.parseColor("#0000ff"));
                                          selectedAvatar = resource3;
                                          break;
            case R.id.avatar_Player_add4: view4.setBackgroundColor(Color.parseColor("#0000ff"));
                                          selectedAvatar = resource4;
                                          break;
            default: break;
       }
    }
}
