package com.example.android.note.Room;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private LiveData<List<Item>> allNotes;
    private ItemRepository itemRepository;
    private final String TAG = this.getClass().getName();


    public MainActivityViewModel(Application application) {
        super(application);
        itemRepository=new ItemRepository(application);
        allNotes=itemRepository.getAllNotes();
    }
    public void insert(Item item){
        itemRepository.insert(item);
    }
    public void update(Item item){
        itemRepository.update(item);
    }
    public void delete(Item item){
        itemRepository.delete(item);
    }
    public void deleteAll(){
        itemRepository.deleteAllNotes();
    }
    public LiveData<List<Item>> getAllNotes(){
        return allNotes;
    }

}