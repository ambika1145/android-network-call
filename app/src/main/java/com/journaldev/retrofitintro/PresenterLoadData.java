package com.journaldev.retrofitintro;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.journaldev.retrofitintro.pojo.DataModel;
import com.journaldev.retrofitintro.pojo.ResponseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PresenterLoadData {

    Context context;
    LoadData loadData;
    APIInterface apiInterface;

    public PresenterLoadData(Context context, LoadData loadData) {
        this.context = context;
        this.loadData = loadData;
    }

    public void LoadNeworkData(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String displayResponse = "";

                ResponseModel responseModel = response.body();
                String json = new Gson().toJson(response.body() );
                try {
                    JSONObject obj = new JSONObject(json);
                    JSONArray jsonArray = obj.getJSONArray("rows");

                    ArrayList<DataModel> playersList= (ArrayList<DataModel>) new  Gson().fromJson(jsonArray.toString(),
                            new TypeToken<ArrayList<DataModel>>() {
                            }.getType());

                    loadData.loaddata(playersList);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });

    }
    public void showProgressBar(boolean show){
        loadData.DisplayProgressBar(show);
    }
}
