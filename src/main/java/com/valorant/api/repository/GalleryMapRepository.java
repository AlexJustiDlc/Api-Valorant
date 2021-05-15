package com.valorant.api.repository;

import com.valorant.api.model.GalleryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryMapRepository extends JpaRepository<GalleryMap, Integer> {
}
