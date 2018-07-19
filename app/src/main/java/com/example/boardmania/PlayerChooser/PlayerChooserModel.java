package com.example.boardmania.PlayerChooser;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.boardmania.Data.Player;
import com.example.boardmania.R;

import java.util.List;

public class PlayerChooserModel
{
    private Button b;

    public PlayerChooserModel(Button b)
    {
        this.b = b;
    }

    public boolean onItemClick(Player p,View view, List<Player> mValues)
    {
        p.setSelected(true);
        int i = 0;
        CheckBox box = (CheckBox) view.findViewById(R.id.Player_CheckBox);
        box.setChecked(true);
        for (Player player:mValues)
        {
            if(player.isSelected())
            {
                i++;
            }
        }
        if(i == 2)
        {
            b.setEnabled(true);
            return false;
        }
        return true;
    }
}
