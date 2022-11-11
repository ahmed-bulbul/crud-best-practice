package com.bulbul.bestpractice.flightmanagement.repository;

import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.flightmanagement.entity.Airport;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends AbstractRepository<Airport> {
}
