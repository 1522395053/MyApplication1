package com.example.javatest.fanxing;

// generics/LinkedStack.java
// 用链式结构实现的堆栈

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;

        Node() { item = null; next = null; }

        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>();  // 栈顶

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<>();
        for (String s : "Phasers on stun!".split(" ")) {
            lss.push(s);
        }
        String s;
        while ((s = lss.pop()) != null) {
            System.out.println(s);
        }
    }
}



  class Exercise17 {
//    private static Random rand = new Random(47);
    private static Random rand = new Random(1000);
    void test(){
        int i = rand.nextInt(20);
        System.out.println(i);
    }
    public static void main(String [] args) {
        Exercise17 e17= new Exercise17();
        for (int i = 0; i < 20; i++) {
            e17.test();
        }
    }
}


class TestMethod{
    public static <T extends Comparable> T[] newArray(int length, T... a) {
        T[] mm = (T[]) Array.newInstance(a.getClass().getComponentType(), length);

        return mm;
    }

    public static <T extends Comparable> T newInstace(Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return t;
    }


    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }
}

class Person{

}



class Student extends Person{

}
class Teacher extends Person{

}
class Police extends Person{

}


class StudentSub1 extends Student{

}
class StudentSub2 extends Student{

}


class TestExtendSuper{

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();

        List<Person> personList = new ArrayList<>();


        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Police> policeList = new ArrayList<>();




        List<StudentSub1> studentSub1s = new ArrayList<>();



        testExtendsParams(students);
        testExtendsParams(teachers);
        testExtendsParams(policeList);
        testExtendsParams(personList);

        //===============================================


        Class<Object[]> aClass = Object[].class;
        Class<String[]> Stringclass = String[].class;

        testSuperParams(personList);
        testSuperParams(students);
        testSuperParams(objectList);
//        testSuperParams(studentSub1s);


        List<? extends Person> extendsPersonList ;
        extendsPersonList = new ArrayList<Student>();
        extendsPersonList = new ArrayList<Teacher>();
        extendsPersonList = new ArrayList<Police>();
        extendsPersonList = new ArrayList<Person>();


        List<? super Student> superStudentList;

        superStudentList = new ArrayList<Student>();
        superStudentList = new ArrayList<Person>();
        superStudentList = new ArrayList<Object>();



    }


    public static void testExtendsParams(List<? extends Person> list){

    }


    public static void testSuperParams(List<? super Student> list){
        list.add(new Student());
        list.add(new StudentSub1());
        list.add(new StudentSub2());
    }


    public static void testExtends(){
        List<? extends Person> people = new ArrayList<>();
        /*
         *  Required type: capture of ? extends Person
         *  Provided: Person
         */
//        people.add(new Person());

        Person person = people.get(0);
    }


    public static void testSuper(){
        List<? super Student> list = new ArrayList<>();
        list.add(new Student());
        /*
         *  Required type: capture of ? super Student
         *  Provided: Person
         */
//        list.add(new Person());
        /*
         *  Required type: capture of ? super Student
         *  Provided: Object
         */
//        list.add(new Object());
        list.add(new StudentSub1());
        list.add(new StudentSub2());

        Object object = list.get(0);
    }
}


interface Filter<E> {
    public boolean test(E element);

    //根据传入的filter过滤器过滤列表并返回被过滤的元素
//    public static <E> List<E> removeIf(List<E> list, Filter<? super E> filter) {
//        List<E> removeList = new ArrayList<>();
//        for(E e : list) {
//            if(filter.test(e)) {
//                removeList.add(e);
//            }
//        }
//        list.removeAll(removeList);
//        return removeList;
//    }

//    public static void main(String[] args) {
//        List<Double> doubleList = new ArrayList<Double>();
//
//
//        Filter<Double> filter = new Filter<Double>() {
//            @Override
//            public boolean test(Double element) {
//                return element > 100;
//            }
//        };
//
//
//        Filter<Number> filter2 = new Filter<Number>() {
//            @Override
//            public boolean test(Number element) {
//                return element.doubleValue() > 100;
//            }
//        };
//
//
////        removeIf(doubleList, filter);
//    }
}

  class GenericCovariance
{
    public static void main(String[] args)
    {
        List<Integer> intList = new ArrayList<>();
        intList.add(2);
        intList.add(4);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(2.1);
        doubleList.add(4.3);

        // List<? extends Number>支持协变，
        // 因此只要元素是Number子类的List集合，就可以赋值给numList集合
        List<? extends Number> numList = intList;  // ①
        // 取出的元素被当成Number处理
        Number n1 = numList.get(0);   // ②
        System.out.println(n1);

        // List<? extends Number>支持协变，
        // 因此只要元素是Number子类的List集合，就可以赋值给numList集合
        List<? extends Number> numList2 = doubleList; // ①
        // 取出的元素被当成Number处理
        Number n2 = numList2.get(0);  // ②
        System.out.println(n2);
    }
}



class Apple<T>
{
    private T info;
    public Apple(T info) {
        this.info = info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public T getInfo() {
        return this.info;
    }
}

class GenericCovariance2 {
    public static void main(String[] args) {
        // 指定泛型T为Integer类型
        Apple<Integer> intApp = new Apple<>(2);

        // 协变
        Apple<? extends Number> numApp = intApp;

        // 协变的泛型，调用以泛型为返回值的方法，正确。
        // 该方法的返回值是T，该T总是Number类或其子类
        Number n = numApp.getInfo();
        System.out.println(n);

        // 协变的泛型，不能调用以泛型为参数的方法，编译报错
        // 因此编译器只能确定T必须是Number的子类，但具体是哪个子类则无法确定，因此编译出错
//        numApp.setInfo(3);  // ① TODO TEST
    }
}



// generics/CovariantArrays.java

class Fruit {}

class Apple1 extends Fruit {}

class Jonathan extends Apple1 {}

class Orange extends Fruit {}
 class CovariantArrays {

    public static void main(String[] args) {
        Fruit[] fruit = new Apple1[10];
        fruit[0] = new Apple1(); // OK
        fruit[1] = new Jonathan(); // OK
        // Runtime type is Apple[], not Fruit[] or Orange[]:
        try {
            // Compiler allows you to add Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            // Compiler allows you to add Oranges:
            fruit[0] = new Orange(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
/* Output:
java.lang.ArrayStoreException: Fruit
java.lang.ArrayStoreException: Orange
*/
