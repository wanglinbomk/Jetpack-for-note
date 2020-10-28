package com.example.lifeofnote.db.create;

import androidx.room.Entity;

import java.util.List;

@Entity
public class CreateGroupEntity {
    private String showDay;
    private DateBean dateBean;

    static class DateBean{
        List<CreateEntity> createEntities;

        public List<CreateEntity> getCreateEntities() {
            return createEntities;
        }

        public void setCreateEntities(List<CreateEntity> createEntities) {
            this.createEntities = createEntities;
        }
    }

    public String getShowDay() {
        return showDay;
    }

    public void setShowDay(String showDay) {
        this.showDay = showDay;
    }

    public DateBean getDateBean() {
        return dateBean;
    }

    public void setDateBean(DateBean dateBean) {
        this.dateBean = dateBean;
    }


}
