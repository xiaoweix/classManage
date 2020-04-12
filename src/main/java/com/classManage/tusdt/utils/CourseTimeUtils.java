package com.classManage.tusdt.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 22:16
 */
public class CourseTimeUtils {

    public static Map<Integer,Integer> generateStartTimeMap() {
        Map<Integer,Integer> timeMap = new HashMap<>();
        timeMap.put(0,1);
        timeMap.put(2,1);
        timeMap.put(3,1);
        timeMap.put(4,1);
        timeMap.put(5,1);
        timeMap.put(6,1);
        timeMap.put(7,1);
        timeMap.put(8,1);
        timeMap.put(9,2);
        timeMap.put(10,3);
        timeMap.put(11,4);
        timeMap.put(12,5);
        timeMap.put(13,5);
        timeMap.put(14,6);
        timeMap.put(15,7);
        timeMap.put(16,8);
        timeMap.put(17,9);
        timeMap.put(18,10);
        timeMap.put(19,10);
        timeMap.put(20,11);
        timeMap.put(21,13);
        timeMap.put(22,13);
        timeMap.put(23,13);
        return timeMap;
    }

    public static Map<Integer,Integer> generateEndTimeMap() {
        Map<Integer,Integer> timeMap = new HashMap<>();
        timeMap.put(0,1);
        timeMap.put(2,1);
        timeMap.put(3,1);
        timeMap.put(4,1);
        timeMap.put(5,1);
        timeMap.put(6,1);
        timeMap.put(7,1);
        timeMap.put(8,1);
        timeMap.put(9,2);
        timeMap.put(10,3);
        timeMap.put(11,4);
        timeMap.put(12,5);
        timeMap.put(13,5);
        timeMap.put(14,5);
        timeMap.put(15,6);
        timeMap.put(16,7);
        timeMap.put(17,9);
        timeMap.put(18,10);
        timeMap.put(19,10);
        timeMap.put(20,11);
        timeMap.put(21,12);
        timeMap.put(22,13);
        timeMap.put(23,13);
        return timeMap;
    }
}
