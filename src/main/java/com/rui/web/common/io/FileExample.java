package com.rui.web.common.io;

import java.io.*;

/**
 * File 例子
 * @author : zhuxueke
 * @since : 2017-12-28 9:48
 **/
public class FileExample {
    public static void main(String[] agrs){
        copyFile();
    }
    public static void createFile(){
        File f = new File("D:/File/example.txt");
        try{
            f.createNewFile();
            System.out.println("该分区大小" + f.getTotalSpace()/(1024*1024*1024)+"G");
            System.out.println("父目录路径：" + f.getParent());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 统计文件字节数
     * @author : zhuxueke
     * @since : 2017/12/28 9:56
     */
    static void getFileByteCount(){
        int count = 0;
        InputStream inputStream = null;
        try{
            String path = "D:/File/image/jvm.jpg";
            inputStream = new FileInputStream(new File(path));
            while (inputStream.read() != -1)
                count++;
            System.out.println(Math.round(count / 1024) + "KB");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制文件
     * @author : zhuxueke
     * @since : 2017/12/28 10:22
     */
    static void copyFile(){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            //每次取出字节数
            byte[] bytes = new byte[1024];
            int num = 0;
            String target = "D:/File/image/jvm.jpg";
            String copyed = "D:/File/image/jvm1.jpg";
            inputStream = new FileInputStream(new File(target));
            outputStream = new FileOutputStream(new File(copyed));
            while ((num = inputStream.read(bytes)) != -1)
                outputStream.write(bytes,0,num);
            System.out.println("ok!");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                outputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
