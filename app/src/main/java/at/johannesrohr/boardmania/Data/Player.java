package at.johannesrohr.boardmania.Data;

import android.graphics.Color;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Player extends RealmObject
{
    @PrimaryKey
    private String name;
    private boolean selected = false;


    private Integer avatarIcon;
    private boolean type;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAvatarIcon()
    {
        return avatarIcon;
    }

    public void setAvatarIcon(Integer avatarIcon)
    {
        this.avatarIcon = avatarIcon;
    }

    public boolean isType()
    {
        return type;
    }

    public void setType(boolean type)
    {
        this.type = type;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
