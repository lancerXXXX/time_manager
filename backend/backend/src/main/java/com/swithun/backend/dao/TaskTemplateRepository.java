/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 19:25:06
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-15 19:25:43
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.TaskTemplateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTemplateRepository extends JpaRepository<TaskTemplateEntity, Integer>{
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 19:25:06
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-15 19:25:07
 */
