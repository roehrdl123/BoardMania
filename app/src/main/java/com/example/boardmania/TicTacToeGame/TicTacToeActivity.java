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
    TicTacToeModel model;
    private Context mContext;
    private Activity mActivity;
    private ArrayAdapter<String> adapter;
    TextView p1TextView, p2TextView, p1PointView, p2PointView;

    String namePlayer1, namePlayer2;

    private CoordinatorLayout mCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        Bundle bundle = getIntent().getExtras();


        model = new TicTacToeModel();
        model.initArray();

        model.setNames(bundle);

        mContext = getApplicationContext();
        mActivity = TicTacToeActivity.this;

        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        gridview = (GridView) findViewById(R.id.gridview);

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
                    if(model.isFinished())
                    {
                        String result = model.getWinner().equals("draw") ? model.getWinner() : model.getWinner()+" won";
                        Toast.makeText(TicTacToeActivity.this, result, Toast.LENGTH_SHORT).show();
                        updateTextViews();
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


        p1TextView = (TextView) findViewById(R.id.Player1_TextView);
        p2TextView = (TextView) findViewById(R.id.Player2_TextView);
        p1PointView = (TextView) findViewById(R.id.Player1_PointsView);
        p2PointView = (TextView) findViewById(R.id.Player2_PointsView);


        updateTextViews();
    }

    public void restart(View view)
    {
        model.restart();
        adapter.notifyDataSetChanged();
    }

    private void updateTextViews()
    {

        p1TextView.setText(model.getPlayer1Name());
        p1PointView.setText("Matches won: "+model.getPlayer1wins());
        p2TextView.setText(model.getPlayer2Name());
        p2PointView.setText("Matches won: "+model.getPlayer2wins());
    }
}
