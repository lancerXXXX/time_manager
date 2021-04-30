/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:43:09
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 16:53:40
 */
package com.swithun.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.swithun.backend.DTO.AddStatisticTemplateDTO;
import com.swithun.backend.DTO.StDevotionDTO;
import com.swithun.backend.DTO.StSatisfactionDTO;
import com.swithun.backend.DTO.StTimeDTO;
import com.swithun.backend.DTO.StTypeDTO;
import com.swithun.backend.dao.StDevotionRepository;
import com.swithun.backend.dao.StNameRepository;
import com.swithun.backend.dao.StSatisfactionRepository;
import com.swithun.backend.dao.StTimeRepository;
import com.swithun.backend.dao.StTypeRepository;
import com.swithun.backend.dao.StatisticTemplateRepository;
import com.swithun.backend.entity.StDevotionEntity;
import com.swithun.backend.entity.StNameEntity;
import com.swithun.backend.entity.StSatisfactionEntity;
import com.swithun.backend.entity.StTimeEntity;
import com.swithun.backend.entity.StTypeEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    private StTimeRepository stTimeR;
    @Autowired
    private StNameRepository stNameR;
    @Autowired
    private StTypeRepository sttypeR;
    @Autowired
    private StDevotionRepository stDevotionR;
    @Autowired
    private StSatisfactionRepository stSatisfactionR;
    @Autowired
    private StatisticTemplateRepository stR;

    public Integer addStatisticTemplate(AddStatisticTemplateDTO dto) {
        /**
         * 0. save statisticTemplate
         */
        StatisticTemplateEntity st = stR.save(new StatisticTemplateEntity(dto.getTemplateName()));

        /**
         * 1. save time
         */
        List<StTimeDTO> from_times = dto.getTime();
        List<StTimeEntity> to_times = new ArrayList<> ();
        for (StTimeDTO f_time : from_times) {
            StTimeEntity to_time = new StTimeEntity();
            to_time.setNotDate(f_time.getNot().get(0));
            to_time.setNotTime(f_time.getNot().get(1));
            to_time.setStartDate(f_time.getDate().get(0));
            to_time.setEndDate(f_time.getDate().get(1));
            to_time.setStartTime(f_time.getTime().get(0));
            to_time.setEndTime(f_time.getTime().get(1));
            to_time.setStatisticTemplateByStId(st);
            to_times.add(to_time);
        }
        stTimeR.saveAll(to_times);
        /**
         * 2. save name
         */
        List<String> f_names = dto.getName();
        List<StNameEntity> t_names = new ArrayList<> ();
        for (String f_name : f_names) {
            StNameEntity t_name = new StNameEntity();
            t_name.setName(f_name);
            t_name.setStatisticTemplateByStId(st);
            t_names.add(t_name);
        }
        stNameR.saveAll(t_names);
        /**
         * 3. save type
         */
        List<StTypeDTO> f_types = dto.getType();
        List<StTypeEntity> t_types = new ArrayList<>();
        for (StTypeDTO f_type : f_types) {
            StTypeEntity t_type = new StTypeEntity();
            t_type.setNotType(f_type.getNot());
            t_type.setPlanType(f_type.getId());
            t_type.setStatisticTemplateByStId(st);
            t_types.add(t_type);
        }
        sttypeR.saveAll(t_types);
        /**
         * 4. save devotion
         */
        List<StDevotionDTO> f_devotions = dto.getDevotion();
        List<StDevotionEntity> t_devotions = new ArrayList<>();
        for (StDevotionDTO f_devotion : f_devotions) {
            StDevotionEntity t_devotion = new StDevotionEntity();
            t_devotion.setOperator(f_devotion.getOperator());
            t_devotion.setLevel(f_devotion.getLevel());
            t_devotion.setStatisticTemplateByStId(st);
            t_devotions.add(t_devotion);
        }
        stDevotionR.saveAll(t_devotions);

        /**
         * 5. satisfaction
         */
        List<StSatisfactionDTO> f_satisfactions = dto.getSatisfaction();
        List<StSatisfactionEntity> t_satisfactions = new ArrayList<>();
        for (StSatisfactionDTO f_satisfaction : f_satisfactions) {
            StSatisfactionEntity t_satisfaction = new StSatisfactionEntity();
            t_satisfaction.setOperator(f_satisfaction.getOperator());
            t_satisfaction.setLevel(f_satisfaction.getLevel());
            t_satisfaction.setStatisticTemplateByStId(st);
            t_satisfactions.add(t_satisfaction);
        }
        stSatisfactionR.saveAll(t_satisfactions);

        return st.getId();
    }
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:43:09
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:43:10
 */
