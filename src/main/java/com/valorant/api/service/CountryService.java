package com.valorant.api.service;

import com.valorant.api.model.Country;
import com.valorant.api.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryService {

    @Autowired
    private CountryRepository repository;

    public List<Country> getAll(){
        return repository.findAll();
    }

    public Country findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String create(Country country){
        repository.save(country);
        return "Country \""+country.getName()+"\" created.";
    }

    public String update(Country country, Integer id){
        Country countryUpd = this.findById(id);
        countryUpd.setName(country.getName());
        return "Country \""+countryUpd.getName()+"\" updated to \""+country.getName()+"\".";
    }
}
