package com.example.android.note.Room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ItemRepository {
    private ItemDao itemDao;
    private ItemDatabase itemDatabase;
    private LiveData<List<Item>> allNotes;
    public ItemRepository(Application application) {
        itemDatabase=itemDatabase.getInstance(application);
        itemDao=itemDatabase.itemDao();
        allNotes=itemDao.getItems();

    }
    public void insert(Item item){
        new insertAsyncTask().execute(item);
    }
    public LiveData<List<Item>> getAllNotes(){
        return allNotes;
    }
    public void update(Item item){
        new updateAsyncTask().execute(item);
    }
    public void delete(Item item){
        new deleteAsyncTask().execute(item);
    }
    public void deleteAllNotes(){
        new deleteAllAsyncTask().execute();
    }
    private class insertAsyncTask extends android.os.AsyncTask<Item,Void,Void> {

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }
    private class updateAsyncTask extends android.os.AsyncTask<Item,Void,Void> {

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.update(items[0]);
            return null;
        }
    }
    private class deleteAsyncTask extends AsyncTask<Item,Void,Void> {

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }
    private class deleteAllAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.deleteAll();
            return null;
        }
    }
}
