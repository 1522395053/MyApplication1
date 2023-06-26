package com.example.javatest.shejimoshi2.guanchazhe;

public interface Subject {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();
}
