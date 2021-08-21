package com.github.dhannyjsb.playerprofile.databases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "RequestFriendDB")
public class RequestFriendDB {
    @DatabaseField(generatedId = true)
    protected Integer id;

    @DatabaseField()
    protected String request_uuid;

    @DatabaseField()
    protected String target_uuid;

    @DatabaseField()
    protected Timestamp time;
}
