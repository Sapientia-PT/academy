package com.ctw.workstation.team_member.boundary;

import com.ctw.workstation.team_member.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamMemberService {

    @Transactional
    public void addTeamMember(TeamMember teamMember) {
        teamMember.setCreatedAt(LocalDateTime.now());
        teamMember.setModifiedAt(LocalDateTime.now());
        teamMember.persist();
    }

    @Transactional
    public TeamMember getTeamMember(UUID id) {
        return TeamMember.findById(id);
    }

    @Transactional
    public boolean removeTeamMember(UUID id) {
        return TeamMember.deleteById(id);
    }

    @Transactional
    public List<TeamMember> getTeamMembers() {
        return TeamMember.listAll();
    }

}
