package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackService {

    @Transactional
    public void addRack(Rack rack) {
        LocalDateTime now = LocalDateTime.now();
        rack.setCreatedAt(now);
        rack.setModifiedAt(now);
        rack.persist();
    }

    @Transactional
    public Rack getRack(UUID id) {
        return Rack.findById(id);
    }

    @Transactional
    public boolean removeRack(UUID id) {
        return Rack.deleteById(id);
    }

    @Transactional
    public List<Rack> getRacks() {
        return Rack.listAll();
    }

    @Transactional
    public void updateRack(UUID id, Rack newRack) {
        Rack entity = Rack.findById(id);
        if (entity == null) {
            throw new NotFoundException("Rack not found");
        }

        entity.setSerialNumber(newRack.getSerialNumber());
        entity.setTeamId(newRack.getTeamId());
        entity.setCreatedAt(newRack.getCreatedAt());
        entity.setModifiedAt(newRack.getModifiedAt());
        entity.setDefaultLocation(newRack.getDefaultLocation());
        entity.setStatus(newRack.getStatus());
    }

}
