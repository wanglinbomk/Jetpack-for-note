package com.example.lifeofnote.db.type;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao //数据库操作的接口
public interface MoneyTypeDao {

    @Insert
    void insertTypes(MoneyTypeEntity... moneyTypeEntities);

    @Query("SELECT * FROM MONEYTYPEENTITY WHERE type = (:type)")
    LiveData<List<MoneyTypeEntity>> getTypeList(int type);

    @Query("SELECT * FROM MONEYTYPEENTITY ORDER BY ID ASC")
    LiveData<List<MoneyTypeEntity>> getAllTypeList();

}

