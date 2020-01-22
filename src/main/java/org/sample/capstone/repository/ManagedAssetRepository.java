package org.sample.capstone.repository;

import org.sample.capstone.entity.ManagedAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ManagedAssetRepository extends JpaRepository<ManagedAsset, Long> {
}

