/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-25 21:43:06
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-26 15:16:47
 */
package com.swithun.backend.DTO;

import java.util.List;

public class getTimeLimitDTO {
    public getTimeLimitDTO(Integer id, String startTime, String endTime, List<Integer> taskType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }
    public getTimeLimitDTO() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startId) {
        this.startTime = startId;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public List<Integer> getTaskType() {
        return taskType;
    }
    public void setTaskType(List<Integer> taskType) {
        this.taskType = taskType;
    }
    private Integer id;
    private String startTime;
    private String endTime;
    private List<Integer> taskType;
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-25 21:43:06
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-25 21:43:07
 */
