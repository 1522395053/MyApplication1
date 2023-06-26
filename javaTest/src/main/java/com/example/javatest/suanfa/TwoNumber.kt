package com.example.javatest.suanfa

class TwoNumber {
    fun getIndex(arr:IntArray,sum:Int):IntArray{
        for (i in   (0 until arr.size - 1)){
            for (j in   (i+1 until arr.size)){
                if (arr[i] + arr[j] == sum)
                    return intArrayOf(i,j)
            }
        }

        return intArrayOf()
    }
}

