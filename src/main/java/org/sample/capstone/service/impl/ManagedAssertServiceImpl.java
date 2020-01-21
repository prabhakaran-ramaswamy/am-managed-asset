package org.sample.capstone.service.impl;
import java.util.List;

import org.sample.capstone.entity.ManagedAssert;
import org.sample.capstone.repository.ManagedAssertRepository;
import org.sample.capstone.service.api.ManagedAssertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class ManagedAssertServiceImpl implements ManagedAssertService {

    @Autowired
    private ManagedAssertRepository managedAssertRepository;


    @Transactional
    public void delete(ManagedAssert managedAssert) {
        managedAssertRepository.delete(managedAssert);
    }

    @Transactional
    public List<ManagedAssert> save(Iterable<ManagedAssert> entities) {
        return managedAssertRepository.saveAll(entities);
    }

    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ManagedAssert> toDelete = managedAssertRepository.findAllById(ids);
        managedAssertRepository.deleteInBatch(toDelete);
    }

    @Transactional
    public ManagedAssert save(ManagedAssert entity) {
        return managedAssertRepository.save(entity);
    }

    public ManagedAssert findOne(Long id) {
        return managedAssertRepository.getOne(id);
    }

    public ManagedAssert findOneForUpdate(Long id) {
        return managedAssertRepository.getOne(id);
    }

    public List<ManagedAssert> findAll(Iterable<Long> ids) {
        return managedAssertRepository.findAllById(ids);
    }

    public List<ManagedAssert> findAll() {
        return managedAssertRepository.findAll();
    }

    public long count() {
        return managedAssertRepository.count();
    }

    public Page<ManagedAssert> findAll(Pageable pageable) {
        return managedAssertRepository.findAll( pageable);
    }


    public Class<ManagedAssert> getEntityType() {
        return ManagedAssert.class;
    }

    public Class<Long> getIdType() {
        return Long.class;
    }
}
