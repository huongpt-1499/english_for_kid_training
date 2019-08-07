package com.huucong.englishforkid.screen;

public interface BasePresenter<T>  {
    void setView(T view);
    void onStart();
    void onStop();
}
