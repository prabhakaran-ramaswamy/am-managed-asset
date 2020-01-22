package org.sample.capstone.service.impl;
import java.util.List;

import org.sample.capstone.entity.ManagedAsset;
import org.sample.capstone.repository.ManagedAssetRepository;
import org.sample.capstone.service.api.ManagedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class ManagedAssetServiceImpl implements ManagedAssetService {

    @Autowired
    private ManagedAssetRepository managedAssetRepository;


    @Transactional
    public void delete(ManagedAsset managedAsset) {
        managedAssetRepository.delete(managedAsset);
    }

    @Transactional
    public List<ManagedAsset> save(Iterable<ManagedAsset> entities) {
        return managedAssetRepository.saveAll(entities);
    }

    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ManagedAsset> toDelete = managedAssetRepository.findAllById(ids);
        managedAssetRepository.deleteInBatch(toDelete);
    }

    @Transactional
    public ManagedAsset save(ManagedAsset entity) {
        return managedAssetRepository.save(entity);
    }

    public ManagedAsset findOne(Long id) {
        return managedAssetRepository.getOne(id);
    }

    public ManagedAsset findOneForUpdate(Long id) {
        return managedAssetRepository.getOne(id);
    }

    public List<ManagedAsset> findAll(Iterable<Long> ids) {
        return managedAssetRepository.findAllById(ids);
    }

    public List<ManagedAsset> findAll() {
        return managedAssetRepository.findAll();
    }

    public long count() {
        return managedAssetRepository.count();
    }

    public Page<ManagedAsset> findAll(Pageable pageable) {
        return managedAssetRepository.findAll( pageable);
    }


    public Class<ManagedAsset> getEntityType() {
        return ManagedAsset.class;
    }

    public Class<Long> getIdType() {
        return Long.class;
    }
}
