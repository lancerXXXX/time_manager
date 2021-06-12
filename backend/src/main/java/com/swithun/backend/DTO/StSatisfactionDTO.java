/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:51:23
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:51:48
 */
package com.swithun.backend.DTO;

public class StSatisfactionDTO {
    public Integer getOperator() {
        return operator;
    }
    public void setOperator(Integer operator) {
        this.operator = operator;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    private Integer operator;
    private Integer level;
}