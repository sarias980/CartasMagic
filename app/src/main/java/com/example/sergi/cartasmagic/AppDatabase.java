package com.example.sergi.cartasmagic;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Sergi on 01/12/2017.
 */

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract CartasDAO getMovieDao();


}
