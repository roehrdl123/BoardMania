package com.example.boardmania.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HistoryEntry extends RealmObject
{
    private Game game;
    private Date date;
    private Player p1, p2;
    private String winner;
    private boolean draw;
    private boolean finished;
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

    public boolean isDraw()
    {
        return draw;
    }

    public void setDraw(boolean draw)
    {
        this.draw = draw;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
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
}
