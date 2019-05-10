package com.example.creditsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView l;
    MyDBadapter helper;

    String [] tt;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyDBadapter(this);


        //helper.insertData();
        result=helper.getallData();
        int n=result.length();

        tt=result.split("\n");

        l=(ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tt);
        l.setAdapter(adapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value = adapter.getItem(position);
                Toast.makeText(MainActivity.this,value+" User Selected ",Toast.LENGTH_LONG).show();
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("user_name",value);

                startActivity(i);


            }


        });


    }
    }

