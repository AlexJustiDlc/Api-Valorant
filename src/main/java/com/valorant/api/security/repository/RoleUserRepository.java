package com.valorant.api.security.repository;

import com.valorant.api.security.model.RoleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepository extends CrudRepository<RoleUser, Integer> {
    RoleUser findByName(String name);
}
