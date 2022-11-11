package com.bulbul.bestpractice.rolemanagement.repository;

import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {
    Optional<Role> findByName(String name);
}