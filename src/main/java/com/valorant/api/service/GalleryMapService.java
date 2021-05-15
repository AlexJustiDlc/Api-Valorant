package com.valorant.api.service;

import com.valorant.api.model.GalleryMap;
import com.valorant.api.repository.GalleryMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GalleryMapService {

    @Autowired
    private GalleryMapRepository repository;

    public List<GalleryMap> getAll(){
        return repository.findAll();
    }

    public GalleryMap findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public GalleryMap create(GalleryMap galleryMap){
        return repository.save(galleryMap);
    }

    public GalleryMap update(GalleryMap galleryMap, Integer id){
        GalleryMap galleryMapUpd = this.findById(id);
        galleryMapUpd.setImage(galleryMap.getImage());
        return repository.save(galleryMapUpd);
    }
}
