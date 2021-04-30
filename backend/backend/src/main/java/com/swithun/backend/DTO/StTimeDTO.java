/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:50:40
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:50:42
 */
package com.swithun.backend.DTO;

import java.util.List;

public class StTimeDTO {
    public List<Boolean> getNot() {
        return not;
    }
    public void setNot(List<Boolean> not) {
        this.not = not;
    }
    public List<String> getDate() {
        return date;
    }
    public void setDate(List<String> date) {
        this.date = date;
    }
    public List<String> getTime() {
        return time;
    }
    public void setTime(List<String> time) {
        this.time = time;
    }
    private List<Boolean> not;
    private List<String> date;
    private List<String> time;
}