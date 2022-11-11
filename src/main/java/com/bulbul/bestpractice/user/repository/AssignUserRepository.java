package com.bulbul.bestpractice.user.repository;

import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignUserRepository extends AbstractRepository<AssignUser> {
    Boolean existsByFareInfoIdAndIsActive(Long fareInfoId, Boolean status);
}