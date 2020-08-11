package com.example.lifeofnote.db.create;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CreateEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String day;
    private String time;
    private int type;
    private double money;
    private String typeName;
    private String tip;

    public CreateEntity(String day, String time, int type, double money, String typeName, String tip) {
        this.day = day;
        this.time = time;
        this.type = type;
        this.money = money;
        this.typeName = typeName;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
