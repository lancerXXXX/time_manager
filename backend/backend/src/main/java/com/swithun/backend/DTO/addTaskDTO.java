/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 19:46:12
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 19:56:15
 */
package com.swithun.backend.DTO;

import java.util.List;

import com.swithun.backend.entity.SubTaskEntity;
import com.swithun.backend.entity.TrackEntity;

public class addTaskDTO {
    public addTaskDTO() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public Integer getTime() {
        return time;
    }
    public void setTime(Integer time) {
        this.time = time;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Integer getDevotion() {
        return devotion;
    }
    public void setDevotion(Integer devotion) {
        this.devotion = devotion;
    }
    public Integer getSatisfaction() {
        return satisfaction;
    }
    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }
    public List<SubTaskEntity> getSubTasks() {
        return subTasks;
    }
    public void setSubTasks(List<SubTaskEntity> subTasks) {
        this.subTasks = subTasks;
    }
    public List<TrackEntity> getTracks() {
        return tracks;
    }
    public void setTracks(List<TrackEntity> tracks) {
        this.tracks = tracks;
    }
    private String name;
    private Integer type;
    private String startTime;
    private String endTime;
    private Integer time;
    private String note;
    private Integer devotion;
    private Integer satisfaction;
    private List<SubTaskEntity> subTasks;
    private List<TrackEntity> tracks;
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 19:46:12
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 19:46:36
 */
