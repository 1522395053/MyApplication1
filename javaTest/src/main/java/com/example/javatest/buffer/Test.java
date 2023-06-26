package com.example.javatest.buffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        testFloatBuffer();
    }
    public static void testFloatBuffer(){
        FloatBuffer b1 = FloatBuffer.allocate(10);//10是容量，创建一个容量为10的FloatBuffer对象
        System.out.println(Arrays.toString(b1.array()));
        int position = b1.position();
        System.out.println("position:"+position);// 0
        // 添加数据
        b1.put(11);

        // 获取当前位置索引
        position = b1.position();
        System.out.println("position:"+position);// 1

        // 设置当前位置索引
        b1.position(5);

        b1.put(22);
        b1.put(33);
        System.out.println("position:"+b1.position());// 7
        System.out.println(Arrays.toString(b1.array()));
        // 打印结果:[11, 0, 0, 0, 0, 22, 33, 0, 0, 0]
    }

    public static void testByteBuffer(){
        ByteBuffer b1 = ByteBuffer.allocate(10);//10是容量，创建一个容量为10的ByteBuffer对象
        System.out.println(Arrays.toString(b1.array()));
        int position = b1.position();
        System.out.println("position:"+position);// 0
        // 添加数据
        b1.put((byte)11);

        // 获取当前位置索引
        position = b1.position();
        System.out.println("position:"+position);// 1

        // 设置当前位置索引
        b1.position(5);

        b1.put((byte)22);
        b1.put((byte)33);
        System.out.println("position:"+b1.position());// 7
        System.out.println(Arrays.toString(b1.array()));
        // 打印结果:[11, 0, 0, 0, 0, 22, 33, 0, 0, 0]
    }
}



