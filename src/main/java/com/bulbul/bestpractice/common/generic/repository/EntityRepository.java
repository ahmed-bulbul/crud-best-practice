package com.bulbul.bestpractice.common.generic.repository;

import com.bulbul.bestpractice.common.generic.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

}
