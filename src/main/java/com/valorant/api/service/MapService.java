package com.valorant.api.service;

import com.valorant.api.model.Map;
import com.valorant.api.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MapService {

    @Autowired
    private MapRepository repository;

    public List<Map> getAll(){
        return repository.findAll();
    }

    public Map findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String create(Map map){
        repository.save(map);
        return "Map \""+map.getName()+"\" created.";
    }

    public String update(Map map, Integer id){
        Map mapUpd = this.findById(id);
        mapUpd.setName(map.getName());
        mapUpd.setDescription(map.getDescription());
        mapUpd.setGallery(map.getGallery());
        return "Map \""+mapUpd.getName()+"\" updated to \""+map.getName()+"\".";
    }
}
