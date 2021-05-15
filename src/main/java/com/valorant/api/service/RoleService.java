package com.valorant.api.service;

import com.valorant.api.model.Role;
import com.valorant.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> getAll(){
        return repository.findAll();
    }

    public Role findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String create(Role role){
        repository.save(role);
        return "Role \""+role.getName()+"\" created.";
    }

    public String update(Role role, Integer id){
        Role roleUpd = this.findById(id);
        roleUpd.setName(role.getName());
        roleUpd.setImage(role.getImage());
        return "Role \""+roleUpd.getName()+"\" updated to \""+role.getName()+"\".";
    }
}
