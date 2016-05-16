package com.swanand.stethodemo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_fname,et_lname,et_city;
    private Button button_insert;
    private String fname,lname,city;
    DBAdapter dbAdapter=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_insert=(Button)findViewById(R.id.btn_insert);
        et_fname=(EditText)findViewById(R.id.editText1);
        et_lname= (EditText) findViewById(R.id.editText2);
        et_city=(EditText)findViewById(R.id.editText3);

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname=et_fname.getText().toString();
                lname=et_lname.getText().toString();
                city=et_city.getText().toString();

                dbAdapter.open();
                long l= dbAdapter.insertRecord(fname,lname,city);
                if(l!=-1)
                {
                    Toast.makeText(getApplicationContext(),"Record inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
