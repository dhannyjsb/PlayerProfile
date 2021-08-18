package com.github.dhannyjsb.playerprofile.databases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "UsersDB")
public class UserDB {
    @DatabaseField(generatedId = true)
    protected Integer id;

    @DatabaseField()
    protected String uuid;

    @DatabaseField()
    protected String player_name;

    @DatabaseField()
    protected Long player_first_join;

    @DatabaseField()
    protected String player_desc;

    @DatabaseField()// Integer artinya 1-3 = 1. Public 2. Friend 3. Disable
    protected Integer desc_show;

    @DatabaseField()
    protected Integer equipment_show;

    @DatabaseField()
    protected Integer allow_mail;

    @DatabaseField()
    protected Boolean allow_friend_add;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setPlayer_first_join(long player_first_join) {
        this.player_first_join = player_first_join;
    }

    public void setPlayer_desc(String player_desc) {
        this.player_desc = player_desc;
    }

    public void setDesc_show(Integer desc_show) {
        this.desc_show = desc_show;
    }

    public void setEquipment_show(Integer equipment_show) {
        this.equipment_show = equipment_show;
    }

    public void setAllow_mail(Integer allow_mail) {
        this.allow_mail = allow_mail;
    }

    public void setAllow_friend_add(Boolean allow_friend_add) {
        this.allow_friend_add = allow_friend_add;
    }


}
