/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-23 15:43:33
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-23 15:46:23
 */
package com.swithun.backend.DTO;

public class AddOrderLimitDTO {
    public AddOrderLimitDTO(Integer bannedTaskType, Integer basedTaskType, Integer order) {
		this.bannedTaskType = bannedTaskType;
		this.basedTaskType = basedTaskType;
		this.order = order;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getBannedTaskType() {
		return bannedTaskType;
	}
	public void setBannedTaskType(Integer bannedTaskType) {
		this.bannedTaskType = bannedTaskType;
	}
	public Integer getBasedTaskType() {
		return basedTaskType;
	}
	public void setBasedTaskType(Integer basedTaskType) {
		this.basedTaskType = basedTaskType;
	}
	private Integer bannedTaskType;
    private Integer basedTaskType;
    private Integer order;
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-23 15:43:33
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-23 15:44:22
 */
