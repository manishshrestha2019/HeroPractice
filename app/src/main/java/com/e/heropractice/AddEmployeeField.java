package com.e.heropractice;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.HeroesAPI;
import model.ImageResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class AddEmployeeField extends AppCompatActivity {
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    private EditText etNameField, etDescField;
    private Button btnAddField;
    private ImageView ivImageProfile;
    String imagePath;
    String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_field);

        etNameField = findViewById(R.id.etNameField);
        etDescField = findViewById(R.id.etDescField);
        btnAddField = findViewById(R.id.btnAddField);
        ivImageProfile = findViewById(R.id.ivImageProfile);

        btnAddField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFieldMap();

            }
        });
        ivImageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
    }

//    private void addField() {
//        String name = etNameField.getText().toString();
//        String desc = etDescField.getText().toString();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);
//        Call<Void> heroesCall = heroesAPI.addHeroField(name, desc);
//
//        heroesCall.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(AddEmployeeField.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Toast.makeText(AddEmployeeField.this, "Successfully Added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(AddEmployeeField.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//
//
//        });
//    }

    //Using @FieldMap
    private void addFieldMap() {
        SaveImageOnly();
        String name = etNameField.getText().toString();
        String desc = etDescField.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("desc", desc);
        map.put("image",imageName);


        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);

        Call<Void> heroesCall = heroesAPI.addHeroFieldMap(map);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddEmployeeField.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddEmployeeField.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEmployeeField.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();

            }
        }
        Uri uri = data.getData();
        imagePath = getRealPathFromUri(uri);
        previewImage(imagePath);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void previewImage(String imagePath) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ivImageProfile.setImageBitmap(myBitmap);
        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    private void SaveImageOnly(){
        File file =new File(imagePath);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);

        HeroesAPI heroesAPI= Url.getInstance().create(HeroesAPI.class);
        Call<ImageResponse> responseBodyCall=heroesAPI.uploadImage(body);
        StrictMode();

        try{
            Response<ImageResponse> imageResponseResponse=responseBodyCall.execute();
            imageName=imageResponseResponse.body().getFilename();

        }catch (IOException e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}


