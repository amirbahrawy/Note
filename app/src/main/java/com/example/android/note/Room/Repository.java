package com.example.android.note.Room;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {
    void addItem(Item item);
    void deleteItem(Item item);
    LiveData<List<Item>> getItems();
}
