package com.bulbul.bestpractice.flightmanagement.repository;

import com.bulbul.bestpractice.flightmanagement.entity.Aircraft;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends AbstractRepository<Aircraft> {
}
