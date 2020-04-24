package com.example.android.note.Room;


import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    private static ItemDatabase itemDatabase;
    public abstract ItemDao itemDao();
    public static synchronized ItemDatabase getInstance( Context context){
        if (itemDatabase==null){
            itemDatabase= Room
                    .databaseBuilder(context.getApplicationContext(),ItemDatabase.class,"item_database").
                    fallbackToDestructiveMigration()
                    .build();
        }
        return itemDatabase;
    }

}