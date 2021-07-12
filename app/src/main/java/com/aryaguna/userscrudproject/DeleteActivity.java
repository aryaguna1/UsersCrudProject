package com.aryaguna.userscrudproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aryaguna.userscrudproject.model.PostPutDelUser;
import com.aryaguna.userscrudproject.rest.ApiClient;
import com.aryaguna.userscrudproject.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class DeleteActivity extends AppCompatActivity {
    EditText editFirstName, editLastName ,editEmail;
    Button btnDelete, btnBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
            }
        });
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}