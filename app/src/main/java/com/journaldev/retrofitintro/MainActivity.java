package com.journaldev.retrofitintro;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.journaldev.retrofitintro.pojo.DataModel;
import com.journaldev.retrofitintro.pojo.ResponseModel;
import com.journaldev.retrofitintro.pojo.adaper.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    TextView responseText;
    APIInterface apiInterface;
    RecyclerView recyclerViewSettings;
    List<DataModel> resultList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(APIInterface.class);
         recyclerViewSettings = findViewById(R.id.recyclerview);

         resultList= new ArrayList<>();
        /**
         GET List Resources
         **/
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

                    ArrayList<DataModel> playersList= (ArrayList<DataModel>) fromJson(jsonArray.toString(),
                            new TypeToken<ArrayList<DataModel>>() {
                            }.getType());



                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(playersList,MainActivity.this);
                    recyclerViewSettings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    recyclerViewSettings.setAdapter(recyclerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });

    }
    public static  List<DataModel> getObjectList(String jsonString,Class<DataModel> cls){
        List<DataModel> list = new ArrayList<DataModel>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    class NameList{
        List<DataModel> list;
        //getter and setter

        public List<DataModel> getList() {
            return list;
        }

        public void setList(List<DataModel> list) {
            this.list = list;
        }
    }
    public static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }
}
