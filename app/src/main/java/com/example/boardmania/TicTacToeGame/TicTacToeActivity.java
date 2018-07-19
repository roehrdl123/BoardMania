package com.example.boardmania.TicTacToeGame;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boardmania.R;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity
{
    TicTacToeModel model;
    private Context mContext;
    private Activity mActivity;
    private ArrayAdapter<String> adapter;
    TextView p1NameView, p1WonView, p2NameView, p2WonView;
    GridView gridview;
    CheckBox player1Box, player2Box;

    String namePlayer1, namePlayer2;

    private CoordinatorLayout mCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();


        model = new TicTacToeModel();

        model.setBundle(bundle);

        mContext = getApplicationContext();
        mActivity = TicTacToeActivity.this;

        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        gridview = (GridView) findViewById(R.id.gridview);
        player1Box = (CheckBox)findViewById(R.id.Player1_turn);
        player2Box = (CheckBox)findViewById(R.id.Player2_turn);

        if(model.getPlayer1Turn())
        {

            player1Box.setChecked(true);
            player2Box.setChecked(false);
        }
        else
        {
            player2Box.setChecked(true);
            player1Box.setChecked(false);
        }


        adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,model.getArray())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView tv_cell = (TextView) super.getView(position,convertView,parent);

                tv_cell.setGravity(Gravity.CENTER);
                tv_cell.getLayoutParams().height = 500;
                tv_cell.setBackgroundResource(R.drawable.grid_items_border);

                return tv_cell;
            }
        };
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                try
                {
                    if(!model.isPlayable())
                    {
                        return;
                    }
                    model.onClick(position);
                    if(model.getPlayer1Turn())
                    {
                        player1Box.setChecked(true);
                        player2Box.setChecked(false);
                    }
                    else
                    {
                        player2Box.setChecked(true);
                        player1Box.setChecked(false);
                    }
                    if(model.isFinished())
                    {
                        String result = model.getWinner().equals("draw") ? model.getWinner() : model.getWinner()+" won";
                        Toast.makeText(TicTacToeActivity.this, result, Toast.LENGTH_SHORT).show();
                        updateTextViews();
                        player1Box.setChecked(false);
                        player2Box.setChecked(false);
                    }
                    else
                    {
                        Toast.makeText(TicTacToeActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                }
                catch(Exception e)
                {
                    Toast.makeText(TicTacToeActivity.this, "This field is already taken by " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        p1NameView = (TextView) findViewById(R.id.Player1_Name);
        p1NameView.setText(model.getPlayer1Name());
        p2NameView = (TextView) findViewById(R.id.Player2_Name);
        p2NameView.setText(model.getPlayer2Name());
        p1WonView = (TextView) findViewById(R.id.Player1_Wins);
        p1WonView.setText(model.getPlayer1wins()+" wins");
        p2WonView = (TextView) findViewById(R.id.Player2_Wins);
        p2WonView.setText(model.getPlayer2wins()+" wins");

        ImageView imgViewP1 = (ImageView) findViewById(R.id.player1_Avatar_game);
        imgViewP1.setImageResource(model.getPlayer1Avatar());
        ImageView imgViewP2 = (ImageView) findViewById(R.id.player2_Avatar_game);
        imgViewP2.setImageResource(model.getPlayer2Avatar());
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

    public void restart(View view)
    {
        model.restart();
        if(model.getPlayer1Turn())
        {
            player1Box.setChecked(true);
            player2Box.setChecked(false);
        }
        else
        {
            player2Box.setChecked(true);
            player1Box.setChecked(false);
        }
        adapter.notifyDataSetChanged();
    }

    private void updateTextViews()
    {
        p1WonView.setText(model.getPlayer1wins()+" wins");
        p2WonView.setText(model.getPlayer2wins()+" wins");
    }
}
