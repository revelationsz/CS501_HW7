package com.example.w7_ch5_p25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.ZoneId;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private AutoCompleteTextView tv;
    Button submit;
    ArrayList<String> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager( this );
        submit = findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstName = findViewById(R.id.firstNameText);
                EditText lastName = findViewById(R.id.lastNameText);
                EditText email = findViewById(R.id.TextEmailAddress);

                user newUser = new user(email.getText().toString(), firstName.getText().toString(), lastName.getText().toString());
                System.out.println(newUser);
                System.out.println(dbManager.selectAll());
                dbManager.insert(newUser);
                updateAdapter();
            }
        });



        tv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        updateAdapter();
    }


    public void updateAdapter(){ emails = dbManager.selectAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,emails);
        System.out.println(adapter);
        tv.setAdapter(adapter);
    }

}