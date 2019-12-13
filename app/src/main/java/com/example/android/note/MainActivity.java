package com.example.android.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.note.Room.Item;
import com.example.android.note.Room.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private List<Item> list;
    private MainActivityViewModel mViewModel;

    // TAG for Logging
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG,"_ActivityInit");

        // init views and adapter
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        fab = (FloatingActionButton) findViewById(R.id.add_btn);
        fab.setOnClickListener(this);
        list = new ArrayList<>();
        list.add(new Item("au ghah","huhu"));
        adapter = new RecyclerViewAdapter(list);

        // set adapter and layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        // set view model
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        LiveData c=mViewModel.getListItems(getApplicationContext());
        c.observe(this,o -> {

        });

    }

    @Override
    public void onClick(View v) {
        // go to add activity
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
