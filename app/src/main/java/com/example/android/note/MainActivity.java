package com.example.android.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.note.Room.Item;
import com.example.android.note.Room.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewAdapter.OnNoteListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private MainActivityViewModel mViewModel;

    // TAG for Logging
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Notes");
        Log.e(TAG,"_ActivityInit");

        // init views and adapter
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        adapter = new RecyclerViewAdapter(this);
        // set adapter and layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        // set view model
        recyclerView.setAdapter(adapter);
        mViewModel=ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mViewModel.getAllNotes().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                adapter.setData(items);
            }
        });
    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mViewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
            Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
        }
    }).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        // go to add activity
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("selected_id",-1);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete_all_notes:
                mViewModel.deleteAll();
                Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onNoteClick(Item item) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("selected_id",item.getId());
        intent.putExtra("selected_title", item.getTitle());
        intent.putExtra("selcted_body",item.getBody());
        startActivity(intent);
    }
}
