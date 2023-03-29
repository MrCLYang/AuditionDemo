package com.example.auditiondemo.SingletonModelDemo;
/** 懒汉式   -----饿汉式
* */
public class Singleton {
    private Singleton(){ }
    private static Singleton single=null;
    public static synchronized Singleton getInstance(){
        if(single==null){
            single=new Singleton();
        }
        return single;
    }
/*
    public class Singleton {
        private static Singleton instance = new Singleton();

        private Singleton() {}

        public static Singleton getInstance() {
            return instance;
        }
    }*/




}
