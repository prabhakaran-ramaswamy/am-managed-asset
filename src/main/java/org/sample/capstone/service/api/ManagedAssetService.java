package org.sample.capstone.service.api;

import java.util.List;

import org.sample.capstone.entity.ManagedAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagedAssetService {

	public abstract ManagedAsset findOne(Long id);

	public abstract void delete(ManagedAsset managedAsset);

	public abstract List<ManagedAsset> save(Iterable<ManagedAsset> entities);

	public abstract void delete(Iterable<Long> ids);

	public abstract ManagedAsset save(ManagedAsset entity);

	public abstract ManagedAsset findOneForUpdate(Long id);

	public abstract List<ManagedAsset> findAll(Iterable<Long> ids);

	public abstract List<ManagedAsset> findAll();

	public abstract long count();

	public abstract Page<ManagedAsset> findAll(Pageable pageable);

}
