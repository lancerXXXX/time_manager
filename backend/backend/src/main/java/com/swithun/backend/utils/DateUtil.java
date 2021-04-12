/*
 * @Descripttion: 日期相关
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-12 15:52:48
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-12 16:38:02
 */
package com.swithun.backend.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public String todayYMD() {
        return LocalDate.now().toString();
    }
    public String todayOfWeek() {
        return String.valueOf(LocalDate.now().getDayOfWeek().getValue());
    }
    public String todayD() {
        return String.valueOf(LocalDate.now().getDayOfMonth());
    }
    public String todayMD() {
        return String.valueOf(LocalDate.now().getDayOfYear());
    }
}
