package com.utils;

/**
 * ClassName： StringUtil
 * package：com.utils
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/5 上午7:29
 * @Version 1.0
 */
public class StringUtil {
    //判斷字符串是否為null
    public static boolean isEmpty(String string){
        return string == null || "".equals(string);
    }

    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }
}