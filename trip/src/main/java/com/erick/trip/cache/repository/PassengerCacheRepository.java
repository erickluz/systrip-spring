package com.erick.trip.cache.repository;

import org.springframework.data.repository.CrudRepository;

import com.erick.trip.domain.passenger.PassengerCache;

public interface PassengerCacheRepository extends CrudRepository<PassengerCache, Integer> {

}
