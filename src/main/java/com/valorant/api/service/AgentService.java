package com.valorant.api.service;

import com.valorant.api.model.Agent;
import com.valorant.api.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentService {

    @Autowired
    private AgentRepository repository;

    public List<Agent> getAll(){
        return repository.findAll();
    }

    public Agent findById(Integer id){
        return repository.findById(id).orElse(null);
    }


    public String create(Agent agent){
        repository.save(agent);
        return "Agent \""+agent.getName()+"\" created.";
    }

    public String update(Agent agent, Integer id){
        Agent agentUpd = this.findById(id);
        agentUpd.setName(agent.getName());
        agentUpd.setBiography(agent.getBiography());
        agentUpd.setImage(agent.getImage());
        agentUpd.setRole(agent.getRole());
        agentUpd.setCountry(agent.getCountry());
        agentUpd.setAbilities(agent.getAbilities());
        return "Agent \""+agentUpd.getName()+"\" updated to \""+agent.getName()+"\".";
    }
}
