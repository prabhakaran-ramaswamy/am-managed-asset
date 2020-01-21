package org.sample.capstone.service.api;

import java.util.List;

import org.sample.capstone.entity.ManagedAssert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagedAssertService {

	public abstract ManagedAssert findOne(Long id);

	public abstract void delete(ManagedAssert managedAssert);

	public abstract List<ManagedAssert> save(Iterable<ManagedAssert> entities);

	public abstract void delete(Iterable<Long> ids);

	public abstract ManagedAssert save(ManagedAssert entity);

	public abstract ManagedAssert findOneForUpdate(Long id);

	public abstract List<ManagedAssert> findAll(Iterable<Long> ids);

	public abstract List<ManagedAssert> findAll();

	public abstract long count();

	public abstract Page<ManagedAssert> findAll(Pageable pageable);

}
