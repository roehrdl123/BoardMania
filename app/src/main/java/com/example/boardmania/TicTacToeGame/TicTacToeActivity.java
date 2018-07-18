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

    private CoordinatorLayout mCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        GridView gridview = (GridView) findViewById(R.id.gridview);


        model = new TicTacToeModel();
        model.initArray();

        /////von da
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
        //////bis da

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                try
                {
                    model.onClick(position);
                    if(model.isFinished())
                    {
                        String result = model.getWinner().equals("draw") ? model.getWinner() : model.getWinner()+" won";
                        Toast.makeText(TicTacToeActivity.this, result, Toast.LENGTH_SHORT).show();
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
    }

    public void restart(View view)
    {
        model.restart();
        adapter.notifyDataSetChanged();
    }
}
