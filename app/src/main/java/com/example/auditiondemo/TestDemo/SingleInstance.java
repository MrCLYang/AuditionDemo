package com.example.auditiondemo.TestDemo;

//单例模式的双重锁写法
public class SingleInstance {
    private volatile static SingleInstance singleInstance;

    private SingleInstance() {
    }

    public static SingleInstance getSingleInstance(){
        if(singleInstance==null){
            synchronized (SingleInstance.class){
                if(singleInstance==null){
                    singleInstance=new SingleInstance();
                }
            }
        }
        return singleInstance;
    }
}
