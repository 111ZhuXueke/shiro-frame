package com.rui.web.common.io;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 合并流
 * @author : zhuxueke
 * @since : 2017-12-28 13:15
 **/
public class SequenceInputStreamTest {

    public static void main(String[] args){
        sequenceStreamUsage();
    }
    static void sequenceStreamUsage(){
        SequenceInputStream sequenceInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try{
            Vector<InputStream> list = new Vector<>();
            list.addElement(new FileInputStream("D:/File/example.txt"));
            list.addElement(new FileInputStream("D:/File/example1.txt"));
            list.addElement(new FileInputStream("D:/File/example2.txt"));
            list.addElement(new FileInputStream("D:/File/example3.txt"));
            Enumeration<InputStream> enumeration = list.elements();
            sequenceInputStream = new SequenceInputStream(enumeration);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:/File/example4.txt"));
            byte[] bytes = new byte[1024];
            int len =0;
            while((len = sequenceInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes,0 ,len);
                bufferedOutputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (sequenceInputStream != null)
                    sequenceInputStream.close();

                if (bufferedOutputStream != null)
                    bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
