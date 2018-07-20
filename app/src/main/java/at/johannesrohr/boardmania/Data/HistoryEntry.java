package at.johannesrohr.boardmania.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HistoryEntry extends RealmObject
{
    private Game game;
    private Date date;
    private Date time;
    private Player p1, p2;
    private String winner;
    private RealmList<Turn> turns;

    public Player getP1()
    {
        return p1;
    }

    public void setP1(Player p1)
    {
        this.p1 = p1;
    }

    public Player getP2()
    {
        return p2;
    }

    public void setP2(Player p2)
    {
        this.p2 = p2;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public List<Turn> getTurns()
    {
        return turns;
    }

    public void setTurns(RealmList<Turn> turns)
    {
        this.turns = turns;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }
}
