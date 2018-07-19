package at.johannesrohr.boardmania.Data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Game extends RealmObject
{
    @PrimaryKey
    private String name;

    private Integer icon;

    public Integer getIcon()
    {
        return icon;
    }

    public void setIcon(Integer icon)
    {
        this.icon = icon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
