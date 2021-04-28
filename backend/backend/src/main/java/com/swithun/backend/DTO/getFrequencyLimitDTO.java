/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-26 13:30:54
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-26 13:34:29
 */
package com.swithun.backend.DTO;

import java.util.List;

public class getFrequencyLimitDTO {
    public getFrequencyLimitDTO(Integer id, List<Integer> taskType, Integer min, Integer max, Integer time) {
		this.id = id;
		this.taskType = taskType;
		this.min = min;
		this.max = max;
		this.time = time;
	}
	public getFrequencyLimitDTO() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getTaskType() {
		return taskType;
	}
	public void setTaskType(List<Integer> taskType) {
		this.taskType = taskType;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	private Integer id;
    private List<Integer> taskType;
    private Integer min;
    private Integer max;
    private Integer time;
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-26 13:30:54
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-26 13:30:55
 */
