/*
 * @Descripttion: 日期相关
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-12 15:52:48
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 09:24:51
 */
package com.swithun.backend.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.graalvm.compiler.lir.aarch64.AArch64Move.LoadAddressOp;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public static String todayYMD() {
        return LocalDate.now().toString();
    }

    public static String todayOfWeek() {
        return String.valueOf(LocalDate.now().getDayOfWeek().getValue());
    }

    public static String todayD() {
        return String.valueOf(LocalDate.now().getDayOfMonth());
    }

    public static String todayMD() {
        return String.valueOf(LocalDate.now().getDayOfYear());
    }

    public static String[] parse(LocalDate localDate) {
        String strs[] = new String[4];
        // ymd, md, d, dofW
        String ymd = localDate.toString();
        strs[0] = ymd; // year-month-day
        System.out.println(ymd);
        strs[1] = ymd.substring(5); // month-day
        strs[2] = ymd.substring(8); // day
        strs[3] = String.valueOf(localDate.getDayOfWeek().getValue());
        return strs;
    }

    public static String[] parsePreNext(LocalDate localDate) {
        String strs[] = new String[6];
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        // For 非重复计划
        strs[0] = localDate.with(fieldISO, 1).plusDays(-1).toString(); // 上周日 year-month-day
        strs[1] = localDate.with(fieldISO, 7).plusDays(1).toString(); // 下周一 year-month-day
        System.out.println("上周日 " + strs[0]);
        System.out.println("下周一 " + strs[1]);
        // For 年重复计划
        strs[2] = strs[0].substring(5);// 上周日 month-day
        strs[3] = strs[1].substring(5);// 下周一 month-day
        // For 月重复计划
        strs[4] = strs[0].substring(8); // 上周日 day
        strs[5] = strs[1].substring(8); // 下周一 day
        return strs;
    }

    public static String TimeStamp2LocalDateStr(String timeStampStr) {
        long timestamp = Long.valueOf(timeStampStr);
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr =localDate.format(fmt);
        System.out.println("DateUtil: " + dateStr);
        return dateStr;
    }
}
