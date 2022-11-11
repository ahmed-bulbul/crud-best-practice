package com.bulbul.bestpractice.flightmanagement.repository;

import com.bulbul.bestpractice.flightmanagement.dto.projection.AgencyProjection;
import com.bulbul.bestpractice.flightmanagement.entity.Agency;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AgencyRepository extends AbstractRepository<Agency> {

    Set<AgencyProjection> findByIdInAndIsActive(Set<Long> agencyIds, Boolean status);

    Set<Agency> findAllByNameIgnoreCaseOrCodeIgnoreCase(String name, String code);
}
