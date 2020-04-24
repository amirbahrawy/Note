package com.example.android.note.Room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ItemDao {

    @Insert
    void insert(Item item);

    @Update
    void update(Item item);
    @Delete
    void delete(Item item);
    @Query("DELETE FROM Item")
    void deleteAll();

    @Query("SELECT * FROM Item")
    LiveData<List<Item>> getItems();
}
