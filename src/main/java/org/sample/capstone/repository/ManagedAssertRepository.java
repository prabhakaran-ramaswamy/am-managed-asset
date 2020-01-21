package org.sample.capstone.repository;

import org.sample.capstone.entity.ManagedAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ManagedAssertRepository extends JpaRepository<ManagedAssert, Long> {
}

