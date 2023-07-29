package com.sc.lock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import com.sc.lock.entity.BusDetails;

import jakarta.persistence.LockModeType;

public interface BusRepository extends CrudRepository<BusDetails, Long> {

//	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<BusDetails> findWithLockingById(Long id);
}
