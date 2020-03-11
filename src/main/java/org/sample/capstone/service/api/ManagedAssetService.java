package org.sample.capstone.service.api;

import java.util.List;

import org.sample.capstone.entity.ManagedAssetView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagedAssetService {

	public abstract ManagedAssetView findOne(Long id);

	public abstract void delete(ManagedAssetView managedAssetView);

	public abstract List<ManagedAssetView> save(Iterable<ManagedAssetView> managedAssetViews);

	public abstract void delete(Iterable<Long> ids);

	public abstract ManagedAssetView save(ManagedAssetView managedAssetView);

	public abstract ManagedAssetView findOneForUpdate(Long id);

	public abstract List<ManagedAssetView> findAll(Iterable<Long> ids);

	public abstract List<ManagedAssetView> findAll();

	public abstract long count();

	public abstract Page<ManagedAssetView> findAll(Pageable pageable);

}
