package com.rui.web.common.io;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * DataInputStream DataOutStream 一般不用于存储对象数据，而是存储对象的成员数据
 * @author : zhuxueke
 * @since : 2017-12-28 10:42
 **/
public class DataStream {
    public static void main(String[] args){
        new DataStream().dataOutInputStream(args);
    }
    /**
     * 将Member对象的成员数据写入文件，并还原成Member对象
     * @author : zhuxueke
     * @since : 2017/12/28 10:45
     */
    void dataOutInputStream(String[] args){
        Member[] members = {
                new Member("曹操"),
                new Member("孙权"),
                new Member("刘备")
        };
        try{
            //args[0] 直接使用会抛出异常,因为args默认为null
            //idea 中edit configuration 设置Programe agruments为当前类的全路径，例如：D:\rui-control\src\main\java\com\rui\web\common\io\DataStream
            //DataOutputStream 会创建一个DataStream文件
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(args[0]));
            for (Member item:members) {
                dataOutputStream.writeUTF(item.getName());
            }
            //数据已经到达目的地
            dataOutputStream.flush();
            dataOutputStream.close();

            Member[] newMembers = new Member[members.length];
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(args[0]));
            for (int i = 0; i < newMembers.length; i++) {
                newMembers[i] = new Member(dataInputStream.readUTF());
            }
            dataInputStream.close();
            for (Member item:newMembers) {
                System.out.println(item.getAge() + " " + item.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Member{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member(String name){
        this.name = name;
    }
}