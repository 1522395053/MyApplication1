package com.example.javatest.shejimoshi.guanchazhe;

public interface Subject{
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();
}
