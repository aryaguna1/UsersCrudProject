package com.aryaguna.userscrudproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aryaguna.userscrudproject.adapter.UsersAdapter;
import com.aryaguna.userscrudproject.model.GetUser;
import com.aryaguna.userscrudproject.model.User;
import com.aryaguna.userscrudproject.rest.ApiClient;
import com.aryaguna.userscrudproject.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnUpdate, btnDelete;
    ApiInterface mApiInterface;
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeleteActivity.class));
            }
        });
    }

    public void refresh(){
        Call<GetUser> userCall = mApiInterface.getUser();
        userCall.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                progressDialog.dismiss();
                List<User> userList = response.body().getData();
                Log.d("Retrofit Get", "Jumlah data user "+String.valueOf(userList.size()));
                mAdapter = new UsersAdapter(userList);
                mRecycleView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}