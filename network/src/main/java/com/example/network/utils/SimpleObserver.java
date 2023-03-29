package com.example.network.utils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SimpleObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NotNull Disposable d) {

    }

    @Override
    public void onNext(@NotNull T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(@NotNull Throwable e) {

    }
}
