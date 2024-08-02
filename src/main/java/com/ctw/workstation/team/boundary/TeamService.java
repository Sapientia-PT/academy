package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamService {

    @Transactional
    public void addTeam(Team team) {
        team.setCreatedAt(LocalDateTime.now());
        team.setModifiedAt(LocalDateTime.now());
        team.persist();
    }

    @Transactional
    public Team getTeam(UUID id) {
        return Team.findById(id);
    }

    @Transactional
    public boolean removeTeam(UUID id) {
        return Team.deleteById(id);
    }

    @Transactional
    public List<Team> getTeams() {
        return Team.listAll();
    }

}
