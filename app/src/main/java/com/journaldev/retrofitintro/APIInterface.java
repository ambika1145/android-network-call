package com.journaldev.retrofitintro;

import com.journaldev.retrofitintro.pojo.DataModel;
import com.journaldev.retrofitintro.pojo.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ambika on 13/01/19.
 */

interface APIInterface {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<ResponseModel> doGetListResources();


}
