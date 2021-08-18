package com.github.dhannyjsb.playerprofile.databases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MailDB")
public class MailDB {
    @DatabaseField(generatedId = true)
    protected Integer id;

    @DatabaseField()
    protected String from_uuid;

    @DatabaseField()
    protected String from_player_name;

    @DatabaseField()
    protected String to_uuid;

    @DatabaseField()
    protected String to_player_name;

    @DatabaseField()
    protected String subject;

    @DatabaseField()
    protected String body;
}
