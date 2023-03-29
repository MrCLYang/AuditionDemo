package com.example.auditiondemo.ViewModelDemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> name;

    public MutableLiveData<String> getName() {
        if(name==null){
            name=new MutableLiveData<>();
        }
        return name;
    }


}
