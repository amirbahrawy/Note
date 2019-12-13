package com.example.android.note.Room;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class RepositoryImpl implements Repository{

    private static RepositoryImpl repository = new RepositoryImpl();
    private ItemDatabase itemDB;
    private final String TAG = this.getClass().getName();

    // operations constants
    private static final int INSERT_OPERATION = 0;
    private static final int DELETE_OPERATION = 1;
    private static Context coontext;
    // constructor
    private RepositoryImpl(){
        initDB();
    }

    public static Repository getInstance(Context context){
        coontext=context;
        return repository;

    }

    private void initDB() {
        Log.e(TAG, "_DataBaseInit");
        itemDB = Room.databaseBuilder(coontext,
                ItemDatabase.class, "ItemDatabase").build();
    }

    @Override
    public void addItem(Item item) {
        Log.e(TAG, "_ItemIsAddedToDB");
        new DataBaseOperation(item,INSERT_OPERATION).execute();
    }

    @Override
    public void deleteItem(Item item) {
        Log.e(TAG, "_ItemIsDeletedFromDB");
        itemDB.itemDao().delete(item);
    }

    @Override
    public LiveData<List<Item>> getItems() {
        Log.e(TAG, "_GetItemsFromDB");
        return itemDB.itemDao().getItems();
    }

    // Async
    class DataBaseOperation extends AsyncTask<Void,Void,Void> {

        public Item item;
        public int operation;

        public DataBaseOperation(Item item, int operation){
            this.item = item;
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e(TAG, "_doInBackgroundInvoked");
            // INSERT operation
            if (operation == INSERT_OPERATION) itemDB.itemDao().insert(item);
            // else delete ...
            return null;
        }

    }

}