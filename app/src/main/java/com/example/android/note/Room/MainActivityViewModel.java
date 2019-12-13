package com.example.android.note.Room;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private final String TAG = this.getClass().getName();
    private Context context;
    private LiveData<List<Item>> listItems;

    public LiveData<List<Item>> getListItems(Context context) {
            this.context=context;
        if (listItems == null){
            Log.e(TAG, "_ListItemsIsNULL");
            listItems = new MutableLiveData<List<Item>>();
            loadItemsFromRepository();
        }
        Log.e(TAG, "_ReturningFromViewModel");
        return listItems;
    }

    private void loadItemsFromRepository() {
        Log.e(TAG, "_LoadFromDB");
        listItems = RepositoryImpl.getInstance(context).getItems();
    }
}