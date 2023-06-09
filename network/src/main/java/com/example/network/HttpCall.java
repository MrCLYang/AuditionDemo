package com.example.network;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.example.network.listener.CallBackList;
import com.example.network.utils.SimpleObserver;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpCall {
    /** 网络请求结果过滤*/
    public static <T> void doCall(LifecycleOwner owner, Observable<ResponseModel<T>> observable, final CallBackList<T> callBackList,final String flag){
        if(observable==null|| callBackList==null){
            throw new IllegalArgumentException("observable 或callBackList为空");
        }

        /*观察者网络请求状态*/
        SimpleObserver<ResponseModel<T>> observer=new SimpleObserver<ResponseModel<T>>() {
            @Override
            public void onNext(@NotNull ResponseModel<T> responseModel) {
                try{
                    if(responseModel.getCode()==ResponseModel.SUCCESS){
                        callBackList.onSuccess(flag,responseModel.getData());
                    }else {
                        callBackList.onFailure(flag,responseModel.getMsg());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    callBackList.onFailure(flag,"解析错误!");
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                callBackList.onFailure(flag,HttpStatusCode.handleException(e));
            }
        };

        if(owner==null){
            //被观察者订阅观察者，根据生命周期取消订阅，子线程订阅主线程观察
            observable.subscribeOn(Schedulers.newThread())
                    .unsubscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }else {
            observable.subscribeOn(Schedulers.newThread())
                    .unsubscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY)))
                    .subscribe(observer);
        }
    }

    /**
     * 该方法不过滤返回数据
     *

     */
    public static <T> void doCallWithoutIntercept(LifecycleOwner owner, Observable<T> observable, final CallBackList<T> callBackList, final String flag) {

        if (observable == null || callBackList == null) {
            throw new IllegalArgumentException("observable或callBackLis为空");
        }

        //观察者_网络请求状态
        SimpleObserver<T> observer = new SimpleObserver<T>() {

            @Override
            public void onNext(T t) {
                try {
                    if (t != null) {
                        callBackList.onSuccess(flag, t);
                    } else {
                        callBackList.onFailure(flag, "请求数据异常！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callBackList.onFailure(flag, "解析错误！");
                }
            }

            @Override
            public void onError(Throwable e) {
                callBackList.onFailure(flag, HttpStatusCode.handleException(e));
            }

        };

        if (owner == null) {
            observable.subscribeOn(Schedulers.newThread())
                    .unsubscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        } else {
            observable.subscribeOn(Schedulers.newThread())
                    .unsubscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    //AutoDispose通过LifecycleOwner与Activity生命周期关联，页面销毁时取消网络请求订阅
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY)))
                    .subscribe(observer);
        }
    }


}
