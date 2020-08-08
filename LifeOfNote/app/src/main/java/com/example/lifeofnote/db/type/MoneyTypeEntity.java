package com.example.lifeofnote.db.type;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class MoneyTypeEntity {

    /**
     * type = -1 支出类型
     * type = 0  全部类型
     * type = 1 收入
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int type;
    private String name;

    @Ignore
    private boolean showIng;

    public boolean isShowIng() {
        return showIng;
    }

    public void setShowIng(boolean showIng) {
        this.showIng = showIng;
    }

    public MoneyTypeEntity(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
