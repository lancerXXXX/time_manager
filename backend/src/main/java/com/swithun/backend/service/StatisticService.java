/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:43:09
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-31 08:58:17
 */
package com.swithun.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import com.swithun.backend.utils.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    private StatisticTemplateRepository stR;
    @Autowired
    private FinishedTaskRecordRepository fTaskRecordR;

    @Autowired
    private StDevotionRepository devotionR;
    @Autowired
    private StNameRepository nameR;
    @Autowired
    private StSatisfactionRepository satisfactionR;
    @Autowired
    private StTimeRepository timeR;
    @Autowired
    private StTypeRepository typeR;

    @Autowired
    private ClassConvert convert;

    Logger logger = LoggerFactory.getLogger(StatisticService.class);

    public StatisticTemplateEntity addStatisticTemplate(StatisticTemplateEntity st) {

        logger.info("id是 " + st.getId());

        if (st.getId() != null) {
            logger.info("id不是null" + st.getId());
            StatisticTemplateEntity origin_st = stR.findById(st.getId()).get();
            if (origin_st != null) {

                Collection<StDevotionEntity> devotions = new ArrayList<StDevotionEntity>(
                        origin_st.getStDevotionsById());
                origin_st.getStDevotionsById().removeAll(devotions);
                devotionR.deleteAll(devotions);

                Collection<StNameEntity> names = new ArrayList<StNameEntity>(origin_st.getStNamesById());
                origin_st.getStNamesById().removeAll(names);
                nameR.deleteAll(names);

                Collection<StSatisfactionEntity> satisfactions = new ArrayList<StSatisfactionEntity>(
                        origin_st.getStSatisfactionsById());
                origin_st.getStSatisfactionsById().removeAll(satisfactions);
                satisfactionR.deleteAll(satisfactions);

                Collection<StTimeEntity> times = new ArrayList<StTimeEntity>(origin_st.getStTimesById());
                origin_st.getStTimesById().removeAll(times);
                timeR.deleteAll(times);

                Collection<StTypeEntity> types = new ArrayList<StTypeEntity>(origin_st.getStTypesById());
                origin_st.getStTypesById().removeAll(types);
                typeR.deleteAll(types);

                stR.save(origin_st);

            }
        }

        Collection<StDevotionEntity> devotions = st.getStDevotionsById();
        logger.warn("投入度  " + devotions.size());
        for (StDevotionEntity devotion : devotions) {
            devotion.setStatisticTemplateByStId(st);
        }
        Collection<StNameEntity> names = st.getStNamesById();
        for (StNameEntity name : names) {
            name.setStatisticTemplateByStId(st);
        }
        Collection<StSatisfactionEntity> satisfactions = st.getStSatisfactionsById();
        for (StSatisfactionEntity satisfaction : satisfactions) {
            satisfaction.setStatisticTemplateByStId(st);
        }
        Collection<StTimeEntity> times = st.getStTimesById();
        for (StTimeEntity time : times) {
            time.setStatisticTemplateByStId(st);
        }
        Collection<StTypeEntity> types = st.getStTypesById();
        for (StTypeEntity type : types) {
            type.setStatisticTemplateByStId(st);
        }
        return stR.save(st);
        // return null;
    }

    public List<StatisticTemplateEntity> getAllTemplate() {
        List<StatisticTemplateEntity> f_sts = stR.findAll();
        for (StatisticTemplateEntity st : f_sts) {
            Collection<StTypeEntity> types = st.getStTypesById();
            for (StTypeEntity type : types) {
                type.setTypeList(convert.getTypeListByPlanTypeId(type.getPlanType()));
            }
        }
        return f_sts;
    }

    public void removeSt(Integer id) {
        stR.deleteById(id);
    }

    public List<FinishedTaskRecordEntity> getFilteredDataBySTId(Integer id) {
        StatisticTemplateEntity st = stR.findById(id).get();
        List<FinishedTaskRecordEntity> allFinishedRecord = doGetFilterdData(st);
        logger.warn("处理时间之前" + allFinishedRecord.size());
        for (FinishedTaskRecordEntity record : allFinishedRecord) {
            record.getPlanByPlanId().setTypeList(convert.getTypeList(record.getPlanByPlanId()));
        }
        ArrayList<FinishedTaskRecordEntity> out = new ArrayList<FinishedTaskRecordEntity>();
        for (FinishedTaskRecordEntity recordEntity : allFinishedRecord) {
            String startTime = recordEntity.getStartTime();
            String endTime = recordEntity.getEndTime();
            Collection<StTimeEntity> times = st.getStTimesById();

            if (TimeFilter(times, startTime, endTime)) {
                out.add(recordEntity);
            }
        }
        return out;
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

                Path<Object> temp = root.get("id");
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
                logger.info("2.5 获取开始结束时间");
                // Path<Object> startTimePath = root.get("startTime");
                // Path<Object> endTimePath = root.get("endTime");

                // Predicate pdcDevotions = null;
                // Predicate pcdNames = null;
                // Predicate pcdTypes = null;
                // Predicate pdcSatisfaction = null;
                // Predicate pdcTimes = null;
                Predicate pdcDevotions = criteriaBuilder.conjunction();
                Predicate pcdNames = criteriaBuilder.conjunction();
                Predicate pcdTypes = criteriaBuilder.conjunction();
                Predicate pdcSatisfaction = criteriaBuilder.conjunction();

                // 3. 过滤
                // 3.1 过滤投入度
                if (devotions != null) {
                    for (StDevotionEntity dvt : devotions) {
                        Integer level = dvt.getLevel();
                        Integer operator = dvt.getOperator();

                        Predicate newPredication = null;

                        logger.warn("level " + level);
                        logger.warn("operator " + operator);

                        if (operator == null) {

                        } else if (operator == 0) {
                            newPredication = criteriaBuilder.greaterThan(devotionPath.as(Integer.class), level);
                        } else if (operator == 1) {
                            newPredication = criteriaBuilder.lessThan(devotionPath.as(Integer.class), level);
                        } else if (operator == 2) {
                            newPredication = criteriaBuilder.equal(devotionPath.as(Integer.class), level);
                        } else if (operator == 3) {
                            newPredication = criteriaBuilder.notEqual(devotionPath.as(Integer.class), level);
                        } else if (operator == 4) {
                            newPredication = criteriaBuilder.greaterThanOrEqualTo(devotionPath.as(Integer.class),
                                    level);
                        } else if (operator == 5) {
                            newPredication = criteriaBuilder.lessThanOrEqualTo(devotionPath.as(Integer.class), level);
                        }

                        pdcDevotions = criteriaBuilder.and(pdcDevotions, newPredication);
                    }
                }
                // 3.2 过滤计划名称
                if (names != null) {
                    for (StNameEntity name : names) {
                        logger.warn("name " + name);
                        Predicate newPredication = criteriaBuilder.like(namePath.as(String.class),
                                "%" + name.getName() + "%");
                        if (pcdNames == null) {
                            pcdNames = newPredication;
                        } else {
                            pcdNames = criteriaBuilder.and(pcdNames, newPredication);
                        }
                    }
                }
                // 3.3 过滤计划种类
                if (types != null) {
                    if (types.size() > 0) {
                        pcdTypes = criteriaBuilder.not(pcdTypes);
                    }
                    logger.info("过滤计划种类");
                    for (StTypeEntity type : types) {
                        Boolean notType = type.getNotType();
                        Integer typeId = type.getPlanType();
                        logger.warn("notType " + notType);
                        logger.warn("typeId " + typeId);
                        Predicate tempTypePredication = criteriaBuilder.conjunction();
                        tempTypePredication = criteriaBuilder.not(tempTypePredication);
                        List<Integer> allTypes = convert.getChildrenType(typeId);
                        logger.error("allTypes长度" + allTypes.size());

                        for (Integer typeIdforCheck : allTypes) {
                            logger.error(String.valueOf(typeIdforCheck));
                            tempTypePredication = criteriaBuilder.or(tempTypePredication,
                                    criteriaBuilder.equal(typeIdPath.as(Integer.class), typeIdforCheck));
                        }

                        if (notType) {
                            tempTypePredication = criteriaBuilder.not(tempTypePredication);
                        }

                        pcdTypes = criteriaBuilder.or(pcdTypes, tempTypePredication);
                    }
                } else {
                    logger.info("没有进入过滤计划种类");
                }

                // 3.4 过滤 满意度

                if (satisfactions != null) {
                    for (StSatisfactionEntity stf : satisfactions) {
                        Integer level = stf.getLevel();
                        Integer operator = stf.getOperator();

                        Predicate newPredication = null;

                        logger.warn("level " + level);
                        logger.warn("operator " + operator);

                        if (operator == null) {

                        } else if (operator == 0) {
                            newPredication = criteriaBuilder.greaterThan(satisfactionPath.as(Integer.class), level);
                        } else if (operator == 1) {
                            newPredication = criteriaBuilder.lessThan(satisfactionPath.as(Integer.class), level);
                        } else if (operator == 2) {
                            newPredication = criteriaBuilder.equal(satisfactionPath.as(Integer.class), level);
                        } else if (operator == 3) {
                            newPredication = criteriaBuilder.notEqual(satisfactionPath.as(Integer.class), level);
                        } else if (operator == 4) {
                            newPredication = criteriaBuilder.greaterThanOrEqualTo(satisfactionPath.as(Integer.class),
                                    level);
                        } else if (operator == 5) {
                            newPredication = criteriaBuilder.lessThanOrEqualTo(satisfactionPath.as(Integer.class),
                                    level);
                        }
                        pdcSatisfaction = criteriaBuilder.and(pdcSatisfaction, newPredication);

                    }
                }

                // logger.info("3.5 开始过滤时间");

                // if (times != null) {
                // for (var time : times) {
                // if (time.getRepeatType() == 0) {
                // // 开始时间在范围内
                // Predicate pStart = criteriaBuilder.between(startTimePath.as(String.class),
                // time.getStartDate(), time.getEndDate());
                // // 结束时间在范围内
                // Predicate pEnd = criteriaBuilder.between(endTimePath.as(String.class),
                // time.getStartDate(),
                // time.getEndDate());
                // pdcTimes = criteriaBuilder.or(pStart, pEnd);

                // if (time.getNotDate() == true) {
                // pdcTimes = criteriaBuilder.not(pdcTimes);
                // }

                // } else if (time.getRepeatType() == 1) {
                // // logger.warn("startTimePath 转 String：" + startTimePath.toString());
                // Integer dayOfWeek =
                // DateUtil.getDayOfWeek(DateUtil.TimeStamp2LocalDate(startTimePath.toString()));
                // criteriaBuilder.between(String.valueOf(dayOfWeek), time.getStartDate(),
                // time.getEndDate());
                // // startTimePath.
                // }
                // }

                logger.info("开始结合");
                Predicate ans = criteriaBuilder.isNotNull(temp);
                if (pdcDevotions != null) {
                    ans = criteriaBuilder.and(ans, pdcDevotions);
                }
                if (pcdNames != null) {
                    ans = criteriaBuilder.and(ans, pcdNames);
                }
                if (pcdTypes != null) {
                    ans = criteriaBuilder.and(ans, pcdTypes);
                }
                if (pdcSatisfaction != null) {
                    ans = criteriaBuilder.and(ans, pdcSatisfaction);
                }

                // return criteriaBuilder.and(pdcDevotions, pcdNames, pcdTypes,
                // pdcSatisfaction);
                return ans;
            }

        });
        return allRecord;
    }

    public boolean TimeFilter(Collection<StTimeEntity> times, String startTime, String endTime) {
        logger.info("3.5 开始过滤时间了");
        logger.info("时间数组长度 " + times.size());
        Boolean flagSum = false;
        if (times.size() == 0) {
            return true;
        }
        for (StTimeEntity time : times) {
            Boolean flag = true;
            logger.info("重复类型为:" + time.getRepeatType());
            if (time.getRepeatType() == 0) {

                LocalDate dataStartTime = DateUtil.TimeStamp2LocalDate(startTime);
                logger.info("dataStartTime " + dataStartTime.toString());
                LocalDate datadEndTime = DateUtil.TimeStamp2LocalDate(endTime);
                logger.info("datadEndTime " + datadEndTime.toString());
                LocalDate stStartDateTime = DateUtil.TimeStamp2LocalDate(time.getStartDate());
                logger.info("stStartDateTime " + stStartDateTime.toString());
                LocalDate stEndDateTime = DateUtil.TimeStamp2LocalDate(time.getEndDate());
                logger.info("stEndDateTime " + stEndDateTime.toString());

                if (stStartDateTime.compareTo(dataStartTime) <= 0 && stEndDateTime.compareTo(dataStartTime) >= 0 || 
                    stStartDateTime.compareTo(datadEndTime) <= 0 && stEndDateTime.compareTo(datadEndTime) >= 0) {

                // if (dataStartTime.compareTo(stStartDateTime) >= 0 && datadEndTime.compareTo(stStartDateTime) <= 0
                //         || dataStartTime.compareTo(stEndDateTime) >= 0 && datadEndTime.compareTo(stEndDateTime) <= 0) {
                    logger.info("符合兄弟");
                    if (time.getNotDate()) {
                        flag = false;
                    }
                } else {
                    if (!time.getNotDate()) {
                        flag = false;
                    }
                }

                // if (startTime.compareTo(time.getStartDate()) >= 0 &&
                // startTime.compareTo(time.getEndDate()) <= 0
                // || endTime.compareTo(time.getStartDate()) >= 0 &&
                // endTime.compareTo(time.getEndDate()) <= 0) {
                // if (time.getNotDate()) {
                // flag = false;
                // }
                // } else {
                // if (time.getNotDate() == false) {
                // flag = false;
                // }
                // }

            } else if (time.getRepeatType() == 2) {
                String startDate = time.getStartDate();
                String[] split = startDate.split(",");
                List<String> days = Arrays.asList(split);
                for (String day : days) {
                    // logger.info("有 " + day);
                }
                String startDay = String.valueOf(DateUtil.getDayOfWeek(DateUtil.TimeStamp2LocalDate(startTime)));
                String endDay = String.valueOf(DateUtil.getDayOfWeek(DateUtil.TimeStamp2LocalDate(endTime)));

                // logger.info("哈哈" + startDay + " " + endDay);

                Boolean tempFlag = false;
                for (String day : days) {
                    if (startDay.compareTo(day) <= 0 && endDay.compareTo(day) >= 0) {
                        tempFlag = true;
                    }
                }
                if (tempFlag) {
                    if (time.getNotDate()) {
                        flag = false;
                    }
                } else {
                    if (!time.getNotDate()) {
                        flag = false;
                    }
                }
            } else if (time.getRepeatType() == 3) {
                String startDate = time.getStartDate();
                String[] split = startDate.split(",");
                List<String> days = Arrays.asList(split);
                for (String day : days) {
                    // logger.info("有 " + day);
                }
                String startDay = String.valueOf(DateUtil.TimeStamp2LocalDate(startTime).getDayOfMonth() + 1);
                String endDay = String.valueOf(DateUtil.TimeStamp2LocalDate(endTime).getDayOfMonth() + 1);

                // logger.info("哈哈" + startDay + " " + endDay);

                Boolean tempFlag = false;
                for (String day : days) {
                    if (startDay.compareTo(day) <= 0 && endDay.compareTo(day) >= 0) {
                        tempFlag = true;
                    }
                }
                if (tempFlag) {
                    if (time.getNotDate()) {
                        flag = false;
                    }
                } else {
                    if (!time.getNotDate()) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                flagSum = true;
            }
        }
        // logger.info("3.5 过滤时间完毕");
        return flagSum;
    }

    public List<FinishedTaskRecordEntity> getFilteredDataWithouSTId(StatisticTemplateEntity st) {
        List<FinishedTaskRecordEntity> allFinishedRecord = doGetFilterdData(st);
        for (FinishedTaskRecordEntity record : allFinishedRecord) {
            record.getPlanByPlanId().setTypeList(convert.getTypeList(record.getPlanByPlanId()));
        }
        ArrayList<FinishedTaskRecordEntity> out = new ArrayList<FinishedTaskRecordEntity>();
        for (FinishedTaskRecordEntity recordEntity : allFinishedRecord) {
            String startTime = recordEntity.getStartTime();
            String endTime = recordEntity.getEndTime();
            Collection<StTimeEntity> times = st.getStTimesById();

            if (TimeFilter(times, startTime, endTime)) {
                out.add(recordEntity);
            }
        }
        return out;
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
