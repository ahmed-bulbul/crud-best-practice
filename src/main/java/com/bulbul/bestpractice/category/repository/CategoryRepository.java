package com.bulbul.bestpractice.category.repository;

import com.bulbul.bestpractice.category.entity.Category;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends AbstractRepository<Category> {
}
