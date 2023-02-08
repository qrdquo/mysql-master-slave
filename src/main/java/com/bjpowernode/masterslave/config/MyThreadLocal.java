package com.bjpowernode.masterslave.config;

public class MyThreadLocal {
    public static final ThreadLocal<String> local = new ThreadLocal<String>();
    public static String getDataSource(){
       return local.get();
    }
    public static void setDataSource(String dataSource){
        local.set(dataSource);
    }
}
