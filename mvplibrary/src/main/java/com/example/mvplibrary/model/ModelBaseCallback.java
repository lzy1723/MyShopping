package com.example.mvplibrary.model;


public interface ModelBaseCallback<T> {
    void onSucess(T t);
    void OnError(String msg, int code);
    void onCancle();
}
