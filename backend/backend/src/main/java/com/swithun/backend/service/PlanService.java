/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-12 16:42:46
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-10 09:18:00
 */
package com.swithun.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
import com.swithun.backend.dao.RelationRepository;
import com.swithun.backend.dao.TrackRepository;
import com.swithun.backend.dao.UnfinishedPlanRepository;
import com.swithun.backend.dao.subTaskRepository;
import com.swithun.backend.entity.FinishedPlanEntity;
import com.swithun.backend.entity.FinishedTaskRecordEntity;
import com.swithun.backend.entity.PlanEntity;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.entity.RelationEntity;
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
  private RelationRepository relationR;
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

  public Map<String, Object> dealWithGetAllPlan(String date) {
    // 0 获取所有这一天的任务(确定时间的 任务)
    List<UnfinishedPlanEntity> ufPlans = gettaskbydate(date);

    Map<String, Object> mp = new ConcurrentHashMap<>();
    List<PlanEntity> oPlans = new ArrayList<>();
    List<PlanEntity> tPlans = new ArrayList<>();

    Set<Integer> checkPlanIds = new HashSet<>();
    // 1. 处理 时间限制 计划
    for (UnfinishedPlanEntity ufPlan : ufPlans) {
      PlanEntity plan = ufPlan.getPlanByPlanId();
      if (!isOrderLimitedPlan(plan)) { // 如果有时间限制
        tPlans.add(plan);
        checkPlanIds.add(plan.getId());
      }
    }
    mp.put("timeLimitedPlanSet", tPlans);
    // 2. 处理顺序限制 邻接表
    List<RelationEntity> allRelation = relationR.findAll();// 获取所有 relation
    Map<String, Set<String>> piles = new ConcurrentHashMap<>();
    Set<Integer> checkedRelation = new HashSet<>();
    boolean notFinish = false;
    do {
      notFinish = false;
      for (RelationEntity relation : allRelation) {
        if (!checkedRelation.contains(relation.getId())) {

          Integer pre = relation.getPlanByPrePlanId().getId();
          Integer back = relation.getPlanByPlanId().getId();

          String pre_str = (isOrderLimitedPlan(relation.getPlanByPrePlanId()) ? "o" : "t") + String.valueOf(pre);
          String back_str = (isOrderLimitedPlan(relation.getPlanByPlanId()) ? "o" : "t") + String.valueOf(back);

          if (checkPlanIds.contains(pre) || checkPlanIds.contains(back)) {

            notFinish = true;

            checkedRelation.add(relation.getId());

            if (!checkPlanIds.contains(pre)) {
              checkPlanIds.add(pre);
              if (isOrderLimitedPlan(relation.getPlanByPrePlanId())) {
                oPlans.add(relation.getPlanByPrePlanId()); // 添加 顺序限制 Plan
              }
            }
            if (!checkPlanIds.contains(back)) {
              checkPlanIds.add(back);
              if (isOrderLimitedPlan(relation.getPlanByPlanId())) {
                oPlans.add(relation.getPlanByPlanId()); // 添加 顺序限制 Plan
              }
            }
            if (!piles.containsKey(pre_str)) {
              piles.put(pre_str, new HashSet<>()); // 添加 桩
            }
            piles.get(pre_str).add(back_str); // 给 桩 添加数据
          }
        }
      }
    } while (notFinish);

    mp.put("planRelation", piles);

    // 3. 处理顺序限制 plan

    mp.put("orderLimitedPlanSet", oPlans);

    return mp;
  }

  public boolean isOrderLimitedPlan(PlanEntity plan) {
    return plan.getExpectedStartTimeBegin() == null && plan.getExpectedStartTimeEnd() == null
        && plan.getExpectedEndTimeBegin() == null && plan.getExpectedEndTimeEnd() == null;
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
  public Integer addPlan(PlanEntity plan) {

    // 0. 完善plan
    conveter.completeAddPlan(plan);
    System.out.println("addPlan " + plan.getExpectedStartDate());

    // 1 将subPlan 的plan设置为 当前plan
    for (SubTaskEntity subTask : plan.getSubTasksById()) {
      subTask.setPlanByParentPlan(plan);
    }
    // 2. 保存 plan
    PlanEntity savedPlan = planR.save(plan);
    // 3. 在 未完成计划中登记
    UnfinishedPlanEntity unfinishedPlanEntity = new UnfinishedPlanEntity();
    unfinishedPlanEntity.setPlanByPlanId(plan);
    ufPlanR.save(unfinishedPlanEntity);
    return savedPlan.getId();
  }

  public void finishPlanByOnce(FinishedTaskRecordEntity finishedTaskRecordEntity) {
    fTaskRecordR.save(finishedTaskRecordEntity);
  }

  public void addPlanType(PlanTypeEntity planTypeEntity) {
    System.out.println(planTypeEntity.getId());
    System.out.println(planTypeEntity.getName());
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

  public void addRelation(Integer plan_id, Integer pre_plan_id) {
    PlanEntity plan = new PlanEntity(plan_id);
    PlanEntity prePlan = new PlanEntity(pre_plan_id);
    RelationEntity relation = new RelationEntity();
    relation.setPlanByPlanId(plan);
    relation.setPlanByPrePlanId(prePlan);
    relationR.save(relation);
  }

  public void updateRelation(Map<String, Object> mp) {
    Integer updateType = (Integer) mp.get("type");
    List<Integer> relation = (List<Integer>) mp.get("relation");
    if (updateType == 0) {
      System.out.println("删除前");
      relationR.deleteAllByPlanByPlanIdAndPlanByPrePlanId(new PlanEntity(relation.get(1)),
          new PlanEntity(relation.get(0)));
      System.out.println("删除后");
      RelationEntity newRelation = new RelationEntity();
      newRelation.setPlanByPlanId(new PlanEntity(relation.get(2)));
      newRelation.setPlanByPrePlanId(new PlanEntity(relation.get(0)));
      relationR.save(newRelation);
    } else {
      relationR.deleteAllByPlanByPlanIdAndPlanByPrePlanId(new PlanEntity(relation.get(0)),
          new PlanEntity(relation.get(1)));
      RelationEntity newRelation = new RelationEntity();
      newRelation.setPlanByPlanId(new PlanEntity(relation.get(0)));
      newRelation.setPlanByPrePlanId(new PlanEntity(relation.get(2)));
      relationR.save(newRelation);
    }
  }

  public void deleteRelation(Map<String, Object> mp) {
    List<Integer> relation = (List<Integer>) mp.get("relation");
    PlanEntity plan = new PlanEntity();
    plan.setId(relation.get(0));
    PlanEntity prePlan = new PlanEntity();
    prePlan.setId(relation.get(1));
    relationR.deleteAllByPlanByPlanIdAndPlanByPrePlanId(plan, prePlan);
  }

  public Map<String, Set<String>> deletePlan(PlanEntity plan) {

    Map<String, Set<String>> mp = new HashMap<>();

    Set<String> rs = new HashSet<>();
    Set<String> oPlans = new HashSet<>();

    List<RelationEntity> delRelations = relationR.findAllByPlanByPlanIdOrPlanByPrePlanId(plan, plan);

    for (RelationEntity relation : delRelations) {
      PlanEntity backPlan = relation.getPlanByPlanId();
      PlanEntity prePlan = relation.getPlanByPrePlanId();

      String backPlan_str = null;
      String prePlan_str = null;

      if (isOrderLimitedPlan(backPlan)) {
        backPlan_str = "o" + String.valueOf(backPlan.getId());
        if (backPlan.getId() != plan.getId())
          oPlans.add(backPlan_str);
      }
      if (isOrderLimitedPlan(prePlan)) {
        prePlan_str = "o" + String.valueOf(prePlan.getId());
        if (prePlan.getId() != plan.getId())
          oPlans.add(prePlan_str);
      }
      rs.add("r" + prePlan_str + "-" + backPlan_str);

    }

    mp.put("relations", rs);
    mp.put("plans", oPlans);

    plan = planR.findById(plan.getId()).get();

    System.out.println("plan ID " + plan.getId());

    List<UnfinishedPlanEntity> ufPlans = new ArrayList<>(plan.getUnfinishedPlansById());

    plan.getUnfinishedPlansById().removeAll(ufPlans);

    ufPlanR.deleteAll(ufPlans);
    planR.delete(plan);

    return mp;

  }

  public void updatePlanPos(Map<String, Object> mp) {
    PlanEntity plan = planR.findOneById((Integer) mp.get("id"));
    plan.setX((Integer)mp.get("x"));
    plan.setY((Integer)mp.get("y"));
    planR.save(plan);
  }

}
