package com.example.boardmania.TicTacToeGame;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.boardmania.R;

public class TicTacToeAdapter extends BaseAdapter
{
        private Context mContext;

        public TicTacToeAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return 9;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            Button btn;
            if (convertView == null)
            {
                btn = new Button(mContext);
                btn.setLayoutParams(new GridView.LayoutParams(100, 55));
                btn.setPadding(8, 8, 8, 8);
            }
            else
            {
                btn = (Button) convertView;
            }


            btn.setTextColor(Color.WHITE);
            btn.setId(position);

            return btn;
        }
}

