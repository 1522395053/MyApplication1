package com.example.javatest.shejimoshi.guanchazhe2;

public class Observer2Impl implements Observer2<String> {
    private final Subject2<String> subject;

    public Observer2Impl(Subject2<String> subject) {
        this.subject = subject;
        this.subject.register(this);
    }

    @Override
    public void update(String s) {
        System.out.println("收到了 泛型字符串类型数据：" + s);
    }
}
