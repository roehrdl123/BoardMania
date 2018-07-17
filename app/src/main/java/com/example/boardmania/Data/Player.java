package com.example.boardmania.Data;

import android.graphics.Color;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Player extends RealmObject
{
    @PrimaryKey
    private String name;

    private String colorHex;
    private boolean type;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getColorHex()
    {
        return colorHex;
    }

    public void setColorHex(String colorHex)
    {
        this.colorHex = colorHex;
    }

    public boolean isType()
    {
        return type;
    }

    public void setType(boolean type)
    {
        this.type = type;
    }

}
