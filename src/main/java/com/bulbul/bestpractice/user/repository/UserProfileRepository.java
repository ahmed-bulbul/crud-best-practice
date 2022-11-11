package com.bulbul.bestpractice.user.repository;

import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.user.dto.projection.UserProfileProjection;
import com.bulbul.bestpractice.user.entity.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserProfileRepository extends AbstractRepository<UserProfile> {
    Set<UserProfileProjection> findAlByIdInAndIsActive(Set<Long> userProfileId, Boolean isActive);
}