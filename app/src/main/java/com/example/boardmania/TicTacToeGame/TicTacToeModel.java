package com.example.boardmania.TicTacToeGame;

import android.os.Bundle;

import com.example.boardmania.Data.Game;
import com.example.boardmania.Data.HistoryEntry;
import com.example.boardmania.Data.Player;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class TicTacToeModel
{
    private boolean player1sTurn = true;
    private boolean playable = true;
    private boolean player1Beginner = true;
    private String nameP1, nameP2;
    private Player player1, player2;
    private int player1wins = 0, player2wins = 0;
    private List<String> fields;
    String winner;
    boolean end;
    HistoryEntry myHistory;
    Realm r;

    public TicTacToeModel()
    {
        initArray();
    }

    public void initArray()
    {
        fields = new ArrayList<>();
        for (int i = 0; i < 9; i++)
        {
            fields.add("");
        }
        myHistory = new HistoryEntry();

        r = Realm.getDefaultInstance();
    }

    public List<String> getArray()
    {
        return fields;
    }

    public void onClick(int position) throws Exception
    {
        if (!playable)
        {
            return;
        }


        if (fields.get(position).isEmpty())
        {
            String sign = player1sTurn ? "X" : "O";
            fields.set(position, sign);
            checkIfWon(position);
            player1sTurn = !player1sTurn;
        }
        else
        {
            String errorMessage = fields.get(position).equals("X") ? player1.getName() : player2.getName();
            throw new Exception(errorMessage);
        }
    }

    public void checkIfWon(int position)
    {
        end = false;

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
            winner = player1sTurn ? player1.getName() : player2.getName();
            if(player1sTurn)
            {

                myHistory.setWinner(player1.getName());
                player1wins++;
            }
            else
            {

                myHistory.setWinner(player2.getName());
                player2wins++;
            }
        }

        else if(!fields.contains(""))
        {
            winner = "draw";
            end = true;
            myHistory.setWinner("draw");
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


            r.beginTransaction();
            r.copyToRealm(myHistory);
            r.commitTransaction();
        }

    }

    public void restart()
    {
        playable = true;
        for(int i = 0; i < fields.size(); i++)
        {
            fields.set(i,"");
        }
    }

    public String getWinner()
    {
        return winner;//in Player umwandeln
    }

    public boolean isFinished()
    {
        return end;
    }


    public void setPlayerNames(Bundle bundle)
    {
        if(bundle != null)
        {
            nameP1 = bundle.getString("Player1");
            nameP2 = bundle.getString("Player2");
            setPlayer();
        }
    }

    private void setPlayer()
    {
        List<Player> players = r.where(Player.class).findAll();
        for (Player p:players)
        {
            if (p.getName().equals(nameP1))
            {
                player1 = p;
            }
            else if (p.getName().equals(nameP2))
            {
                player2 = p;
            }

            myHistory.setP1(player1);

            myHistory.setP2(player2);
        }

        myHistory.setGame(r.where(Game.class).beginsWith("name","Tic").findFirst());//Game mitgeben
    }


    public String getPlayer1Name()
    {
        return player1.getName();
    }

    public String getPlayer2Name()
    {
        return player2.getName();
    }

    public int getPlayer1wins()
    {
        return player1wins;
    }

    public int getPlayer2wins()
    {
        return player2wins;
    }

    public boolean isPlayable()
    {
        return playable;
    }

    public Integer getPlayer1Avatar()
    {
        return player1.getAvatarIcon();
    }

    public Integer getPlayer2Avatar()
    {
        return player2.getAvatarIcon();
    }

    public boolean getPlayer1Turn()
    {
        return player1sTurn;
    }
}
