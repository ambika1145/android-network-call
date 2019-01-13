package com.journaldev.retrofitintro.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {

    @SerializedName("rows")
    public ArrayList rows;

    public ArrayList<DataModel> getResponse() {
        return rows;
    }

    public void setResult(ArrayList<DataModel> response) {
        this.rows = response;
    }
}
