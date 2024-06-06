package com.riwi.filtro.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.filtro.api.dto.response.ClassResp;
import com.riwi.filtro.domain.entities.ClassEntity;


public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    Page<ClassResp> findByNameAndDescription(String name, String description, Pageable pageable);
}
