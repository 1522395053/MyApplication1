package com.example.javatest.shejimoshi.guanchazhe2;

import java.util.LinkedList;

public class Subject2Impl implements Subject2<String> {
    private final LinkedList<Observer2<String>> linkedList = new LinkedList<>();

    @Override
    public void register(Observer2<String> observer) {
        unregister(observer);
        linkedList.add(observer);
    }

    @Override
    public void unregister(Observer2<String> observer) {
        linkedList.remove(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer2<String> observer : linkedList) {
            observer.update("发送：泛型-字符串类型数据");
        }


    }


}
