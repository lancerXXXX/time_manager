/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 19:21:59
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 20:12:50
 */
package com.swithun.backend.dao;

import java.util.ArrayList;
import java.util.List;

import com.swithun.backend.entity.SubTaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface subTaskRepository extends JpaRepository<SubTaskEntity, Integer> {
    @Override
    default <S extends SubTaskEntity> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<S>();
        for (S entity: entities) {
            result.add(save(entity));
        }
        return result;
    }
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-28 19:21:59
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-28 19:22:00
 */
