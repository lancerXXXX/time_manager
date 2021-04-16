/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-06 08:59:19
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-16 10:13:38
 */
package com.swithun.backend;

import java.time.LocalDate;
import java.util.List;

import com.swithun.backend.entity.UnfinishedPlanEntity;
import com.swithun.backend.service.PlanService;
import com.swithun.backend.utils.DateUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.ForEmulatedAttachment;


@SpringBootTest
class BackendApplicationTests {

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

}
