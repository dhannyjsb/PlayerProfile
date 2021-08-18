package com.github.dhannyjsb.playerprofile.databases;

import com.j256.ormlite.field.DatabaseField;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "FriendDB")
public class FriendDB {
    @DatabaseField(generatedId = true)
    protected Integer id;

    @DatabaseField()
    protected String user_uuid;

    @DatabaseField()
    protected String friend_uuid;

}
