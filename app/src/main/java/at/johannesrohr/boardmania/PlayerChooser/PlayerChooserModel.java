package at.johannesrohr.boardmania.PlayerChooser;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import at.johannesrohr.boardmania.Data.Player;
import com.johannesrohr.boardmania.R;

import java.util.List;

public class PlayerChooserModel
{
    private Button b;

    public PlayerChooserModel(Button b)
    {
        this.b = b;
    }

    public void onItemClick(Player p,View view, List<Player> mValues)
    {
        p.setSelected(!p.isSelected());
        int i = 0;
        CheckBox box = (CheckBox) view.findViewById(R.id.Player_CheckBox);
        box.setChecked(!box.isChecked());
        for (Player player:mValues)
        {
            if(player.isSelected())
            {
                i++;
            }
        }

        b.setEnabled(i==2);
    }
}
