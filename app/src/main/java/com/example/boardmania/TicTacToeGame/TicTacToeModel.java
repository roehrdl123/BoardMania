package com.example.boardmania.TicTacToeGame;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel
{
    private boolean player1sTurn = true;
    private boolean playable = true;
    private boolean player1Beginner = true;
    private String player1 = "Player 1", player2 = "Player 2";
    private List<String> fields;
    String winner;
    boolean end;

    public void initArray()
    {
        fields = new ArrayList<>();
        for (int i = 0; i < 9; i++)
        {
            fields.add("");
        }
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
            String errorMessage = fields.get(position).equals("X") ? player1 : player2;
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
            winner = player1sTurn ? player1 : player2;
        }

        else if(!fields.contains(""))
        {
            winner = "draw";
            end = true;
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
        return winner;
    }

    public boolean isFinished()
    {
        return end;
    }

}
