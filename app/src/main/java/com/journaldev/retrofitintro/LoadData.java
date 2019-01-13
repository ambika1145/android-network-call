package com.journaldev.retrofitintro;

import com.journaldev.retrofitintro.pojo.DataModel;

import java.util.List;

public interface LoadData {

   void loaddata(List<DataModel> loaddata);


   void DisplayProgressBar(boolean show);
}
