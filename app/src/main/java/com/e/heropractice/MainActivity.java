package com.e.heropractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    private Button btnAddClass,btnAddField,btnViewHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddClass=findViewById(R.id.btnAddClass);
        btnAddField=findViewById(R.id.btnAddField);
        btnViewHeroes=findViewById(R.id.btnViewHeroes);

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,AddEmployee.class);
                startActivity(intent);
            }
        });
        btnAddField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddEmployeeField.class);
                startActivity(intent);
            }
        });
        btnViewHeroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,ViewHeroes.class);
                startActivity(intent);
            }
        });
    }

}
