package com.example.javatest.shejimoshi.guanchazhe;

public class ObserverImpl implements Observer{
    private final Subject subject;

    public ObserverImpl(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
    }


    @Override
    public void update(Object obj) {
        System.out.println("收到了 obj：" + obj);
    }
}
