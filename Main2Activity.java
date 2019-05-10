package com.example.creditsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView t1;
    EditText e1;
    Button b1;
    Spinner spin;
    String a,result,no,selected,aselected;
    String[] transfer_users,uset;
    MyDBadapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        helper = new MyDBadapter(this);
        t1=(TextView)findViewById(R.id.textView2);
        Intent i=getIntent();
        a = i.getStringExtra("user_name");
        uset=a.split(" ");
        t1.setText(uset[0]);
        aselected=uset[0];


        spin=(Spinner)findViewById(R.id.spinner);
        b1=(Button)findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.editText);
        result=helper.getData();
        transfer_users=result.split("\n");

        ArrayAdapter aa = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_spinner_item, transfer_users);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no=e1.getText().toString();
                int n=Integer.parseInt(no);

                // helper.update(aselected,selected,n);

                helper.insert_data(aselected,selected,no);
               // String r=helper.getcheckData();
                //Toast.makeText(Main2Activity.this,r,Toast.LENGTH_LONG).show();
                Toast.makeText(Main2Activity.this,"Successfully transfered credits",Toast.LENGTH_LONG).show();
                Intent i2=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i2);

            }
        });



    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        selected=transfer_users[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
