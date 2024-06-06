package com.riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.filtro.domain.entities.LessonEntity;

public interface LessonRepository extends JpaRepository<LessonEntity, Long>{
    
}
