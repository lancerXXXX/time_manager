/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-12 16:42:46
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-06 09:44:58
 */
package com.swithun.backend.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.swithun.backend.DTO.addTaskDTO;
import com.swithun.backend.dao.FinishedPlanRepository;
import com.swithun.backend.dao.FinishedTaskRecordRepository;
import com.swithun.backend.dao.PlanRepository;
import com.swithun.backend.dao.PlanTypeRepository;
import com.swithun.backend.dao.TrackRepository;
import com.swithun.backend.dao.UnfinishedPlanRepository;
import com.swithun.backend.dao.subTaskRepository;
import com.swithun.backend.entity.FinishedPlanEntity;
import com.swithun.backend.entity.FinishedTaskRecordEntity;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.entity.SubTaskEntity;
import com.swithun.backend.entity.TrackEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;
import com.swithun.backend.utils.ClassConvert;
import com.swithun.backend.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.pattern.Converter;

@Service
public class PlanService {
  @Autowired
  PlanRepository planR;
  @Autowired
  private UnfinishedPlanRepository ufPlanR;
  @Autowired
  private FinishedTaskRecordRepository fTaskRecordR;
  @Autowired
  private PlanTypeRepository planTypeR;
  @Autowired
  private FinishedPlanRepository fPlanR;
  @Autowired
  private subTaskRepository subTaskR;
  @Autowired
  private TrackRepository trackR;
  @Autowired
  private ClassConvert conveter;

  /**
   * @description: 获取本周计划
   * @param {String date} date = "" 则默认取当日
   * @return {List<UnfinishedPlanEntity>}
   */
  public List<UnfinishedPlanEntity> getWeekPlan(String date) {
    LocalDate now = date == "" ? LocalDate.now() : LocalDate.parse(date);

    String strs[] = DateUtil.parsePreNext(now);

    List<UnfinishedPlanEntity> unfinishedPlanEntities = ufPlanR.findAll(new Specification<UnfinishedPlanEntity>() {
      /**
       *
       */
      private static final long serialVersionUID = 1L;

      @Override
      public Predicate toPredicate(Root<UnfinishedPlanEntity> root, CriteriaQuery<?> query,
          CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> join = root.join("planByPlanId");
        Path<Object> repeatTypePath = join.get("repeatType");
        Path<Object> expectedStartDatePath = join.get("expectedStartDate");
        Path<Object> expectedEndDatePath = join.get("expectedEndDate");
        // 非重复计划
        Predicate predicate0 = criteriaBuilder.equal(repeatTypePath, 0);
        Predicate predicate0Date = criteriaBuilder.or(
            criteriaBuilder.between(expectedStartDatePath.as(String.class), strs[0], strs[1]),
            criteriaBuilder.between(expectedEndDatePath.as(String.class), strs[0], strs[1]));
        Predicate type0 = criteriaBuilder.and(predicate0, predicate0Date);

        // 周重复计划
        Predicate type1 = criteriaBuilder.equal(repeatTypePath, 1);

        // 月重复计划
        Predicate predicate2 = criteriaBuilder.equal(repeatTypePath, 2);
        Predicate predicate2Date = criteriaBuilder.or(
            criteriaBuilder.between(expectedStartDatePath.as(String.class), strs[4], strs[5]),
            criteriaBuilder.between(expectedEndDatePath.as(String.class), strs[4], strs[5]));
        Predicate type2 = criteriaBuilder.and(predicate2, predicate2Date);

        Predicate predicate3 = criteriaBuilder.equal(repeatTypePath, 3);
        Predicate predicate3Date = criteriaBuilder.or(
            criteriaBuilder.between(expectedStartDatePath.as(String.class), strs[2], strs[3]),
            criteriaBuilder.between(expectedEndDatePath.as(String.class), strs[2], strs[3]));
        Predicate type3 = criteriaBuilder.and(predicate3, predicate3Date);

        return criteriaBuilder.or(type0, type1, type2, type3);
      }
    });
    return unfinishedPlanEntities;
  }

  /**
   * @description: 获取当天的task
   * @param {*}
   * @return {List<PlanEntity>}
   */

  public List<UnfinishedPlanEntity> gettaskbydate(String date) {
    LocalDate localDate = date == "" ? LocalDate.now() : LocalDate.parse(date);
    String[] strs = DateUtil.parse(localDate);

    List<UnfinishedPlanEntity> unfinishedPlanEntities = ufPlanR.findAll(new Specification<UnfinishedPlanEntity>() {

      @Override
      public Predicate toPredicate(Root<UnfinishedPlanEntity> root, CriteriaQuery<?> query,
          CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> join = root.join("planByPlanId");
        Path<Object> repeat_type = join.get("repeatType");
        Path<Object> expected_start_date_path = join.get("expectedStartDate");
        Path<Object> expected_end_date_path = join.get("expectedEndDate");

        Predicate p0 = criteriaBuilder.and(criteriaBuilder.equal(repeat_type, 0),
            criteriaBuilder.lessThanOrEqualTo(expected_start_date_path.as(String.class), strs[0]),
            criteriaBuilder.greaterThanOrEqualTo(expected_end_date_path.as(String.class), strs[0]));

        Predicate p1 = criteriaBuilder.and(criteriaBuilder.equal(repeat_type, 1),
            criteriaBuilder.lessThanOrEqualTo(expected_start_date_path.as(String.class), strs[3]),
            criteriaBuilder.greaterThanOrEqualTo(expected_end_date_path.as(String.class), strs[3]));

        Predicate p2 = criteriaBuilder.and(criteriaBuilder.equal(repeat_type, 2),
            criteriaBuilder.lessThanOrEqualTo(expected_start_date_path.as(String.class), strs[2]),
            criteriaBuilder.greaterThanOrEqualTo(expected_end_date_path.as(String.class), strs[2]));

        Predicate p3 = criteriaBuilder.and(criteriaBuilder.equal(repeat_type, 3),
            criteriaBuilder.lessThanOrEqualTo(expected_start_date_path.as(String.class), strs[1]),
            criteriaBuilder.greaterThanOrEqualTo(expected_end_date_path.as(String.class), strs[1]));
        return criteriaBuilder.or(p0, p1, p2, p3);
      }

    });
    return unfinishedPlanEntities;

  }

  /**
   * @description: 获取所有未完成计划
   * @param {*}
   * @return {List<PlanEntity>}
   */
  public List<UnfinishedPlanEntity> getAllPlan() {
    return ufPlanR.findAll();
  }

  /**
   * @description: 添加一个任务(日期为当前)
   * @param {*}
   * @return {*}
   */
  public void addPlan(PlanEntity plan) {

    // 0. 完善plan
    conveter.completeAddPlan(plan);
    System.out.println("addPlan " + plan.getExpectedStartDate());

    // 1. 保存 plan
    planR.save(plan);
    // 2. 在 未完成计划中登记
    UnfinishedPlanEntity unfinishedPlanEntity = new UnfinishedPlanEntity();
    unfinishedPlanEntity.setPlanByPlanId(plan);
    ufPlanR.save(unfinishedPlanEntity);
  }

  public void finishPlanByOnce(FinishedTaskRecordEntity finishedTaskRecordEntity) {
    fTaskRecordR.save(finishedTaskRecordEntity);
  }

  public void addPlanType(PlanTypeEntity planTypeEntity) {
    System.out.println(planTypeEntity.getId());
    System.out.println(planTypeEntity.getName());
    // System.out.println(planTypeEntity.getPlanTypeByParentId().getId());
    planTypeR.save(planTypeEntity);
  }

  public List<PlanTypeEntity> getAllPlanType() {
    return planTypeR.findAllByPlanTypeByParentId(null);
  }

  public void updatePlanTypeName(PlanTypeEntity planTypeEntity) {
    PlanTypeEntity old = planTypeR.findById(planTypeEntity.getId()).get();
    old.setName(planTypeEntity.getName());
    planTypeR.save(old);
  }

  public void deletePlanType(PlanTypeEntity planTypeEntity) {
    PlanTypeEntity originPlanTypeEntity = planTypeR.findById(planTypeEntity.getId()).get();
    planTypeR.deleteAll(originPlanTypeEntity.getPlanTypesById());
    planTypeR.delete(originPlanTypeEntity);
  }

  public PlanTypeEntity getPlanTypeEntityByName(String name) {
    return planTypeR.findOneByName(name);
  }

  public void addtask(addTaskDTO dto) {
    PlanEntity plan = new PlanEntity();
    plan.setPlanName(dto.getName());
    plan.setRepeatType(0);
    plan.setPracticeStartDateTime(dto.getStartTime());
    plan.setPracticeEndDateTime(dto.getEndTime());
    plan.setDevotion(dto.getDevotion());
    plan.setSatisfaction(dto.getSatisfaction());
    plan.setTime(dto.getTime());
    plan.setNote(dto.getNote());
    plan.setPlanTypeByPlanType(new PlanTypeEntity(dto.getType()));
    PlanEntity rtPlan = planR.save(plan);
    List<SubTaskEntity> subTasks = dto.getSubTasks();
    for (SubTaskEntity task : subTasks) {
      task.setPlanByParentPlan(rtPlan);
    }
    subTaskR.saveAll(subTasks);
    List<TrackEntity> tracks = dto.getTracks();
    for (TrackEntity track : tracks) {
      track.setPlanByParentPlan(rtPlan);
    }
    trackR.saveAll(tracks);
    FinishedTaskRecordEntity finishedTaskRecord = new FinishedTaskRecordEntity();
    finishedTaskRecord.setStartTime(dto.getStartTime());
    finishedTaskRecord.setEndTime(dto.getEndTime());
    finishedTaskRecord.setPlanByPlanId(rtPlan);
    fTaskRecordR.save(finishedTaskRecord);
    FinishedPlanEntity finishedPlanEntity = new FinishedPlanEntity();
    finishedPlanEntity.setPlanByPlanId(rtPlan);
    fPlanR.save(finishedPlanEntity);
  }

}
