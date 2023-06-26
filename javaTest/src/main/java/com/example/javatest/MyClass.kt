package com.example.javatest

import java.lang.reflect.Field

class MyClass {
}


//fun main(args: Array<String>) {
//
//    val a : Int = 1000
//    println(a == a) //true
//    println(a === a) //true
//    val a1 : Int = a
//    val a2 : Int = a
//    println(a1 == a2) //true
//    println(a1 === a2) //true
//
//}

//fun main(args: Array<String>) {
//    val a : Int = 1000
//    println(a == a) //true
//    println(a === a) //true
//    val a1 : Int? = a
//    val a2 : Int? = a
//    println(a1 == a2) //true
//    println(a1 === a2) //false
//}

//fun main(args: Array<String>) {
//    val a : Int? = 1000
//    println(a == a) //true
//    println(a === a) //true
//    val a1 : Int? = a
//    val a2 : Int? = a
//    println(a1 == a2) //true
//    println(a1 === a2) //true
//}

//fun main(args: Array<String>) {
//    val a : Int = 100
//    println(a == a) //true
//    println(a === a) //true
//    val a1 : Int? = a
//    val a2 : Int? = a
//    println(a1 == a2) //true
//    println(a1 === a2) //true
//}

fun main(args: Array<String>) {
    val user = User()
    val ageField: Field = User::class.java.getDeclaredField("age")
    ageField.isAccessible = true
    val int = ageField.getInt(user)
    println(int)
    println(int === 1 as Int?)
    println(int == 1)
    println("==============================================")


    val cls = User::class.java

    val getIntMethod =
        cls.getMethod("getInt", String::class.java, Int::class.javaPrimitiveType)
    val value:Any? = getIntMethod.invoke(user, "ro.miui.notch", 0)
    println(value)
    println(value == 1)
    println(value == (1 as Int?))
    println(value === (1 as Int?))
    println("==============================================")

    val getBoolMethod =
        cls.getMethod("getBool", String::class.java, Int::class.javaPrimitiveType)
    val value1:Any? = getBoolMethod.invoke(user, "ro.miui.notch", 0)
    println(value1)
    println(value1 == true)
    println(value1 == (true as Boolean?))
    println(value1 === (true as Boolean?))
}

class User{
    val age = 1
    val boolF = true

    fun getInt(string: String,int: Int):Int{
        return 1
    }

    fun getBool(string: String,int: Int):Boolean{
        return true
    }
}
