package com.example.lifeofnote.db.time;

public class SelectTimeEntity {
    private String name;
    private boolean isShowing = false;

    public SelectTimeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }
}

