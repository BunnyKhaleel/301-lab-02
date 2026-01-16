package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int selected_city = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //buttons and textbox
        Button actual_add = findViewById(R.id.actual_add_button);
        EditText city_add = findViewById(R.id.city_name_textbox);
        Button add = findViewById(R.id.add_button);
        Button delete = findViewById(R.id.delete_button);
        cityList = findViewById(R.id.city_list);

        //list init
        String []cities = {"Edmonton", "Vancouver", "Moscow"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //unlokc add stuff
        actual_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                city_add.setEnabled(true);
                add.setEnabled(true);
            }
        });

        //add button logic
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get typed city
                String city_typed = city_add.getText().toString();
                dataList.add(city_typed);
                cityAdapter.notifyDataSetChanged();
                city_add.setText("");
                city_add.setEnabled(false);
                add.setEnabled(false);
            }
        });

        //select city
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_city = position;
            }
        });

        //remove
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (selected_city != -1) {
                    dataList.remove(selected_city);
                    cityAdapter.notifyDataSetChanged();
                    selected_city = -1;
                }
            }
        });

    }
}