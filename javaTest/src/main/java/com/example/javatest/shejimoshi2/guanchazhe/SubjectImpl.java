package com.example.javatest.shejimoshi2.guanchazhe;

import com.example.javatest.shejimoshi.model.People;
import com.example.javatest.shejimoshi.model.Student;
import com.example.javatest.shejimoshi.model.StudentSub1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubjectImpl implements Subject {
    private final LinkedList<Observer> linkedList = new LinkedList<>();

    @Override
    public void register(Observer observer) {
        unregister(observer);
        linkedList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        linkedList.remove(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer observer : linkedList) {
            observer.update("字符串obj");
        }


    }

    private void m1() {
        List<? extends People> list = new ArrayList<>();
        People people = list.get(0);
    }

    private void m2() {
        List<? super Student> list = new ArrayList<>();
        list.add(new Student());
        list.add(new StudentSub1());

        Object object = list.get(0);
    }
}
