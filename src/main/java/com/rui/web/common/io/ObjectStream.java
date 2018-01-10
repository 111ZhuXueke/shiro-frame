package com.rui.web.common.io;

import java.io.*;

/**
 * ObjectInputStream ObjectOutputStream 允许读取/写入自定义的类 该类必须实现Serializable接口作为标记
 * @author : zhuxueke
 * @since : 2017-12-28 10:31
 **/
public class ObjectStream {
    public static void main(String[] agrs){
        objectOutInputStream();
    }

    static void objectOutInputStream(){
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            String path = "D:/File/example.txt";
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(new Student(10,"孔融"));
            objectOutputStream.writeObject(new Student(11,"司马迁"));
            objectOutputStream.writeObject(new Student(12,"匡衡"));
            objectInputStream = new ObjectInputStream(new FileInputStream(path));
            objectInputStream = new ObjectInputStream(new FileInputStream(path));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectInputStream.readObject());
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try{
                objectInputStream.close();
                objectOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

class Student implements Serializable{
    private Integer age;
    private String name;

    public Student(Integer age,String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}