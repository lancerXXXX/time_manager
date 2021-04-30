/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:30:27
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 16:48:15
 */

package com.swithun.backend.DTO;

import java.util.List;

public class AddStatisticTemplateDTO {
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public List<StTimeDTO> getTime() {
        return time;
    }
    public void setTime(List<StTimeDTO> time) {
        this.time = time;
    }
    public List<String> getName() {
        return name;
    }
    public void setName(List<String> name) {
        this.name = name;
    }
    public List<StTypeDTO> getType() {
        return type;
    }
    public void setType(List<StTypeDTO> type) {
        this.type = type;
    }
    public List<StDevotionDTO> getDevotion() {
        return devotion;
    }
    public void setDevotion(List<StDevotionDTO> devotion) {
        this.devotion = devotion;
    }
    public List<StSatisfactionDTO> getSatisfaction() {
        return satisfaction;
    }
    public void setSatisfaction(List<StSatisfactionDTO> satisfaction) {
        this.satisfaction = satisfaction;
    }
    private String templateName;
    private List<StTimeDTO> time;
    private List<String> name;
    private List<StTypeDTO> type;
    private List<StDevotionDTO> devotion;
    private List<StSatisfactionDTO> satisfaction;
}
