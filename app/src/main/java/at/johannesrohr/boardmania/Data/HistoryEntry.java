package at.johannesrohr.boardmania.Data;

import android.support.annotation.NonNull;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HistoryEntry extends RealmObject implements Comparable<HistoryEntry>
{
    private Game game;
    private Date time;
    private Player p1, p2;
    private Player winner;
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

    public Player getWinner()
    {
        return winner;
    }

    public void setWinner(Player winner)
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

    @Override
    public int compareTo(@NonNull HistoryEntry historyEntry)
    {
        return this.getTime().compareTo(historyEntry.getTime()) * (-1);
    }
}
