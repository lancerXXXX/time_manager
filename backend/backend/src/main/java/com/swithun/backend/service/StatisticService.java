/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:43:09
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-10 13:50:36
 */
package com.swithun.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.swithun.backend.DTO.AddStatisticTemplateDTO;
import com.swithun.backend.DTO.StDevotionDTO;
import com.swithun.backend.DTO.StSatisfactionDTO;
import com.swithun.backend.DTO.StTimeDTO;
import com.swithun.backend.DTO.StTypeDTO;
import com.swithun.backend.dao.FinishedTaskRecordRepository;
import com.swithun.backend.dao.StDevotionRepository;
import com.swithun.backend.dao.StNameRepository;
import com.swithun.backend.dao.StSatisfactionRepository;
import com.swithun.backend.dao.StTimeRepository;
import com.swithun.backend.dao.StTypeRepository;
import com.swithun.backend.dao.StatisticTemplateRepository;
import com.swithun.backend.entity.FinishedTaskRecordEntity;
import com.swithun.backend.entity.StDevotionEntity;
import com.swithun.backend.entity.StNameEntity;
import com.swithun.backend.entity.StSatisfactionEntity;
import com.swithun.backend.entity.StTimeEntity;
import com.swithun.backend.entity.StTypeEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;
import com.swithun.backend.utils.ClassConvert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    @Autowired
    private FinishedTaskRecordRepository fTaskRecordR;

    Logger logger = LoggerFactory.getLogger(StatisticService.class);

    @Autowired
    private ClassConvert convert;

    public Integer addStatisticTemplate(AddStatisticTemplateDTO dto) {
        StatisticTemplateEntity st = new StatisticTemplateEntity();
        if (dto.getId() != null) {
            st = stR.findById(dto.getId()).get();
            /**
             * 清空旧数据
             */
            System.out.println("开始清空");
            // 1. time
            Collection<StTimeEntity> stTimesById = new ArrayList<>(st.getStTimesById());
            st.getStTimesById().removeAll(stTimesById);
            stTimeR.deleteAll(stTimesById);
            // 2. name
            Collection<StNameEntity> names = new ArrayList<>(st.getStNamesById());
            st.getStNamesById().removeAll(names);
            stNameR.deleteAll(names);

            // 3. type
            Collection<StTypeEntity> types = new ArrayList<>(st.getStTypesById());
            st.getStTypesById().removeAll(types);
            sttypeR.deleteAll(types);

            // 4. devotion
            Collection<StDevotionEntity> devotions = new ArrayList<>(st.getStDevotionsById());
            st.getStDevotionsById().removeAll(devotions);
            stDevotionR.deleteAll(devotions);

            // 5. satisfaction
            Collection<StSatisfactionEntity> satisfactions = new ArrayList<>(st.getStSatisfactionsById());
            st.getStSatisfactionsById().removeAll(satisfactions);
            stSatisfactionR.deleteAll(satisfactions);

            System.out.println("结束清空");
        }
        /**
         * 更新为新数据
         */

        convert.statisticTemplateDTO2StatisticTemplateEntity(st, dto);

        StatisticTemplateEntity save = stR.save(st);

        return save.getId();
    }

    public List<AddStatisticTemplateDTO> getAllTemplate() {
        List<StatisticTemplateEntity> f_sts = stR.findAll();
        List<AddStatisticTemplateDTO> t_sts = new ArrayList<>();
        for (StatisticTemplateEntity f_st : f_sts) {
            AddStatisticTemplateDTO t_st = new AddStatisticTemplateDTO();
            /**
             * 1. setId
             */
            t_st.setId(f_st.getId());
            /**
             * 2. setName
             */
            Collection<StNameEntity> f_names = f_st.getStNamesById();
            List<String> t_names = new ArrayList<String>();
            for (StNameEntity f_name : f_names) {
                t_names.add(f_name.getName());
            }
            t_st.setName(t_names);
            /**
             * 3. setTime
             */
            Collection<StTimeEntity> f_times = f_st.getStTimesById();
            List<StTimeDTO> t_times = new ArrayList<StTimeDTO>();
            for (StTimeEntity f_time : f_times) {
                StTimeDTO t_time = new StTimeDTO();
                /**
                 * 3.1 set Not
                 */
                List<Boolean> t_nots = new ArrayList<>();
                t_nots.add(f_time.getNotDate());
                t_nots.add(f_time.getNotTime());

                t_time.setNot(t_nots);
                /**
                 * 3.2 set date
                 */
                List<String> t_date = new ArrayList<>();
                t_date.add(f_time.getStartDate());
                t_date.add(f_time.getEndDate());

                t_time.setDate(t_date);
                /**
                 * 3.3 set Time
                 */

                List<String> t_time_time = new ArrayList<>();
                t_time_time.add(f_time.getStartTime());
                t_time_time.add(f_time.getEndTime());

                t_time.setTime(t_time_time);

                t_times.add(t_time);

            }
            t_st.setTime(t_times);

            /**
             * 4. setType
             */
            Collection<StTypeEntity> f_types = f_st.getStTypesById();
            List<StTypeDTO> t_types = new ArrayList<>();
            for (StTypeEntity f_type : f_types) {
                StTypeDTO t_type = new StTypeDTO();
                t_type.setId(f_type.getId());
                t_type.setNot(f_type.getNotType());
                t_types.add(t_type);
            }
            t_st.setType(t_types);

            /**
             * 5. setDevtion
             */
            Collection<StDevotionEntity> f_devotions = f_st.getStDevotionsById();
            List<StDevotionDTO> t_devotions = new ArrayList<>();
            for (StDevotionEntity f_devotion : f_devotions) {
                StDevotionDTO t_devotion = new StDevotionDTO();
                t_devotion.setLevel(f_devotion.getLevel());
                t_devotion.setOperator(f_devotion.getOperator());
                t_devotions.add(t_devotion);
            }
            t_st.setDevotion(t_devotions);
            /**
             * 6. setSatisfaction
             */

            Collection<StSatisfactionEntity> f_satisfactions = f_st.getStSatisfactionsById();
            List<StSatisfactionDTO> t_satisfactions = new ArrayList<>();
            for (StSatisfactionEntity f_stf : f_satisfactions) {
                StSatisfactionDTO t_stf = new StSatisfactionDTO();
                t_stf.setLevel(f_stf.getLevel());
                t_stf.setOperator(f_stf.getOperator());
                t_satisfactions.add(t_stf);
            }
            t_st.setDevotion(t_devotions);

            t_sts.add(t_st);
        }
        return t_sts;
    }

    public void removeSt(Integer id) {
        stR.deleteById(id);
    }

    public List<FinishedTaskRecordEntity> getFilteredDataBySTId(Integer id) {
        StatisticTemplateEntity st = stR.findById(id).get();
        return doGetFilterdData(st);
    }

    private List<FinishedTaskRecordEntity> doGetFilterdData(StatisticTemplateEntity st) {

        Collection<StDevotionEntity> devotions = st.getStDevotionsById();
        Collection<StNameEntity> names = st.getStNamesById();
        Collection<StTypeEntity> types = st.getStTypesById();
        Collection<StSatisfactionEntity> satisfactions = st.getStSatisfactionsById();

        List<FinishedTaskRecordEntity> allRecord = fTaskRecordR.findAll(new Specification<FinishedTaskRecordEntity>() {

            @Override
            public Predicate toPredicate(Root<FinishedTaskRecordEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                // 1. 获取 计划
                Join<Object, Object> joinPlan = root.join("planByPlanId");
                // 2. 获取 计划 属性
                // 2.1 获取 投入度
                Path<Object> devotionPath = joinPlan.get("devotion");
                // 2.2 获取 计划名称
                Path<Object> namePath = joinPlan.get("planName");
                // 2.3 获取 计划类型
                Join<Object, Object> joinPlanJoinPlanType = joinPlan.join("planTypeByPlanType");
                Path<Object> typeIdPath = joinPlanJoinPlanType.get("id");
                // 2.4 获取 满意度
                Path<Object> satisfactionPath = joinPlan.get("satisfaction");

                Predicate pdcDevotions = null;
                Predicate pcdNames = null;
                Predicate pcdTypes = null;
                Predicate pdcSatisfaction = null;

                // 3. 过滤
                // 3.1 过滤投入度
                for (StDevotionEntity dvt : devotions) {
                    Integer level = dvt.getLevel();
                    Integer operator = dvt.getOperator();

                    Predicate newPredication = null;

                    logger.warn("level " + level);
                    logger.warn("operator " + operator);

                    if (operator == 0) {
                        newPredication = criteriaBuilder.greaterThan(devotionPath.as(Integer.class), level);
                    }
                    if (operator == 1) {
                        newPredication = criteriaBuilder.lessThan(devotionPath.as(Integer.class), level);
                    }
                    if (operator == 2) {
                        newPredication = criteriaBuilder.equal(devotionPath.as(Integer.class), level);
                    }
                    if (operator == 3) {
                        newPredication = criteriaBuilder.notEqual(devotionPath.as(Integer.class), level);
                    }
                    if (operator == 4) {
                        newPredication = criteriaBuilder.greaterThanOrEqualTo(devotionPath.as(Integer.class), level);
                    }
                    if (operator == 5) {
                        newPredication = criteriaBuilder.lessThanOrEqualTo(devotionPath.as(Integer.class), level);
                    }
                    if (pdcDevotions == null) {
                        pdcDevotions = newPredication;
                    } else {
                        pdcDevotions = criteriaBuilder.and(pdcDevotions, newPredication);
                    }
                }
                // 3.2 过滤计划名称
                for (StNameEntity name : names) {
                    logger.warn("name " + name);
                    Predicate newPredication = criteriaBuilder.like(namePath.as(String.class),
                            "%" + name.getName() + "%");
                    if (pcdNames == null) {
                        pcdNames = newPredication;
                    } else {
                        pcdNames = criteriaBuilder.or(pcdNames, newPredication);
                    }
                }
                // 3.3 过滤计划种类
                for (StTypeEntity type : types) {
                    Boolean notType = type.getNotType();
                    Integer typeId = type.getPlanType();
                    logger.warn("notType " + notType);
                    logger.warn("typeId " + typeId);
                    Predicate newPredication = null;
                    if (!notType) {
                        newPredication = criteriaBuilder.equal(typeIdPath.as(Integer.class), typeId);
                    } else {
                        newPredication = criteriaBuilder.notEqual(typeIdPath.as(Integer.class), typeId);
                    }

                    if (pcdTypes == null) {
                        pcdTypes = newPredication;
                    } else {
                        pcdTypes = criteriaBuilder.or(pcdTypes, newPredication);
                    }
                }

                // 3.4 过滤 满意度
                for (StSatisfactionEntity stf : satisfactions) {
                    Integer level = stf.getLevel();
                    Integer operator = stf.getOperator();

                    Predicate newPredication = null;

                    logger.warn("level " + level);
                    logger.warn("operator " + operator);

                    if (operator == 0) {
                        newPredication = criteriaBuilder.greaterThan(satisfactionPath.as(Integer.class), level);
                    }
                    if (operator == 1) {
                        newPredication = criteriaBuilder.lessThan(satisfactionPath.as(Integer.class), level);
                    }
                    if (operator == 2) {
                        newPredication = criteriaBuilder.equal(satisfactionPath.as(Integer.class), level);
                    }
                    if (operator == 3) {
                        newPredication = criteriaBuilder.notEqual(satisfactionPath.as(Integer.class), level);
                    }
                    if (operator == 4) {
                        newPredication = criteriaBuilder.greaterThanOrEqualTo(satisfactionPath.as(Integer.class),
                                level);
                    }
                    if (operator == 5) {
                        newPredication = criteriaBuilder.lessThanOrEqualTo(satisfactionPath.as(Integer.class), level);
                    }
                    if (pdcSatisfaction == null) {
                        pdcSatisfaction = newPredication;
                    } else {
                        pdcSatisfaction = criteriaBuilder.and(pdcSatisfaction, newPredication);
                    }

                }

                return criteriaBuilder.and(pdcDevotions, pcdNames, pcdTypes, pdcSatisfaction);
            }

        });
        return allRecord;
    }

}
/*
 * @Descripttion:
 * 
 * @version:
 * 
 * @@Company: None
 * 
 * @Author: Swithun Liu
 * 
 * @Date: 2021-04-29 15:43:09
 * 
 * @LastEditors: Swithun Liu
 * 
 * @LastEditTime: 2021-04-29 15:43:10
 */
