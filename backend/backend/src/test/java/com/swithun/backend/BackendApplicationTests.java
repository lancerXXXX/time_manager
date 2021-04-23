/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-06 08:59:19
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-21 13:49:43
 */
package com.swithun.backend;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.swithun.backend.dao.PlanTypeRepository;
import com.swithun.backend.entity.PlanTypeEntity;
import com.swithun.backend.entity.UnfinishedPlanEntity;
import com.swithun.backend.service.PlanService;
import com.swithun.backend.utils.DateUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	PlanService planService;

	@Autowired
	PlanTypeRepository planTypeRepository;

	@Test
	void contextLoads() {
		String strs[] = DateUtil.parse(LocalDate.now());
		for (int i = 0; i < 4; i++) {
			System.out.println(strs[i]);
		}
	}

	@Test
	void testTaskByDate() {
		PlanService planService = new PlanService();
		List<UnfinishedPlanEntity> unfinishedPlanEntities = planService.gettaskbydate("");
		System.out.println("end");
		for (UnfinishedPlanEntity unfinishedPlanEntity : unfinishedPlanEntities) {
			System.out.println("test: " + unfinishedPlanEntity.getPlanByPlanId());
		}
	}

	@Test
	void testTimeStamp() {
		Timestamp timestamp = Timestamp.valueOf("2021-04-16 12:35:34");
		String a = timestamp.toString();
		System.out.println("testTimeStamp    " + a);
	}

	@Test
	void testAddPlanType() {
		PlanTypeEntity planTypeEntity = new PlanTypeEntity();
		planTypeEntity.setName("娱乐");
		planService.addPlanType(planTypeEntity);
	}

	@Test
	void testGetAllPlanType() {
		List<PlanTypeEntity> ls = planService.getAllPlanType();
		for (PlanTypeEntity planTypeEntity : ls) {
			System.out.println(planTypeEntity.getName());
		}

	}

	@Test
	void testDeletePlanType() {
		PlanTypeEntity planTypeEntity = new PlanTypeEntity();
		planTypeEntity.setId(11);
		planTypeEntity.setName("几何1.0");
		planService.updatePlanTypeName(planTypeEntity);
	}

}
