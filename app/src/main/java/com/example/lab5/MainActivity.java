package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void loginFunction(View view){
        EditText usernameField = (EditText) findViewById(R.id.editTextUsername);
        String username = usernameField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();
        goToActivity2();

    }

    public void goToActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")){
            String username = sharedPreferences.getString(usernameKey, "");
            goToActivity2();
        }else{
            setContentView(R.layout.activity_main);
        }
    }
}