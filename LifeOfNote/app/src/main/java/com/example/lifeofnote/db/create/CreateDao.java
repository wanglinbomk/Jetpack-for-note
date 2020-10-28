package com.example.lifeofnote.db.create;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CreateDao {

    @Insert
    void insertCreate(CreateEntity... createEntities);

    @Delete
    void deleteCreate(CreateEntity... createEntity);

    @Query("SELECT * FROM CREATEENTITY ORDER BY ID ASC")
    LiveData<List<CreateEntity>> getAllTypeList();

    @Query("SELECT * FROM CREATEENTITY WHERE type = (:type)")
    LiveData<List<CreateEntity>> getCreateByType(int type);

    @Query("SELECT * FROM CREATEENTITY WHERE CREATEENTITY.day BETWEEN (:startTime) AND (:endTime) GROUP BY CREATEENTITY.showDay")
    LiveData<List<CreateGroupEntity>> getHomeDay(int startTime, int endTime);

   /* @Update
    void updateCreate(CreateEntity... createEntity);*/
}
