package com.valorant.api.security.service;

import com.valorant.api.security.model.RoleUser;
import com.valorant.api.security.repository.RoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleUserService {

    @Autowired
    private RoleUserRepository repository;

    public List<RoleUser> getAll(){
        return (List<RoleUser>) repository.findAll();
    }

    public RoleUser getByName(String name){
        return repository.findByName(name);
    }

    public String create(RoleUser role){
        repository.save(role);
        return "Role \""+role.getName()+"\" Created.";
    }
}
