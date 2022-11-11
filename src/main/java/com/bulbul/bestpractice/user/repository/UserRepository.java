package com.bulbul.bestpractice.user.repository;

import com.bulbul.bestpractice.user.dto.projection.UserProjection;
import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    Optional<User> findByUsername(String username);
    Set<UserProjection> findByIdInAndIsActive(Set<Long> userIds, Boolean status);
}