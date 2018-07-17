package com.example.boardmania.TicTacToeGame;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boardmania.R;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity
{
    private Context mContext;
    private Activity mActivity;
    private List<String> fields;
    private ArrayAdapter<String> adapter;
    private boolean player1sTurn = true;
    private boolean playable = true;
    private boolean player1Beginner = true;
    private String player1 = "Player 1", player2 = "Player 2";

    private CoordinatorLayout mCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        GridView gridview = (GridView) findViewById(R.id.gridview);


        /////von da
        mContext = getApplicationContext();
        mActivity = TicTacToeActivity.this;
        fields = new ArrayList<>();
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");
        fields.add("");

        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        gridview = (GridView) findViewById(R.id.gridview);

        adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,fields)
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
        //////bis da

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if (playable)
                {
                    if (fields.get(position).isEmpty())
                    {
                        if (player1sTurn)
                        {
                            fields.set(position, "X");
                            checkIfWon(position);
                            player1sTurn = !player1sTurn;
                        }
                        else
                        {
                            fields.set(position, "O");
                            checkIfWon(position);
                            player1sTurn = !player1sTurn;
                        }
                    }
                    else
                    {
                        String errorMessage;
                        switch (fields.get(position))
                        {
                            case "X":
                                errorMessage = player1;
                                break;
                            case "O":
                                errorMessage = player2;
                                break;
                            default:
                                errorMessage = "Field is EMPTY!!";
                                break;
                        }
                        Toast.makeText(TicTacToeActivity.this, "This field is already taken by " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void restart(View view)
    {
        playable = true;
        for(int i = 0; i < fields.size(); i++)
        {
            fields.set(i,"");
        }
        adapter.notifyDataSetChanged();
    }

    private void checkIfWon(int position)
    {
        boolean end = false;

        if((!fields.get(0).isEmpty() && fields.get(0).equals(fields.get(4)) && fields.get(0).equals(fields.get(8)))
                || (!fields.get(0).isEmpty() && fields.get(0).equals(fields.get(1)) && fields.get(0).equals(fields.get(2)))
                || (!fields.get(0).isEmpty() && fields.get(0).equals(fields.get(3)) && fields.get(0).equals(fields.get(6)))
                || (!fields.get(1).isEmpty() && fields.get(1).equals(fields.get(4)) && fields.get(1).equals(fields.get(7)))
                || (!fields.get(2).isEmpty() && fields.get(2).equals(fields.get(5)) && fields.get(2).equals(fields.get(8)))
                || (!fields.get(3).isEmpty() && fields.get(3).equals(fields.get(4)) && fields.get(3).equals(fields.get(5)))
                || (!fields.get(6).isEmpty() && fields.get(6).equals(fields.get(7)) && fields.get(6).equals(fields.get(8)))
                || (!fields.get(2).isEmpty() && fields.get(2).equals(fields.get(4)) && fields.get(2).equals(fields.get(6))))
        {
            end = true;
            if(player1sTurn)
            {
                Toast.makeText(TicTacToeActivity.this, player1+" won", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(TicTacToeActivity.this, player2+" won", Toast.LENGTH_SHORT).show();
            }
        }

        else if(!fields.contains(""))
        {
            Toast.makeText(TicTacToeActivity.this, "draw", Toast.LENGTH_SHORT).show();
            end = true;
        }
        if(end)
        {
            playable = false;
            if(player1Beginner)
            {
                player1sTurn= false;
            }
            else
            {
                player1sTurn = true;
            }
            player1Beginner = !player1Beginner;
        }
        else
        {
            Toast.makeText(TicTacToeActivity.this, "" + position, Toast.LENGTH_SHORT).show();
        }
    }
}
