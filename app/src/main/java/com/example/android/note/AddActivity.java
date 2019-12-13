package com.example.android.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.note.Room.Item;
import com.example.android.note.Room.RepositoryImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText body;
    private FloatingActionButton fab;

    private final String TAG = this.getClass().getName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Log.e(TAG,"_ActivityInit");

        // init vars
        title = (EditText) findViewById(R.id.title);
        body = (EditText) findViewById(R.id.body);
        fab = (FloatingActionButton) findViewById(R.id.add_btn);

        // set listener to FAB
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "_onClickInvoked");
        RepositoryImpl.getInstance(getApplicationContext()).addItem(new Item(title.getText().toString(), body.getText().toString()));
        // show toast
        Toast.makeText(AddActivity.this,"Record Added", Toast.LENGTH_SHORT).show();
        // get back to main activity
        finish();
    }
}
