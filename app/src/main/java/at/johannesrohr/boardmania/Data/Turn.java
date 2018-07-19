package at.johannesrohr.boardmania.Data;

import io.realm.RealmObject;

public class Turn extends RealmObject
{
    private Player player;
    private int col;
    private int row;

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }
}
