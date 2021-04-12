/*
 * @Descripttion: 日期相关
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-12 15:52:48
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-12 16:28:51
 */
package com.swithun.backend.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public String todayYMD() {
        return LocalDate.now().toString();
    }
    public String todayOfWeek() {
        return String.valueOf(DayOfWeek.from(LocalDate.now()).getValue());
    }
    public String todayD() {
        return LocalDate.now().toString().substring(8);
    }
    public String todayMD() {
        return LocalDate.now().toString().substring(5);
    }
}
