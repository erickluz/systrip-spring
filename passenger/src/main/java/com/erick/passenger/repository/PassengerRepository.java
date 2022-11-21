package com.erick.passenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erick.passenger.domain.Passenger;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {

}
