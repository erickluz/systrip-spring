package com.erick.trip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erick.trip.domain.travel.Travel;

@Repository
public interface TravelRepository extends CrudRepository<Travel, Integer> {

}
