package com.rui.web.common.io;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

/**
 * 回退流
 * @author : zhuxueke
 * @since : 2017-12-28 13:07
 **/
public class PushBackInputStreamD {
    public static void main(String[] args){
        pushStreamUsage();
    }

    static void pushStreamUsage(){
        String str = "hello,world";
        PushbackInputStream pushbackInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try{
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            pushbackInputStream = new PushbackInputStream(byteArrayInputStream);
            int temp = 0;
            while((temp = pushbackInputStream.read()) != -1){
                if(temp == ','){
                    pushbackInputStream.unread(temp);
                    temp = pushbackInputStream.read();
                    System.out.println("回退字符 " + (char)temp);
                }else{
                    System.out.println((char)temp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
