package com.thecocasio.headache;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseTable db;
    int countText_Int;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView countText = findViewById(R.id.countmaldecrane);

        final Button add = findViewById(R.id.add_button);
        add.setOnClickListener(v -> {
            // Code here executes on main thread after user presses button

            countText_Int = Integer.parseInt((String) countText.getText());


            countText_Int = countText_Int +1;
            DatabaseTable myDB = new DatabaseTable(MainActivity.this);
            myDB.SetCounter(countText_Int);

            countText.setText(String.valueOf(countText_Int));

        });

        final Button dis = findViewById(R.id.dis);
        dis.setOnClickListener(v -> {
                // Code here executes on main thread after user presses button

                countText_Int = Integer.parseInt((String) countText.getText());
                countText_Int = countText_Int-1;

                DatabaseTable myDB = new DatabaseTable(MainActivity.this);
                myDB.SetCounter(countText_Int);

                countText.setText(String.valueOf(countText_Int));

        });

        final Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(v -> {
            // Code here executes on main thread after user presses button
            countText_Int = Integer.parseInt((String) countText.getText());
            countText_Int = 0;

            DatabaseTable myDB = new DatabaseTable(MainActivity.this);
            myDB.SetCounter(countText_Int);

            countText.setText(String.valueOf(countText_Int));

        });

        db = new DatabaseTable(MainActivity.this);

        displayData(countText);

    }

    void displayData(TextView countText){
        Cursor cursor = db.ReadAllData();
        if (cursor.getCount() == 0 ){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                countText_Int = cursor.getInt(0);
                countText.setText(String.valueOf(countText_Int));
            }
        }
    }

}