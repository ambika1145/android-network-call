package com.journaldev.retrofitintro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.journaldev.retrofitintro.pojo.DataModel;
import com.journaldev.retrofitintro.pojo.ResponseModel;
import com.journaldev.retrofitintro.adaper.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoadData {

    TextView responseText;
    RecyclerView recyclerViewSettings;
    List<DataModel> resultList;
    Context context;
    ProgressBar progressBar;
    PresenterLoadData presenterLoadData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
         recyclerViewSettings = findViewById(R.id.recyclerview);
         resultList= new ArrayList<>();
         progressBar= findViewById(R.id.progressbar);
        presenterLoadData = new PresenterLoadData(context,this);
          presenterLoadData.showProgressBar(true);
          presenterLoadData.LoadNeworkData();



    }


    @Override
    public void loaddata(List<DataModel> loaddata) {
        presenterLoadData.showProgressBar(false);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(loaddata,MainActivity.this);
        recyclerViewSettings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewSettings.setAdapter(recyclerAdapter);

    }
    @Override
    public void DisplayProgressBar(boolean show) {

        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

   }
