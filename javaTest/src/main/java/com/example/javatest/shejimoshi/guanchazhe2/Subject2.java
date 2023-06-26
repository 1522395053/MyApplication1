package com.example.javatest.shejimoshi.guanchazhe2;

public interface Subject2<T> {
    void register(Observer2<T> observer);
    void unregister(Observer2<T> observer);
    void notifyObservers();
}
