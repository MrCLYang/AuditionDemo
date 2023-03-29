package com.example.auditiondemo.SingletonModelDemo;
/****
 * 双重锁模式
 * */
public class Singleton1 {
    private Singleton1() {}
    private volatile static Singleton1 singleton1;
    public static Singleton1 getInstance(){
        if(singleton1==null){
            synchronized (Singleton1.class){
                if(singleton1==null){
                    singleton1=new Singleton1();
                }
            }
        }
        return singleton1;
    }
}
