package com.example.network.listener;

public interface CallBackList<T> {
    void onSuccess(String method,T content);
    void onFailure(String method,String error);
}
