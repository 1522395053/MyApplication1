package com.example.javatest.shejimoshi;

import com.example.javatest.shejimoshi.guanchazhe.Observer;
import com.example.javatest.shejimoshi.guanchazhe.ObserverImpl;
import com.example.javatest.shejimoshi.guanchazhe.Subject;
import com.example.javatest.shejimoshi.guanchazhe.SubjectImpl;
import com.example.javatest.shejimoshi.guanchazhe2.Observer2;
import com.example.javatest.shejimoshi.guanchazhe2.Observer2Impl;
import com.example.javatest.shejimoshi.guanchazhe2.Subject2;
import com.example.javatest.shejimoshi.guanchazhe2.Subject2Impl;

public class TestJ {
    public static void main(String[] args) {
        testSubject();
        System.out.println("========================");

        testSubject2();
    }
    private static void testSubject(){
        Subject subject = new SubjectImpl();


        Observer observer = new ObserverImpl(subject);



        subject.notifyObservers();
    }
    private static void testSubject2(){
        Subject2<String> subject2 = new Subject2Impl();


        Observer2<String> observer2 = new Observer2Impl(subject2);



        subject2.notifyObservers();
    }
}
