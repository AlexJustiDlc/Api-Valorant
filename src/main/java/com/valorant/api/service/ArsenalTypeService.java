package com.valorant.api.service;

import com.valorant.api.model.ArsenalType;
import com.valorant.api.repository.ArsenalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArsenalTypeService {

    @Autowired
    private ArsenalTypeRepository repository;

    public List<ArsenalType> getAll(){
        return repository.findAll();
    }

    public ArsenalType findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String create(ArsenalType arsenalType){
        repository.save(arsenalType);
        return "Arsenal Type \""+arsenalType.getName()+"\" created.";
    }

    public String update(ArsenalType arsenalType, Integer id){
        ArsenalType arsenalTypeUpd = this.findById(id);
        arsenalTypeUpd.setName(arsenalType.getName());
        return "Arsenal Type \""+arsenalTypeUpd.getName()+"\" updated to \""+arsenalType.getName()+"\".";
    }
}
