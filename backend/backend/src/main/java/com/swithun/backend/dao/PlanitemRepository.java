package com.swithun.backend.dao;

import com.swithun.backend.entity.PlanitemEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanitemRepository extends JpaRepository<PlanitemEntity, Integer>{
    
}
