package com.bulbul.bestpractice.flightmanagement.repository;

import com.bulbul.bestpractice.flightmanagement.entity.FareInfo;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.flightmanagement.dto.projection.FareInfoProjection;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FareInfoRepository extends AbstractRepository<FareInfo> {
    Set<FareInfoProjection> findByIdInAndIsActive(Set<Long> fareIds, Boolean status);

    Set<FareInfo> findAllByUniqueCodeIgnoreCase(String uniqueCode);
}
