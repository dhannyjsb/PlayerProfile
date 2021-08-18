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
    protected String player_first_join;

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


}
