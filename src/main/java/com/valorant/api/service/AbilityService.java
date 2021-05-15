package com.valorant.api.service;

import com.valorant.api.model.Ability;
import com.valorant.api.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AbilityService {

    @Autowired
    private AbilityRepository repository;

    public List<Ability> getAll(){
        return repository.findAll();
    }

    public Ability findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String create(Ability ability){
        repository.save(ability);
        return "Ability \""+ability.getName()+"\" created.";
    }

    public String update(Ability ability, Integer id){
        Ability abilityUpd = this.findById(id);
        abilityUpd.setName(ability.getName());
        abilityUpd.setDescription(ability.getDescription());
        abilityUpd.setImage(ability.getImage());
        abilityUpd.setVideo(ability.getVideo());
        return "Ability \""+abilityUpd.getName()+"\" updated to \""+ability.getName()+"\".";
    }
}
