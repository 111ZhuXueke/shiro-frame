package com.rui.web.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 用于生成随机字符串
 * @author : zhuxueke
 * @since : 2017-12-13 14:01
 **/
public class RandomUtils {

    private static char[] numberAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    /**
     * 根据指定长度随机生成小写字母
     * @param length 长度
     * @return 指定长度的随机小写字母字符串
     */
    public static String randomLowerWords(int length){
        /*
           0~9的ASCII为48~57
           A~Z的ASCII为65~90
           a~z的ASCII为97~122
         */
        StringBuilder sb = new StringBuilder();
        Random randData = new Random();
        int data = 0;
        for(int i = 0; i < length; i++)
        {
            data=randData.nextInt(26)+97;//保证只会产生97~122之间的整数
            sb.append((char)data);
        }
        return sb.toString();
    }

    /**
     * 根据指定长度随机生成大写字母
     * @param length 长度
     * @return 指定长度的随机大写字母字符串
     */
    public static String randomUpperWords(int length) {
        /*
           0~9的ASCII为48~57
           A~Z的ASCII为65~90
           a~z的ASCII为97~122
         */
        StringBuilder sb = new StringBuilder();
        Random randData = new Random();

        int data = 0;
        for (int i = 0; i < length; i++) {
            data = randData.nextInt(26) + 65;//保证只会产生65~90之间的整数
            sb.append((char) data);
        }
        return sb.toString();
    }

    /**
     * 根据指定长度随机生成数字
     * @param length 长度
     * @return 指定长度的随机数字
     */
    public static String randomNumbers(int length) {
        /*
           0~9的ASCII为48~57
           A~Z的ASCII为65~90
           a~z的ASCII为97~122
         */
        StringBuilder sb = new StringBuilder();
        Random randData = new Random();

        int data = 0;
        for(int i = 0; i < length; i++)
        {
            data=randData.nextInt(10);//仅仅会生成0~9
            sb.append(data);
        }
        return sb.toString();
    }

    /**
     * 生成32位UUID字符，去除字符'-'
     * @return 32位随机UUID字符串
     */
    public static String randomCustomUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();

        return uuidStr.replaceAll("-","");
    }

    /**
     * 生成36位UUID字符 / 非重复
     * @return 36未随机UUID字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 根据传入的length返回相应长度的随机字符串
     * @param length
     * @return
     */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numberAndLetters[new Random().nextInt(71)];
        }
        return new String(randBuffer);
    }
    //字符（数字与大写字母）
    public static String[] charsNum = new String[] { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "6", "8", "3", "5", "0", "B", "2", "D", "4", "F",
            "6", "H", "8", "J", "A", "1", "C", "3", "E", "5", "G", "7", "I",
            "9", "K", "2", "M", "4", "O", "6", "Q", "8", "S", "0", "U", "9",
            "W", "X", "Y", "Z", "1", "L", "3", "N", "5", "P", "7",
            "R", "9", "T", "6", "V" };

    /**
     * 生成唯一随机码（数字与大写字母）
     * @param number 位数
     * @return
     */
    public static String generateNumUuid(int number) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < number; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(charsNum[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
}
