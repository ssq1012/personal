package com.example.demo.pojo;

public class SingleLoon {
    private static SingleLoon singleLoon = null;
    private SingleLoon(){}

    public static SingleLoon getInstance(){
        if(null == singleLoon){
            return new SingleLoon();
        }
        return singleLoon;
    }
}
