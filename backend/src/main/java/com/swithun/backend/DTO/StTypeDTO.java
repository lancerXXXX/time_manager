/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:51:01
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:51:02
 */
package com.swithun.backend.DTO;

public class StTypeDTO {
    public Boolean getNot() {
        return not;
    }
    public void setNot(Boolean not) {
        this.not = not;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    private Boolean not;
    private Integer id;
}