package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    SharedPreferences sharedPreferences;
    public static ArrayList<Note> notes = new ArrayList<>();

    DBHelper dbHelper;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                Intent logoutIntent = new Intent(this, MainActivity.class);
                sharedPreferences.edit().remove("username").apply();
                startActivity(logoutIntent);
                return true;
            case R.id.addNote:
                Intent addNoteIntent = new Intent(this, ThirdActivity.class);
                startActivity(addNoteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);

        textView = (TextView) findViewById(R.id.textView);
        String username = sharedPreferences.getString("username", "");
        textView.setText("Welcome " + username);

         context = getApplicationContext();
         sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
         dbHelper = new DBHelper(sqLiteDatabase);

         notes = dbHelper.readNotes(username);

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note: notes){
            displayNotes.add(String.format("Title: %s\nDate: %s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (android.widget.ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });



    }
}