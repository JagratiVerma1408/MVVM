package com.example.mvvm.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.Model.NameModel;
import com.example.mvvm.Respitory.Repo;

import java.util.List;


public class NameViewModel extends ViewModel {
    MutableLiveData<List<NameModel>> liveData;


    public void init (Context context){
        if (liveData!=null){
            return;
        }
        liveData= Repo.getInstance().getNamedata();
    }
    public MutableLiveData<List<NameModel>> getNameModel(){
        return liveData;
    }
}
