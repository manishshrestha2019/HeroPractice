package com.e.heropractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.HeroesAPI;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEmployee extends AppCompatActivity {
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    private EditText etName, etDesc;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee2);

        etName = findViewById(R.id.etEmployeeName);
        etDesc = findViewById(R.id.etDescrpt);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save();

            }
        });

    }

//    private void Save() {
//        String name = etName.getText().toString();
//        String desc = etDesc.getText().toString();
//
//        Heroes heroes = new Heroes(name, desc);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);
//
//        Call<Void> heroesCall = heroesAPI.addHero(heroes);
//
//        heroesCall.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(AddEmployee.this, "Code"+ response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Toast.makeText(AddEmployee.this,"Added",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(AddEmployee.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}

