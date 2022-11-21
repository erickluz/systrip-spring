package com.erick.driver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erick.driver.domain.Driver;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer>{

}
