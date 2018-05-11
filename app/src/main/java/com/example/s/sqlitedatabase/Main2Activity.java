package com.example.s.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button reg_register;
    EditText reg_name;
    EditText reg_email;
    EditText reg_pass;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dataBaseHandler = new DataBaseHandler(this);
        reg_name = (EditText) findViewById(R.id.et_reg_name);
        reg_email = (EditText) findViewById(R.id.et_reg_email);
        reg_pass = (EditText) findViewById(R.id.et_reg_password);
        reg_register = (Button) findViewById(R.id.btn_reg_submit);


        reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = reg_name.getText().toString();
                String email = reg_email.getText().toString();
                String password = reg_pass.getText().toString();

               long rowId = dataBaseHandler.insertData(name,email,password);

               if(rowId!=-1){
                   Toast.makeText(getApplicationContext(),"Row Inserted",Toast.LENGTH_SHORT).show();
               }

               else {
                   Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}
