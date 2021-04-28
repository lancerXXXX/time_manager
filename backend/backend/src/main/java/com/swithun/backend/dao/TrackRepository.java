/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 19:23:16
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 19:23:44
 */
package com.swithun.backend.dao;

import com.swithun.backend.entity.TrackEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<TrackEntity, Integer> {
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 19:23:16
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 19:23:17
 */
