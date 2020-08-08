package com.example.lifeofnote.entity;

public class CratePayEntity {

    private int icon;
    private int iconSelect;
    private String name;
    private boolean isSelect;

    public CratePayEntity(int icon, String name, boolean isSelect, int iconSelect) {
        this.icon = icon;
        this.iconSelect = iconSelect;
        this.name = name;
        this.isSelect = isSelect;
    }

    public int getIconSelect() {
        return iconSelect;
    }

    public void setIconSelect(int iconSelect) {
        this.iconSelect = iconSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
