package org.rj.frame.shiro.web.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 拉姆达
 * @author : zhuxueke
 * @since : 2018-01-12 16:53
 **/
public class Lambda {
    /**
     * 日期比较
     * @author : zhuxueke
     * @since : 2018/1/12 16:53
     */
    public static void main(String[] args) throws Exception{
        String str1 = "13:30";
        String str2 = "14:30";
        String date1 = "2017-03-09 05:" + str1;
        String date2 = "2017-03-09 05:" + str2;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> list = new ArrayList<Date>();
        list.add(new Date());
        list.add(sim.parse(date1));
        list.add(sim.parse(date2));
        Date date = list.stream().min(Comparator.comparing(num -> num)).get();
        System.out.println(sim.format(date));

        List<Integer> lists = Arrays.asList(1,8,5,6,3);
        lists.sort((Integer x, Integer y) -> x < y? -1 : 0);
        for (Integer item:
             lists) {
            System.out.println(item);
        }
    }
}
