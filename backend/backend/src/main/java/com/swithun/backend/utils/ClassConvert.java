/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-30 15:03:48
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-30 16:39:22
 */
package com.swithun.backend.utils;

import com.swithun.backend.entity.StDevotionEntity;
import com.swithun.backend.entity.StNameEntity;
import com.swithun.backend.entity.StSatisfactionEntity;
import com.swithun.backend.entity.StTimeEntity;
import com.swithun.backend.entity.StTypeEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.swithun.backend.DTO.AddStatisticTemplateDTO;
import com.swithun.backend.DTO.StDevotionDTO;
import com.swithun.backend.DTO.StSatisfactionDTO;
import com.swithun.backend.DTO.StTimeDTO;
import com.swithun.backend.DTO.StTypeDTO;

@Component
public class ClassConvert {
    public StTimeEntity StTimeDTO2StTimeEntity(StTimeDTO f_time, StatisticTemplateEntity st) {
        StTimeEntity t_time = new StTimeEntity();
        t_time.setNotDate(f_time.getNot().get(0));
        t_time.setNotTime(f_time.getNot().get(1));
        t_time.setStartDate(f_time.getDate().get(0));
        t_time.setEndDate(f_time.getDate().get(1));
        t_time.setStartTime(f_time.getTime().get(0));
        t_time.setEndTime(f_time.getTime().get(1));
        if (st != null) {
            t_time.setStatisticTemplateByStId(st);
        }
        return t_time;
    }

    public List<StTimeEntity> StTimeDTOs2StTimeEntities(List<StTimeDTO> f_times, StatisticTemplateEntity st) {
        List<StTimeEntity> t_times = new ArrayList<>();
        for (StTimeDTO f_time : f_times) {
            t_times.add(StTimeDTO2StTimeEntity(f_time, st));
        }
        System.out.println("t_times " + t_times.size());
        return t_times;
    }

    public StNameEntity StNameDTO2StNameEntity(String f_name, StatisticTemplateEntity st) {
        StNameEntity t_name = new StNameEntity();
        t_name.setName(f_name);
        if (st != null)
            t_name.setStatisticTemplateByStId(st);
        return t_name;
    }

    public List<StNameEntity> stNameDTOs2StNameEntities(List<String> f_names, StatisticTemplateEntity st) {
        List<StNameEntity> t_names = new ArrayList<>();
        for (String f_name : f_names) {
            t_names.add(StNameDTO2StNameEntity(f_name, st));
        }
        return t_names;
    }

    public StTypeEntity stTypeDTO2StTypeEntity(StTypeDTO f_type, StatisticTemplateEntity st) {
        StTypeEntity t_type = new StTypeEntity();
        t_type.setNotType(f_type.getNot());
        t_type.setPlanType(f_type.getId());
        if (st != null)
            t_type.setStatisticTemplateByStId(st);
        return t_type;
    }

    public List<StTypeEntity> stTypeDTOs2StTypeEntities(List<StTypeDTO> f_types, StatisticTemplateEntity st) {
        List<StTypeEntity> t_types = new ArrayList<>();
        for (StTypeDTO f_type : f_types) {
            t_types.add(stTypeDTO2StTypeEntity(f_type, st));
        }
        return t_types;
    }

    public Collection<StDevotionEntity> stDevotionDTOs2StDevotionsEntities(List<StDevotionDTO> f_devotions,
            StatisticTemplateEntity st) {
        List<StDevotionEntity> t_devotions = new ArrayList<>();
        for (StDevotionDTO f_devotion : f_devotions) {
            t_devotions.add(stDevotionDTO2StDevotionEntity(f_devotion, st));
        }
        return t_devotions;
    }

    private StDevotionEntity stDevotionDTO2StDevotionEntity(StDevotionDTO f_devotion, StatisticTemplateEntity st) {
        StDevotionEntity t_devotion = new StDevotionEntity();
        t_devotion.setOperator(f_devotion.getOperator());
        t_devotion.setLevel(f_devotion.getLevel());
        t_devotion.setStatisticTemplateByStId(st);
        return t_devotion;
    }

    public Collection<StSatisfactionEntity> stSatisfactionDTOs2StSatisfactionEntities(
            List<StSatisfactionDTO> f_satisfactions, StatisticTemplateEntity st) {
        List<StSatisfactionEntity> t_satisfactions = new ArrayList<>();
        for (StSatisfactionDTO f_satisfaction : f_satisfactions) {
            t_satisfactions.add(stSatisfactionDTO2StSatisfactionEntity(f_satisfaction, st));
        }
        return t_satisfactions;
    }

    public StSatisfactionEntity stSatisfactionDTO2StSatisfactionEntity(StSatisfactionDTO f_satisfaction,
            StatisticTemplateEntity st) {
        StSatisfactionEntity t_satisfaction = new StSatisfactionEntity();
        t_satisfaction.setOperator(f_satisfaction.getOperator());
        t_satisfaction.setLevel(f_satisfaction.getLevel());
        t_satisfaction.setStatisticTemplateByStId(st);
        return t_satisfaction;
    }

    public void statisticTemplateDTO2StatisticTemplateEntity(StatisticTemplateEntity origin_st, AddStatisticTemplateDTO dto) {
        // 1. create time
        origin_st.setStTimesById(StTimeDTOs2StTimeEntities(dto.getTime(), origin_st));
        // 2. add name
        origin_st.setStNamesById(stNameDTOs2StNameEntities(dto.getName(), origin_st));
        // 3. create type
        origin_st.setStTypesById(stTypeDTOs2StTypeEntities(dto.getType(), origin_st));
        // 4. create devotion
        origin_st.setStDevotionsById(stDevotionDTOs2StDevotionsEntities(dto.getDevotion(), origin_st));
        // 5. create satisfaction
        origin_st.setStSatisfactionsById(stSatisfactionDTOs2StSatisfactionEntities(dto.getSatisfaction(), origin_st));

    }

}
