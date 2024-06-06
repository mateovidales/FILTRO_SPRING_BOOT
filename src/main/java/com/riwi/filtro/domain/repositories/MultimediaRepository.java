package com.riwi.filtro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.filtro.domain.entities.MultimediaEntity;

public interface MultimediaRepository extends JpaRepository<MultimediaEntity, Long>{
    
}
