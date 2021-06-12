/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:22:20
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-30 10:32:11
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.StTimeEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StTimeRepository extends JpaRepository<StTimeEntity, Integer> {
	void deleteByStatisticTemplateByStId(StatisticTemplateEntity statisticTemplateEntity);
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:22:20
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:22:20
 */
