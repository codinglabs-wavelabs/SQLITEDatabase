package com.example.s.sqlitedatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView signUp;
    Button login;
    EditText userName;
    EditText passWord;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHandler = new DataBaseHandler(this);
        signUp = findViewById(R.id.tv_login_signup);
        login = findViewById(R.id.btn_login_signin);
        userName = findViewById(R.id.et_login_username);
        passWord = findViewById(R.id.et_login_pass);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = userName.getText().toString();
                String pass = passWord.getText().toString();
                String retPass =  dataBaseHandler.retrieveData(uName);
                if(pass.equals(retPass)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Success");
                    builder.setMessage("UserName and Password Matches. You Have Successfully LoggedIn");
                    builder.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(),"You Said Thank You",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"UserName and Password doesn't Match",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
