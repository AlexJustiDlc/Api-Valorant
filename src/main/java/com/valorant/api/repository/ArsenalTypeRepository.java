package com.valorant.api.repository;

import com.valorant.api.model.ArsenalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArsenalTypeRepository extends JpaRepository<ArsenalType, Integer> {
}
