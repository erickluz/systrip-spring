package com.erick.trip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erick.trip.domain.travel.TravelStatusHistory;

@Repository
public interface TravelStatusHistoryRepository extends CrudRepository<TravelStatusHistory, Integer>{

}
