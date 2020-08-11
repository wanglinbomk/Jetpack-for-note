package com.example.lifeofnote.db.create;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = CreateEntity.class, version = 1, exportSchema = false)
public abstract class CreateDatabase extends RoomDatabase {

    private static CreateDatabase INSTANCE;

    static synchronized CreateDatabase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CreateDatabase.class, "type_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract CreateDao getCreateDao();
}
