package com.ctw.workstation.rack_asset.boundary;

import com.ctw.workstation.rack_asset.entity.RackAsset;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackAssetService {

    @Transactional
    public void addRackAsset(RackAsset rackAsset) {
        rackAsset.persist();
    }

    @Transactional
    public RackAsset getRackAsset(UUID id) {
        return RackAsset.findById(id);
    }

    @Transactional
    public boolean removeRackAsset(UUID id) {
        return RackAsset.deleteById(id);
    }

    public List<RackAsset> getRackAssets() {
        return RackAsset.listAll();
    }

}
