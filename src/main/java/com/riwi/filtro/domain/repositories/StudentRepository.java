package com.riwi.filtro.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.riwi.filtro.domain.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    public Page<StudentEntity> findByNameAndActive(String name, Boolean active, PageRequest pagination);

}
