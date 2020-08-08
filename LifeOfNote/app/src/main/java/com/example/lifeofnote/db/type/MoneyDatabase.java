package com.example.lifeofnote.db.type;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MoneyTypeEntity.class, version = 1, exportSchema = false)
public abstract class MoneyDatabase extends RoomDatabase {
    private static MoneyDatabase INSTANCE;

    static synchronized MoneyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MoneyDatabase.class, "type_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract MoneyTypeDao getMoneyTypeDao();
}
