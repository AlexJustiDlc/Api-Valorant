package com.valorant.api.service;

import com.valorant.api.model.Arsenal;
import com.valorant.api.repository.ArsenalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArsenalService {
    @Autowired
    private ArsenalRepository repository;

    public List<Arsenal> getAll(){
        return repository.findAll();
    }

    public Arsenal findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String create(Arsenal arsenal){
        repository.save(arsenal);
        return "Arsenal \""+arsenal.getName()+"\" created.";
    }

    public String update(Arsenal arsenal, Integer id){
        Arsenal arsenalUpd = this.findById(id);
        arsenalUpd.setName(arsenal.getName());
        arsenalUpd.setDescription(arsenal.getDescription());
        arsenalUpd.setImage(arsenal.getImage());
        arsenalUpd.setType(arsenal.getType());
        return "Arsenal \""+arsenalUpd.getName()+"\" updated to \""+arsenal.getName()+"\".";
    }
}
