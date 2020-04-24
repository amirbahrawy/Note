package com.example.android.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.note.Room.Item;
import com.example.android.note.Room.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText body;
    private String title_name;
    private String body_name;
    public static FloatingActionButton fab;
    private MainActivityViewModel mainActivityViewModel;
    private Intent intent;
    private final String TAG = this.getClass().getName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Log.e(TAG,"_ActivityInit");
        setTitle("Add Note");
        // init vars
        title = (EditText) findViewById(R.id.title);
        body = (EditText) findViewById(R.id.body);
        fab = (FloatingActionButton) findViewById(R.id.add_btn);
        // set listener to FAB
        fab.setOnClickListener(this);
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        intent=getIntent();
        if(intent.getIntExtra("selected_id",1)!=-1){
         setTitle("Edit Note");
        title_name=intent.getStringExtra("selected_title");
        body_name=intent.getStringExtra("selcted_body");
        title.setText(title_name);
        body.setText(body_name);
        }
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "_onClickInvoked");
        title_name=title.getText().toString();
        body_name=body.getText().toString();
        Item item=new Item(title_name,body_name);
        int id=intent.getIntExtra("selected_id",1);
        if(title_name.isEmpty())
            title.setError("Can't be Empty");
        else if(body_name.isEmpty())
            body.setError("Can't be Empty");
        else if(id==-1){

                mainActivityViewModel.insert(item);
                Toast.makeText(AddActivity.this,"Record Added", Toast.LENGTH_SHORT).show();
            // get back to main activity
            finish();
        }
        else {
            item.setId(id);
            mainActivityViewModel.update(item);
            Toast.makeText(AddActivity.this,"Record Updated", Toast.LENGTH_SHORT).show();
            // get back to main activity
            finish();
        }

    }
}
