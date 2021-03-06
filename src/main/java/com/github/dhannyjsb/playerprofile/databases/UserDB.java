package com.github.dhannyjsb.playerprofile.databases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "UserDB")
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
    protected Integer status;

    @DatabaseField()
    protected Integer equipment_show;

    @DatabaseField()
    protected Integer allow_mail;

    @DatabaseField()
    protected Integer is_online;

    @DatabaseField()
    protected Timestamp logout_time;

    @DatabaseField()
    protected Boolean allow_friend_add;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setPlayer_first_join(Long player_first_join) {
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

    public void setIs_online(Integer is_online) {
        this.is_online = is_online;
    }

    public void setLogout_time(Timestamp logout_time) {
        this.logout_time = logout_time;
    }

    public void setAllow_friend_add(Boolean allow_friend_add) {
        this.allow_friend_add = allow_friend_add;
    }

    public Integer getDesc_show() {
        return desc_show;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getPlayer_name() {
        return player_name;
    }

    public Integer getEquipment_show() {
        return equipment_show;
    }

    public Integer getAllow_mail() {
        return allow_mail;
    }

    public Boolean getAllow_friend_add() {
        return allow_friend_add;
    }


    public String getPlayer_description() {
        return player_desc;
    }


    public Integer getStatus() {
        return status;
    }
}
